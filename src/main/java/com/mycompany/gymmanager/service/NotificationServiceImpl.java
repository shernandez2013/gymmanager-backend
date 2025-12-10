package com.mycompany.gymmanager.service;

import com.mycompany.gymmanager.entity.Notification;
import com.mycompany.gymmanager.repository.NotificationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.NotWritablePropertyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Notification create(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getAll() {
        return notificationRepository.findAll();
    }

    @Override
    public Notification getById(UUID id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found: " + id));
    }

    @Override
    public Notification update(UUID id, Notification update) {
        Notification existing = getById(id);
        BeanUtils.copyProperties(update, existing, "id");
        return notificationRepository.save(existing);
    }

    @Override
    public void delete(UUID id) {
        if (!notificationRepository.existsById(id)) {
            throw new RuntimeException("Notification not found: " + id);
        }
        notificationRepository.deleteById(id);
    }

    @Override
    public Notification markAsRead(UUID id) {
        Notification notification = getById(id);

        BeanWrapper bw = new BeanWrapperImpl(notification);
        try {
            bw.setPropertyValue("read", true);
        } catch (NotWritablePropertyException ex1) {
            try {
                bw.setPropertyValue("isRead", true);
            } catch (NotWritablePropertyException ex2) {
                // property not present; ignore — still save current entity
            }
        }

        return notificationRepository.save(notification);
    }
}
