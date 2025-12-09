package com.mycompany.gymmanager.service;


import com.mycompany.gymmanager.entity.Notification;

import java.util.List;
import java.util.UUID;


public interface NotificationService {
    Notification create(Notification notification);
    List<Notification> getAll();
    Notification getById(UUID id);
    Notification update(UUID id, Notification update);
    void delete(UUID id);
    Notification markAsRead(UUID id);
}

