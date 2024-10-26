package com.bank.notification.Service;


import com.bank.notification.Entity.Notification;
import com.bank.notification.Repository.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationServiceImpl implements NotificationService {

    private NotificationRepository notificationRepository;
    private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

    public NotificationServiceImpl(NotificationRepository notificationRepository){
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void sendEmail(String message) {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setNotificationTime(LocalDateTime.now());
        notificationRepository.save(notification);
        logger.info("Successfully saved notification in the DB");

    }
}
