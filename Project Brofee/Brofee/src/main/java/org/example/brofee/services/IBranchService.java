package org.example.brofee.services;

import org.example.brofee.dto.BranchDto;
import org.example.brofee.entities.Branch;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IBranchService {
    List<Branch> getAllBranches();
    Optional<Branch> getBranchById(UUID id);
    public void save(BranchDto branchDto);
    public void update(UUID id, BranchDto branchDto);
    public void delete(UUID id);
}
