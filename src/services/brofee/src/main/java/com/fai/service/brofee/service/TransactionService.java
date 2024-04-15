package com.fai.service.brofee.service;

import com.fai.service.brofee.dto.TransactionDTO;
import com.fai.service.brofee.entity.Transaction;
import com.fai.service.brofee.entity.User;
import com.fai.service.brofee.repository.TransactionRepository;
import com.fai.service.brofee.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

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
