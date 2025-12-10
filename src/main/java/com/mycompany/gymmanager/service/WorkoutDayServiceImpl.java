package com.mycompany.gymmanager.service;

import com.mycompany.gymmanager.entity.WorkoutDay;
import com.mycompany.gymmanager.repository.WorkoutDayRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class WorkoutDayServiceImpl implements WorkoutDayService {

    private final WorkoutDayRepository repository;

    public WorkoutDayServiceImpl(WorkoutDayRepository repository) {
        this.repository = repository;
    }


    @Override
    public WorkoutDay create(WorkoutDay workoutDay) {
        return repository.save(workoutDay);
    }

    @Override
    public List<WorkoutDay> getAll() {
        return repository.findAll();
    }

    @Override
    public WorkoutDay getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workout day not found: " + id));
    }

    @Override
    public WorkoutDay update(UUID id, WorkoutDay update) {
        WorkoutDay existing = getById(id);
        BeanUtils.copyProperties(update, existing, "id");
        return repository.save(existing);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("WorkoutDay not found: " + id);
        }
        repository.deleteById(id);
    }
}
