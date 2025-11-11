// java
package com.mycompany.gymmanager.service;

import com.mycompany.gymmanager.entity.Branch;
import com.mycompany.gymmanager.exception.ResourceNotFoundException;
import com.mycompany.gymmanager.repository.BranchRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class BranchServiceImpl implements BranchService {

    private final BranchRepository repository;

    public BranchServiceImpl(BranchRepository repository) {
        this.repository = repository;
    }

    @Override
    public Branch createBranch(Branch branch) {
        branch.setId(null); // ensure new
        branch.setCreatedAt(LocalDateTime.now());
        branch.setUpdatedAt(LocalDateTime.now());
        return repository.save(branch);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Branch> getAllBranches() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Branch getBranchById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id: " + id));
    }

    @Override
    public Branch updateBranch(UUID id, Branch branch) {
        Branch existing = getBranchById(id);
        // update allowed fields
        existing.setName(branch.getName());
        existing.setAddress(branch.getAddress());
        existing.setCity(branch.getCity());
        existing.setState(branch.getState());
        existing.setCountry(branch.getCountry());
        existing.setPhone(branch.getPhone());
        existing.setUpdatedAt(LocalDateTime.now());
        return repository.save(existing);
    }

    @Override
    public void deleteBranch(UUID id) {
        Branch existing = getBranchById(id);
        repository.delete(existing);
    }
}

