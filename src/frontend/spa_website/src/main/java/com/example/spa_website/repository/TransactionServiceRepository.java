package com.example.spa_website.repository;

import com.example.spa_website.entity.TransactionService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionServiceRepository extends JpaRepository<TransactionService, Long> {
}
