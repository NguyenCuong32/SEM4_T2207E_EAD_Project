package com.fai.brofee_fe.service;

import com.fai.brofee_fe.dto.CommissionDTO;
import com.fai.brofee_fe.dto.UserDTO;
import com.fai.brofee_fe.entity.Commission;
import com.fai.brofee_fe.entity.User;
import com.fai.brofee_fe.repository.CommissionRepository;
import com.fai.brofee_fe.repository.UserRepository;
import com.fai.brofee_fe.repository.stored_procedure.CommissionServiceRepository_SP;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public Boolean createCommissionPaymentForUser(String userCode) {
        Long id = userRepository.findByCode(userCode).map(User::getId).orElseThrow(() -> new RuntimeException("User not found"));
        // Get the start and end datetime of this month
        LocalDateTime start = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime end = LocalDateTime.now().withDayOfMonth(1).plusMonths(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        commissionRepository.createCommissionPaymentForUser(id, start, end);
        return true;
    }
}
