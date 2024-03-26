package org.example.brofee.repositories;

import org.example.brofee.entities.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IBranchRepository extends JpaRepository<Branch, UUID> {
     public Branch findBranchByName(String name);
}
