package com.todo.list.services;

import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

@Component
public class Notification {

    private String notification;


    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }
}
