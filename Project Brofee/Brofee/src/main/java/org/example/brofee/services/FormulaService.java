package org.example.brofee.services;

import lombok.RequiredArgsConstructor;
import org.example.brofee.dto.FormulaDto;
import org.example.brofee.entities.Formula;
import org.example.brofee.repositories.IFormulaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FormulaService implements IFormulaService{
    private final IFormulaRepository iFormulaRepository;
    @Override
    public List<Formula> getAllFormulas() {
        return iFormulaRepository.findAll();
    }

    @Override
    public Optional<Formula> getFormulaById(UUID id) {
        return iFormulaRepository.findById(id);
    }

    @Override
    public void save(FormulaDto formulaDto) {
        Formula formula = new Formula();
        formula.setName(formulaDto.getName());
        formula.setDescription(formulaDto.getDescription());
        formula.setStatus(formulaDto.getStatus());
        iFormulaRepository.save(formula);
    }

    @Override
    public void update(UUID id, FormulaDto formulaDto) {
        Optional<Formula> existingFormulaOptional = getFormulaById(id);
        if(existingFormulaOptional.isPresent()){
            Formula existingFormula = existingFormulaOptional.get();
            existingFormula.setName(formulaDto.getName());
            existingFormula.setDescription(formulaDto.getDescription());
            existingFormula.setStatus(formulaDto.getStatus());
            iFormulaRepository.saveAndFlush(existingFormula);
        }
    }

    @Override
    public void delete(UUID id) {
        iFormulaRepository.deleteById(id);
    }
}
