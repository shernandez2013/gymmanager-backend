package com.mycompany.gymmanager.service;


import com.mycompany.gymmanager.entity.BranchSettings;
import com.mycompany.gymmanager.repository.BranchSettingsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BranchSettingsServiceImpl implements BranchSettingsService {

    private final BranchSettingsRepository repository;

    public BranchSettingsServiceImpl(BranchSettingsRepository repository) {
        this.repository = repository;
    }

    @Override
    public BranchSettings create(BranchSettings settings) {
        return repository.save(settings);
    }

    @Override
    public List<BranchSettings> getAll() {
        return repository.findAll();
    }

    @Override
    public BranchSettings getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("BranchSettings not found: " + id));
    }

    @Override
    public BranchSettings update(Integer id, BranchSettings update) {
        BranchSettings existing = getById(id);
        BeanUtils.copyProperties(update, existing, "id");
        return repository.save(existing);
    }

    @Override
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("BranchSettings not found: " + id);
        }
        repository.deleteById(id);
    }
}

