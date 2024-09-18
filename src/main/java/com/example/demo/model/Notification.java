package com.example.demo.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notif_id")
    private int notifId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "scheduler_id", nullable = true)
    private Scheduler scheduler;

    @Column(name = "notif_type", nullable = false)
    private String notifType;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "notif_date", nullable = false)
    private Date notifDate;

    @Column(name = "read_status", columnDefinition = "INT DEFAULT 0")
    private int readStatus;

    @Column(name = "email_status", columnDefinition = "INT DEFAULT 0")
    private int emailStatus;

    // Constructors
    public Notification() {
    }

    public Notification(User user, Scheduler scheduler, String notifType, String message, Date notifDate, int readStatus, int emailStatus) {
        this.user = user;
        this.scheduler = scheduler;
        this.notifType = notifType;
        this.message = message;
        this.notifDate = notifDate;
        this.readStatus = readStatus;
        this.emailStatus = emailStatus;
    }

    // Getters and Setters

    public int getNotifId() {
        return notifId;
    }

    public void setNotifId(int notifId) {
        this.notifId = notifId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public String getNotifType() {
        return notifType;
    }

    public void setNotifType(String notifType) {
        this.notifType = notifType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getNotifDate() {
        return notifDate;
    }

    public void setNotifDate(Date notifDate) {
        this.notifDate = notifDate;
    }

    public int getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(int readStatus) {
        this.readStatus = readStatus;
    }

    public int getEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(int emailStatus) {
        this.emailStatus = emailStatus;
    }
}

