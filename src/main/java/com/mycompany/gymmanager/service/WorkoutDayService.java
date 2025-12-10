package com.mycompany.gymmanager.service;

import com.mycompany.gymmanager.entity.WorkoutDay;

import java.util.List;
import java.util.UUID;

public interface WorkoutDayService {

    WorkoutDay create(WorkoutDay workoutDay);

    List<WorkoutDay> getAll();

    WorkoutDay getById(UUID id);

    WorkoutDay update(UUID id, WorkoutDay update);

    void delete(UUID id);
}
