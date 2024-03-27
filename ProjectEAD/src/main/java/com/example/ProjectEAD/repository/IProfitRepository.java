package com.example.ProjectEAD.repository;

import com.example.ProjectEAD.entity.Profit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProfitRepository extends JpaRepository<Profit, Integer> {
}
