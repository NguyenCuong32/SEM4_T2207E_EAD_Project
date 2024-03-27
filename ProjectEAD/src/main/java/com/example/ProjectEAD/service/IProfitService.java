package com.example.ProjectEAD.service;

import com.example.ProjectEAD.entity.Profit;

import java.util.List;
import java.util.Optional;

public interface IProfitService {
    List<Profit> getAllProfit();
    Optional<Profit> getProfitById(Integer id);
    void add(Profit profit);
    void deleteById(Integer id);
}
