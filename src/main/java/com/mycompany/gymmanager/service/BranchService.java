// java
package com.mycompany.gymmanager.service;

import com.mycompany.gymmanager.entity.Branch;

import java.util.List;
import java.util.UUID;

public interface BranchService {
    Branch createBranch(Branch branch);
    List<Branch> getAllBranches();
    Branch getBranchById(UUID id);
    Branch updateBranch(UUID id, Branch branch);
    void deleteBranch(UUID id);
}
