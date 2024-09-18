package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Notification;
import com.example.demo.model.Scheduler;
import com.example.demo.model.User;
import com.example.demo.repository.NotificationRepository;
import com.example.demo.repository.SchedulerRepository;
import com.example.demo.repository.UserRepository;

@Service
public class NotificationService {
	@Autowired
    private NotificationRepository notificationRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SchedulerRepository schedulerRepository;

    public Notification createNotification(Notification notification, int userId) {
        try {
        	if(notification.getUser()==null) {
        		User user = userRepository.findById(userId).orElse(null);
            	if(user==null) {
            		throw new IllegalArgumentException("No user found with ID: "+userId);
            	}
            	notification.setUser(user);
        	}
        	if (notification.getScheduler() != null) { //if only scheduler id is passed, associate the scheduler object with the notification
                Scheduler scheduler = schedulerRepository.findById(notification.getScheduler().getSchedulerId())
                    .orElseThrow(() -> new IllegalArgumentException("No scheduler found with ID: " + notification.getScheduler().getSchedulerId()));
                notification.setScheduler(scheduler);
            }
            return notificationRepository.save(notification);
        } catch (Exception e) {
            throw new RuntimeException("Error while creating the notification: " + e.getMessage());
        }
    }

    public Notification getNotificationById(int notifId) {
        try {
            Optional<Notification> notification = notificationRepository.findById(notifId);
            if (notification.isPresent()) {
                return notification.get();
            } else {
                throw new IllegalArgumentException("Notification with ID " + notifId + " not found");
            }
        } catch (IllegalArgumentException e) {
            throw e;
        }
        catch (Exception e) {
            throw new RuntimeException("Error while retrieving notification: " + e.getMessage());
        }
    }

    public List<Notification> getAllNotifications() {
        try {
            return notificationRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error while retrieving all notifications: " + e.getMessage());
        }
    }

    public List<Notification> getNotificationsByUserId(int userId) {
        try {
            return notificationRepository.findByUserUserId(userId);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while retrieving notifications by user ID: " + e.getMessage());
        }
    }

    public Notification updateNotification(Notification notification) {
        try {
        	Notification existingNotification = notificationRepository.findById(notification.getNotifId())
                    .orElseThrow(() -> new IllegalArgumentException("Notification with ID " + notification.getNotifId() + " not found"));

                // Update only the fields that are provided in the updatedNotification
                if (notification.getNotifType() != null) {
                    existingNotification.setNotifType(notification.getNotifType());
                }
                if (notification.getMessage() != null) {
                    existingNotification.setMessage(notification.getMessage());
                }
                if (notification.getNotifDate() != null) {
                    existingNotification.setNotifDate(notification.getNotifDate());
                }
                if (notification.getReadStatus() != 0) { 
                    existingNotification.setReadStatus(notification.getReadStatus());
                }
                if (notification.getEmailStatus() != 0) { 
                    existingNotification.setEmailStatus(notification.getEmailStatus());
                }
                return notificationRepository.save(existingNotification);
            } catch (Exception e) {
                throw new RuntimeException("Error while updating notification: " + e.getMessage());
            }
    }

    public void deleteNotificationById(int notifId) {
        try {
            if (notificationRepository.existsById(notifId)) {
                notificationRepository.deleteById(notifId);
            } else {
                throw new IllegalArgumentException("Notification with ID " + notifId + " not found");
            }
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error while deleting notification: " + e.getMessage());
        }
    }
}
