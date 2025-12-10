package com.mycompany.gymmanager.controller;

import com.mycompany.gymmanager.entity.WorkoutDay;
import com.mycompany.gymmanager.service.WorkoutDayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/workout-days")
public class WorkoutDayController {

    private final WorkoutDayService workoutDayService;

    public WorkoutDayController(WorkoutDayService workoutDayService) {
        this.workoutDayService = workoutDayService;
    }

    @PostMapping
    public ResponseEntity<WorkoutDay> create(@RequestBody WorkoutDay workoutDay) {
        WorkoutDay created = workoutDayService.create(workoutDay);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<WorkoutDay>> getAll() {
        List<WorkoutDay> list = workoutDayService.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutDay> getById(@PathVariable("id") UUID id) {
        WorkoutDay workoutDay = workoutDayService.getById(id);
        return ResponseEntity.ok(workoutDay);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkoutDay> update(@PathVariable("id") UUID id, @RequestBody WorkoutDay update) {
        WorkoutDay updated = workoutDayService.update(id, update);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        workoutDayService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
