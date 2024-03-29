package org.example.brofee.repositories;

import org.example.brofee.entities.Formula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IFormulaRepository extends JpaRepository<Formula, UUID> {
    public Formula findFormulaByName(String name);
}
