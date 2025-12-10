package com.mycompany.gymmanager.repository;

import com.mycompany.gymmanager.entity.BranchSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchSettingsRepository extends JpaRepository<BranchSettings, Integer> {
}

