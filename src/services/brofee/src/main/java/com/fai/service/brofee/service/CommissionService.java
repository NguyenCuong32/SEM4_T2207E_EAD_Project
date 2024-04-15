package com.fai.service.brofee.service;

import com.fai.service.brofee.dto.CommissionDTO;
import com.fai.service.brofee.dto.UserDTO;
import com.fai.service.brofee.entity.Commission;
import com.fai.service.brofee.entity.User;
import com.fai.service.brofee.repository.CommissionRepository;
import com.fai.service.brofee.repository.UserRepository;
import com.fai.service.brofee.repository.stored_procedure.CommissionServiceRepository_SP;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
