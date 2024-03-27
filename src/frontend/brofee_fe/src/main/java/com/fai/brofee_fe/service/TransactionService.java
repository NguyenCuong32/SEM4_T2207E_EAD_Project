package com.fai.brofee_fe.service;

import com.fai.brofee_fe.dto.TransactionCreateNewCustomerDTO;
import com.fai.brofee_fe.dto.TransactionCreateOldCustomerDTO;
import com.fai.brofee_fe.dto.TransactionDTO;
import com.fai.brofee_fe.entity.Role;
import com.fai.brofee_fe.entity.Service;
import com.fai.brofee_fe.entity.Transaction;
import com.fai.brofee_fe.entity.User;
import com.fai.brofee_fe.entity.stored_procedure_entity.CommissionService_SP;
import com.fai.brofee_fe.repository.*;
import com.fai.brofee_fe.repository.stored_procedure.CommissionServiceRepository_SP;
import jakarta.persistence.EntityNotFoundException;
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
    private final ServiceRepository serviceRepository;
    private final RoleRepository roleRepository;
    private final TransactionServiceRepository transactionServiceRepository;
    private final CommissionServiceRepository_SP commissionServiceRepositorySP;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;

    @Transactional
    public void createTransactionForNewCustomer(TransactionCreateNewCustomerDTO dto) {
        // Check phone number
        if (userRepository.findByPhone(dto.getPhone()).isPresent()) {
            throw new RuntimeException("There is an user with that phone number");
        }

        // Check referrer (broker)
        Optional<User> referrer = Optional.empty();
        if(!dto.getCodeOrPhone().isEmpty()) {
            referrer = userRepository.findByCodeOrPhone(dto.getCodeOrPhone(), dto.getCodeOrPhone());
            if (referrer.isEmpty()) {
                throw new RuntimeException("No referrer with the code/phone found");
            }
        }

        // Create new user object
        String code;
        do {
            code = generateCode();
        } while (userRepository.existsByCode(code));
        User user = User.builder()
                .code(code)
                .phone(dto.getPhone())
                .name(dto.getName())
                .email(dto.getEmail().isEmpty() ? null : dto.getEmail())
                .password(bCryptPasswordEncoder.encode("password"))
                .address(dto.getAddress().isEmpty() ? null : dto.getAddress())
                .referrer(referrer.orElse(null))
                .createdAt(LocalDateTime.now())
                .build();
        Role role = roleRepository.findByName("CUSTOMER").orElseThrow(
                () -> new EntityNotFoundException("Role with name 'CUSTOMER' does not exist.")
        );
        user.setRoles(Collections.singleton(role));
        userRepository.save(user);

        // Create transaction object
        BigDecimal totalPrice = BigDecimal.ZERO;
        Transaction transaction = Transaction.builder()
                .customer(user)
                .transactionDate(LocalDateTime.now())
                .total(totalPrice)
                .build();
        transactionRepository.save(transaction);

        List<com.fai.brofee_fe.entity.TransactionService> transactionServices = new ArrayList<>();
        for (Service service : serviceRepository.findByIdIn(dto.getServices())) {
            com.fai.brofee_fe.entity.TransactionService transactionService = com.fai.brofee_fe.entity.TransactionService.builder()
                    .transaction(transaction)
                    .service(service)
                    .price(service.getBasePrice())
                    .build();
            transactionServices.add(transactionService);
            totalPrice = totalPrice.add(service.getBasePrice());
        }
        transactionServiceRepository.saveAll(transactionServices);

        // Update transaction object
        transaction.setTransactionServices(transactionServices);
        transaction.setTotal(totalPrice);
        transactionRepository.save(transaction);
    }

    public void createTransactionForOldCustomer(TransactionCreateOldCustomerDTO dto) {
        // Get the user
        Optional<User> user = userRepository.findByPhone(dto.getPhone());
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        // Create transaction object
        BigDecimal totalPrice = BigDecimal.ZERO;
        Transaction transaction = Transaction.builder()
                .customer(user.get())
                .transactionDate(LocalDateTime.now())
                .total(totalPrice)
                .build();
        transactionRepository.save(transaction);

        List<com.fai.brofee_fe.entity.TransactionService> transactionServices = new ArrayList<>();
        for (Service service : serviceRepository.findByIdIn(dto.getServices())) {
            com.fai.brofee_fe.entity.TransactionService transactionService = com.fai.brofee_fe.entity.TransactionService.builder()
                    .transaction(transaction)
                    .service(service)
                    .price(service.getBasePrice())
                    .build();
            transactionServices.add(transactionService);
            totalPrice = totalPrice.add(service.getBasePrice());
        }
        transactionServiceRepository.saveAll(transactionServices);

        // Update transaction object
        transaction.setTransactionServices(transactionServices);
        transaction.setTotal(totalPrice);
        transactionRepository.save(transaction);
    }

    private String generateCode() {
        StringBuilder codeBuilder = new StringBuilder();
        Random random = new Random();

        // Ensure a mix of numbers and letters
        for (int i = 0; i < 6; i++) {
            if (random.nextBoolean()) {
                // Add a random uppercase letter
                codeBuilder.append((char) (random.nextInt(26) + 'A'));
            } else {
                // Add a random digit
                codeBuilder.append(random.nextInt(10));
            }
        }

        return codeBuilder.toString();
    }

    public List<TransactionDTO> getAllTransactionByDate(LocalDateTime start, LocalDateTime end) {
        List<Transaction> transactions = transactionRepository.findByTransactionDateBetween(start, end);
        return transactions.stream()
                .map(transaction -> modelMapper.map(transaction, TransactionDTO.class))
                .toList();
    }

    public List<TransactionDTO> getAllTransactionByCustomer(String code) {
        Long id = userRepository.findByCode(code).map(User::getId).orElseThrow(() -> new RuntimeException("User not found"));
        List<Transaction> transactions = transactionRepository.findByCustomer_Id(id);
        return transactions.stream()
                .map(transaction -> modelMapper.map(transaction, TransactionDTO.class))
                .toList();
    }

    @Transactional
    public List<CommissionService_SP> getUnpaidCommissionServiceCurrentMonth(String code) {
        Long id = userRepository.findByCode(code).map(User::getId).orElseThrow(() -> new RuntimeException("User not found"));
        // Get the start and end datetime of this month
        LocalDateTime start = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime end = LocalDateTime.now().withDayOfMonth(1).plusMonths(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        return commissionServiceRepositorySP.getUnpaidCommissionsByUserAndTime(id, start, end);
    }

}
