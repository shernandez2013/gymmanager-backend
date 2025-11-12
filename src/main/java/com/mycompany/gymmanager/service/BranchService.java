// java
package com.mycompany.gymmanager.service;

import com.mycompany.gymmanager.entity.Branch;

import java.util.List;

public interface BranchService {
    Branch createBranch(Branch branch);
    List<Branch> getAllBranches();
    Branch getBranchById(Integer id);
    Branch updateBranch(Integer id, Branch branch);
    void deleteBranch(Integer id);
}
