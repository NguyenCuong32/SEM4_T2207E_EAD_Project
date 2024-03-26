package org.example.brofee.services;

import lombok.RequiredArgsConstructor;
import org.example.brofee.dto.BranchDto;
import org.example.brofee.entities.Branch;
import org.example.brofee.repositories.IBranchRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BranchService implements IBranchService{
    private final IBranchRepository iBranchRepository;
    @Override
    public List<Branch> getAllBranches() {
        return iBranchRepository.findAll();
    }

    @Override
    public Optional<Branch> getBranchById(UUID id) {
        return iBranchRepository.findById(id);
    }

    @Override
    public void save(BranchDto branchDto) {
        Branch branch = new Branch();
        branch.setName(branchDto.getName());
        branch.setAddress(branchDto.getAddress());
        branch.setPhone(branchDto.getPhone());
        branch.setEmail(branchDto.getEmail());
        branch.setStatus(branchDto.getStatus());
        iBranchRepository.save(branch);
    }

    @Override
    public void update(UUID id, BranchDto branchDto) {
        Optional<Branch> existingBranchOptional = getBranchById(id);
        if(existingBranchOptional.isPresent()){
            Branch existingBranch = existingBranchOptional.get();
            existingBranch.setName(branchDto.getName());
            existingBranch.setAddress(branchDto.getAddress());
            existingBranch.setPhone(branchDto.getPhone());
            existingBranch.setEmail(branchDto.getEmail());
            existingBranch.setStatus(branchDto.getStatus());
            iBranchRepository.saveAndFlush(existingBranch);
        }
    }

    @Override
    public void delete(UUID id) {
        iBranchRepository.deleteById(id);
    }
}
