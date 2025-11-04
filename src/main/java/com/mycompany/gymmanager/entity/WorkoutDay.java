package com.mycompany.gymmanager.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "workout_days")
public class WorkoutDay {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    private Workout workout;

    @Column(name = "day_of_week", nullable = false)
    private String dayOfWeek;

    @Column(name = "exercise_name", nullable = false)
    private String exerciseName;

    private Integer sets;
    private Integer reps;
    private Integer durationMinutes;
    private String notes;

    @OneToMany(mappedBy = "workoutDay", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProgressLog> progressLogs = new ArrayList<>();

    public WorkoutDay() {
    }

    public WorkoutDay(UUID id, Workout workout, String dayOfWeek, String exerciseName, Integer sets, Integer reps,
                      Integer durationMinutes, String notes, List<ProgressLog> progressLogs) {
        this.id = id;
        this.workout = workout;
        this.dayOfWeek = dayOfWeek;
        this.exerciseName = exerciseName;
        this.sets = sets;
        this.reps = reps;
        this.durationMinutes = durationMinutes;
        this.notes = notes;
        this.progressLogs = progressLogs;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<ProgressLog> getProgressLogs() {
        return progressLogs;
    }

    public void setProgressLogs(List<ProgressLog> progressLogs) {
        this.progressLogs = progressLogs;
    }
}

