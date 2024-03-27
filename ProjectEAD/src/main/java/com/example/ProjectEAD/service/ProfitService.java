package com.example.ProjectEAD.service;

import com.example.ProjectEAD.entity.Profit;
import com.example.ProjectEAD.repository.IProfitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfitService implements IProfitService{
    private final IProfitRepository profitRepository;

    @Autowired
    public ProfitService(IProfitRepository profitRepository) {
        this.profitRepository = profitRepository;
    }

    @Override
    public List<Profit> getAllProfit() {
        return profitRepository.findAll();
    }

    @Override
    public Optional<Profit> getProfitById(Integer id) {
        return profitRepository.findById(id);
    }

    @Override
    public void add(Profit profit) {
        profitRepository.save(profit);
    }

    @Override
    public void deleteById(Integer id) {
        profitRepository.deleteById(id);
    }
}
