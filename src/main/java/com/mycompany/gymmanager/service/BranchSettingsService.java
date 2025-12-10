package com.mycompany.gymmanager.service;

import com.mycompany.gymmanager.entity.BranchSettings;

import java.util.List;
import java.util.UUID;

public interface BranchSettingsService {
    BranchSettings create(BranchSettings settings);
    List<BranchSettings> getAll();
    BranchSettings getById(Integer id);
    BranchSettings update(Integer id, BranchSettings update);
    void delete(Integer id);
}

