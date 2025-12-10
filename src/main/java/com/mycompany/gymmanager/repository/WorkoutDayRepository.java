package com.mycompany.gymmanager.repository;

import com.mycompany.gymmanager.entity.WorkoutDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkoutDayRepository extends JpaRepository<WorkoutDay, UUID> {
}
