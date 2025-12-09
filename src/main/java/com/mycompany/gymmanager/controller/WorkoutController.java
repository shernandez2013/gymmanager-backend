package com.mycompany.gymmanager.controller;

import com.mycompany.gymmanager.dto.WorkoutRequestDTO;
import com.mycompany.gymmanager.dto.WorkoutResponseDTO;
import com.mycompany.gymmanager.service.WorkoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping
    public ResponseEntity<WorkoutResponseDTO> create(@RequestBody WorkoutRequestDTO dto) {
        return ResponseEntity.ok(workoutService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<WorkoutResponseDTO>> getAll() {
        return ResponseEntity.ok(workoutService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutResponseDTO> getById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(workoutService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkoutResponseDTO> update(@PathVariable("id") UUID id, @RequestBody WorkoutRequestDTO dto) {
        return ResponseEntity.ok(workoutService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        workoutService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
