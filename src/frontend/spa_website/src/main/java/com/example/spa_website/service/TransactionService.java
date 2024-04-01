package com.example.spa_website.service;

import com.example.spa_website.dto.TransactionDTO;
import com.example.spa_website.entity.Transaction;
import com.example.spa_website.entity.User;
import com.example.spa_website.repository.TransactionRepository;
import com.example.spa_website.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;


    public List<TransactionDTO> getAllTransactionByCustomer(String code) {
        Long id = userRepository.findByCode(code).map(User::getId).orElseThrow(() -> new RuntimeException("User not found"));
        List<Transaction> transactions = transactionRepository.findByCustomer_Id(id);
        return transactions.stream()
                .map(transaction -> modelMapper.map(transaction, TransactionDTO.class))
                .toList();
    }

}
