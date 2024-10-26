package com.bank.transaction.Controller;


import com.bank.transaction.Entity.Transaction;
import com.bank.transaction.Request.DepositRequest;
import com.bank.transaction.Request.TransferRequest;
import com.bank.transaction.Request.WithdrawRequest;
import com.bank.transaction.Service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody TransferRequest transferRequest) {
        transactionService.transfer(transferRequest);
        return ResponseEntity.status(HttpStatus.OK).body("Transfer successful");
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestBody DepositRequest depositRequest) {
        transactionService.deposit(depositRequest);
        return ResponseEntity.status(HttpStatus.OK).body("Deposit successful");
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestBody WithdrawRequest withdrawRequest) {
        transactionService.withdraw(withdrawRequest);
        return ResponseEntity.status(HttpStatus.OK).body("Withdrawal successful");
    }

    @PostMapping("/fastCash")
    public ResponseEntity<String> fastCash(@RequestBody WithdrawRequest withdrawRequest){
        transactionService.fastCash(withdrawRequest);
        return ResponseEntity.status(HttpStatus.OK).body("Fast Cash Withdrawal Successful");
    }

    @GetMapping("/statement/{accountId}")
    public ResponseEntity<List<Transaction>> statement(@PathVariable Long accountId) {
        List<Transaction> statement = transactionService.getStatement(accountId);
        if (statement.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(statement);
    }
}
