package com.mycompany.gymmanager.repository;

import com.mycompany.gymmanager.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkoutRepository extends JpaRepository<Workout, UUID> {
}

