package com.mycompany.gymmanager.service;


import com.mycompany.gymmanager.dto.*;
import com.mycompany.gymmanager.mapper.WorkoutMapper;
import com.mycompany.gymmanager.entity.*;
import com.mycompany.gymmanager.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final BranchRepository branchRepository;
    private final WorkoutMapper workoutMapper;

    public WorkoutService(
            WorkoutRepository workoutRepository,
            BranchRepository branchRepository,
            WorkoutMapper workoutMapper
    ) {
        this.workoutRepository = workoutRepository;
        this.branchRepository = branchRepository;
        this.workoutMapper = workoutMapper;
    }

    public WorkoutResponseDTO create(WorkoutRequestDTO dto) {
        Branch branch = branchRepository.findById(dto.getBranchId())
                .orElseThrow(() -> new RuntimeException("Branch not found"));

        Workout workout = new Workout();
        workout.setBranch(branch);
        workout.setName(dto.getName());
        workout.setDescription(dto.getDescription());
        workout.setLevel(dto.getLevel());
        workout.setCreatedAt(LocalDateTime.now());
        workout.setUpdatedAt(LocalDateTime.now());

        return workoutMapper.toDTO(workoutRepository.save(workout));
    }

    public List<WorkoutResponseDTO> getAll() {
        return workoutMapper.toDTOList(workoutRepository.findAll());
    }

    public WorkoutResponseDTO getById(UUID id) {
        Workout workout = workoutRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workout not found"));
        return workoutMapper.toDTO(workout);
    }

    public WorkoutResponseDTO update(UUID id, WorkoutRequestDTO dto) {
        Workout workout = workoutRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workout not found"));

        if (dto.getBranchId() != null) {
            Branch branch = branchRepository.findById(dto.getBranchId())
                    .orElseThrow(() -> new RuntimeException("Branch not found"));
            workout.setBranch(branch);
        }

        workout.setName(dto.getName());
        workout.setDescription(dto.getDescription());
        workout.setLevel(dto.getLevel());
        workout.setUpdatedAt(java.time.LocalDateTime.now());

        return workoutMapper.toDTO(workoutRepository.save(workout));
    }

    public void delete(UUID id) {
        workoutRepository.deleteById(id);
    }
}

