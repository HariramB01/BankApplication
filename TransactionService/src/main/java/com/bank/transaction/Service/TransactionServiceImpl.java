package com.bank.transaction.Service;


import com.bank.SharedDTO.Request.TransactionEvent;
import com.bank.SharedDTO.Response.AccountResponse;
import com.bank.SharedDTO.status.TRANSACTION_STATUS;
import com.bank.SharedDTO.status.TRANSACTION_TYPE;
import com.bank.transaction.Entity.Transaction;
import com.bank.transaction.Mapper.Mapper;
import com.bank.transaction.Repository.TransactionRepository;
import com.bank.transaction.Request.DepositRequest;
import com.bank.transaction.Request.TransferRequest;
import com.bank.transaction.Request.WithdrawRequest;
import com.bank.transaction.client.AccountClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{

    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
    private KafkaTemplate<String, TransactionEvent> kafkaTemplate;

    private static final String TRANSACTION_EVENT_TOPIC = "transaction-event";

    private TransactionRepository transactionRepository;
    private AccountClient accountClient;

    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  AccountClient accountClient,
                                  KafkaTemplate<String, TransactionEvent> kafkaTemplate){
        this.transactionRepository = transactionRepository;
        this.accountClient = accountClient;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void transfer(TransferRequest transferRequest) {
        logger.info("Initializing transfer request made by {}", transferRequest.getFromAccountId());

        AccountResponse fromAccount = getAccountResponse(transferRequest.getFromAccountId());
        AccountResponse toAccount = getAccountResponse(transferRequest.getToAccountId());

        if (fromAccount.getBalance() < transferRequest.getAmount()) {
            logger.error("Insufficient funds in account ID {}", fromAccount.getId());
            throw new RuntimeException("Insufficient funds in the account.");
        }

        fromAccount.setBalance(fromAccount.getBalance() - transferRequest.getAmount());
        toAccount.setBalance(toAccount.getBalance() + transferRequest.getAmount());

        updateAccount(fromAccount);
        updateAccount(toAccount);

        TransactionEvent transactionEvent = Mapper.convertTransactionToTransactionEvent(saveTransaction("TRANSFER", transferRequest.getAmount(), fromAccount.getId(), toAccount.getId(), fromAccount.getUserId()));

        sendKafkaMessages(transactionEvent);
    }

    private AccountResponse getAccountResponse(Long accountId) {
        ResponseEntity<AccountResponse> response = accountClient.getAccount(accountId);
        if (!response.getStatusCode().is2xxSuccessful()) {
            logger.error("Failed to retrieve account information for account ID {}", accountId);
            throw new RuntimeException("Failed to retrieve account information.");
        }
        return response.getBody();
    }

    private void updateAccount(AccountResponse account) {
        accountClient.updateAccount(account.getId(), Mapper.convertAccountResponseToRequest(account));
        logger.info("Updated account ID {} successfully", account.getId());
    }

    private void sendKafkaMessages(TransactionEvent transactionEvent) {
        try {
            kafkaTemplate.send(TRANSACTION_EVENT_TOPIC, transactionEvent);
            logger.info("Processed Kafka topic successfully for accounts {} and {}", transactionEvent.getFromAccountId(), transactionEvent.getToAccountId());
        } catch (Exception e) {
            logger.error("Error occurred while producing messages for accounts {} and {}", transactionEvent.getFromAccountId(), transactionEvent.getToAccountId(), e);
            throw new RuntimeException("Failed to update account in the account service.", e);
        }
    }



    private Transaction saveTransaction(String type, double amount, Long fromAccountId, Long toAccountId, Long userId) {
        logger.info("Going to save the transaction in the DB");

        TRANSACTION_TYPE transactionType = TRANSACTION_TYPE.valueOf(type);

        if (!transactionType.requiresToAccountId()) {
            toAccountId = null;
        }

        Transaction transaction = new Transaction();
        transaction.setTransactionType(transactionType);
        transaction.setAmount(amount);
        transaction.setFromAccountId(fromAccountId);
        transaction.setToAccountId(toAccountId);
        transaction.setUserId(userId);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setStatus(TRANSACTION_STATUS.SUCCESS);
        transactionRepository.save(transaction);

        logger.info("Saved transaction successfully");
        return transaction;
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public void withdraw(WithdrawRequest withdrawRequest) {
        AccountResponse account = accountClient.getAccount(withdrawRequest.getAccountId()).getBody();

        if (account.getBalance() < withdrawRequest.getAmount()) {
            throw new RuntimeException("Insufficient funds in the account.");
        }

        account.setBalance(account.getBalance() - withdrawRequest.getAmount());
        accountClient.updateAccount(account.getId(), Mapper.convertAccountResponseToRequest(account));

        TransactionEvent transactionEvent = Mapper.convertTransactionToTransactionEvent(saveTransaction("WITHDRAW", withdrawRequest.getAmount(), account.getId(), null, account.getUserId()));

        sendKafkaMessages(transactionEvent);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deposit(DepositRequest depositRequest) {
        AccountResponse account = accountClient.getAccount(depositRequest.getAccountId()).getBody();

        account.setBalance(account.getBalance() + depositRequest.getAmount());
        accountClient.updateAccount(account.getId(), Mapper.convertAccountResponseToRequest(account));

        TransactionEvent transactionEvent = Mapper.convertTransactionToTransactionEvent(
                saveTransaction("DEPOSIT", depositRequest.getAmount(), account.getId(), null, account.getUserId())
        );

        sendKafkaMessages(transactionEvent);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void fastCash(WithdrawRequest withdrawRequest) {
        AccountResponse account = accountClient.getAccount(withdrawRequest.getAccountId()).getBody();

        if (account.getBalance() < withdrawRequest.getAmount()) {
            throw new RuntimeException("Insufficient funds in the account.");
        }

        account.setBalance(account.getBalance() - withdrawRequest.getAmount());


        accountClient.updateAccount(account.getId(), Mapper.convertAccountResponseToRequest(account));


        TransactionEvent transactionEvent = Mapper.convertTransactionToTransactionEvent(
                saveTransaction("FASTCASH", withdrawRequest.getAmount(), account.getId(), null, account.getUserId())
        );


        sendKafkaMessages(transactionEvent);
    }



    @Override
    public List<Transaction> getStatement(Long accountId) {
        return transactionRepository.getLast10TransactionsByAccountId(accountId);
    }


}
