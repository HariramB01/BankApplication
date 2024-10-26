package com.bank.notification.Controller;


import com.bank.SharedDTO.Request.TransactionEvent;
import com.bank.notification.Service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    private NotificationService notificationService;
    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    public NotificationController(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "transaction-event", groupId = "notification-group")
    public void transactionNotification(TransactionEvent transactionEvent){
        logger.info("Transaction Event successfully received in notification service for {}.", transactionEvent.getTransactionId());
        String message = String.format("A transaction of $%.2f was made from Account ID %d to Account ID %d on %s.",
                transactionEvent.getAmount(), transactionEvent.getFromAccountId(), transactionEvent.getToAccountId(), transactionEvent.getTransactionDate());

        notificationService.sendEmail(message);

    }

}
