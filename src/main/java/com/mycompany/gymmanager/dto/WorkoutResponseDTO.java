package com.mycompany.gymmanager.dto;

import java.util.UUID;

public class WorkoutResponseDTO {

    private UUID id;
    private Integer branchId;
    private String name;
    private String description;
    private String level;


    public WorkoutResponseDTO() {
    }

    public WorkoutResponseDTO(UUID id, Integer branchId, String name, String description, String level) {
        this.id = id;
        this.branchId = branchId;
        this.name = name;
        this.description = description;
        this.level = level;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}