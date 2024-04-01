package com.example.spa_website.service;

import com.example.spa_website.dto.CommissionDTO;
import com.example.spa_website.dto.UserDTO;
import com.example.spa_website.entity.Commission;
import com.example.spa_website.entity.User;
import com.example.spa_website.repository.CommissionRepository;
import com.example.spa_website.repository.UserRepository;
import com.example.spa_website.repository.stored_procedure_repository.CommissionServiceRepository_SP;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CommissionService {

    private final CommissionRepository commissionRepository;
    private final CommissionServiceRepository_SP commissionServiceRepositorySP;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public List<CommissionDTO> getCommissionByUser(String userCode) {
        Long id = userRepository.findByCode(userCode).map(User::getId).orElseThrow(() -> new RuntimeException("User not found"));
        List<Commission> commissions = commissionRepository.findByRecipientId(id);
        List<CommissionDTO> commissionDTOs = commissions.stream().map(commission -> CommissionDTO.builder()
                .id(commission.getId())
                .recipient(modelMapper.map(commission.getRecipient(), UserDTO.class))
                .total(commission.getTotal())
                .status(commission.getStatus())
                .createdAt(commission.getCreatedAt())
                .commissionServices(
                        commissionServiceRepositorySP.getCommissionServiceByCommissionId(commission.getId())
                )
                .build()).toList();
        return commissionDTOs;
    }
}
