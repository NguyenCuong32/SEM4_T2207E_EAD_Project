package org.example.brofee.services;


import org.example.brofee.dto.FormulaDto;
import org.example.brofee.entities.Formula;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IFormulaService {
    public List<Formula> getAllFormulas();
    Optional<Formula> getFormulaById(UUID id);
    public void save(FormulaDto formulaDto);
    public void update(UUID id, FormulaDto formulaDto);
    public void delete(UUID id);
}
