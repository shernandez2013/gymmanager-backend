package com.mycompany.gymmanager.dto;

public class WorkoutRequestDTO {

    private Integer branchId;
    private String name;
    private String description;
    private String level;

    public WorkoutRequestDTO() {
    }

    public WorkoutRequestDTO(Integer branchId, String name, String description, String level) {
        this.branchId = branchId;
        this.name = name;
        this.description = description;
        this.level = level;
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
