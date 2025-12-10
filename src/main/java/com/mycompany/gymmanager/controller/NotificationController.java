package com.mycompany.gymmanager.controller;


import com.mycompany.gymmanager.entity.Notification;
import com.mycompany.gymmanager.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<Notification> create(@RequestBody Notification notification) {
        Notification created = notificationService.create(notification);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<Notification>> getAll() {
        List<Notification> list = notificationService.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notification> getById(@PathVariable("id") UUID id) {
        Notification notification = notificationService.getById(id);
        return ResponseEntity.ok(notification);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notification> update(@PathVariable("id") UUID id, @RequestBody Notification update) {
        Notification updated = notificationService.update(id, update);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        notificationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/read")
    public ResponseEntity<Notification> markAsRead(@PathVariable("id") UUID id) {
        Notification updated = notificationService.markAsRead(id);
        return ResponseEntity.ok(updated);
    }
}

