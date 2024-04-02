package com.fai.brofee_fe.service;

import com.fai.brofee_fe.dto.*;
import com.fai.brofee_fe.entity.CommissionPolicy;
import com.fai.brofee_fe.entity.CommissionTier;
import com.fai.brofee_fe.entity.ServicePolicyAssignment;
import com.fai.brofee_fe.repository.CommissionPolicyRepository;
import com.fai.brofee_fe.repository.CommissionTierRepository;
import com.fai.brofee_fe.repository.ServicePolicyAssignmentRepository;
import com.fai.brofee_fe.repository.ServiceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommissionPolicyService {

    private final CommissionPolicyRepository commissionPolicyRepository;
    private final ServiceRepository serviceRepository;
    private final ServicePolicyAssignmentRepository servicePolicyAssignmentRepository;
    private final CommissionTierRepository commissionTierRepository;
    private final ModelMapper modelMapper;

    public Page<CommissionPolicyDTO> getCommissionPolicies(Pageable pageable) {
        Page<CommissionPolicy> commissionPolicies = commissionPolicyRepository.findAll(pageable);
        List<CommissionPolicyDTO> policyDTOList = commissionPolicies
                .stream()
                .map(commissionPolicy -> modelMapper.map(commissionPolicy, CommissionPolicyDTO.class))
                .toList();
        LocalDateTime now = LocalDateTime.now();
        policyDTOList.forEach(policyDTO -> {
            policyDTO.setTotalRate(policyDTO.getCommissionTiers().stream().map(CommissionTierDTO::getCommissionRate).reduce(BigDecimal.ZERO, BigDecimal::add));

            if (policyDTO.getEndDate() != null && policyDTO.getEndDate().isBefore(now)) {
                policyDTO.setStatus(PolicyStatus.EXPIRED);
            } else if (policyDTO.getStartDate().isAfter(now)) {
                policyDTO.setStatus(PolicyStatus.PENDING);
            } else {
                policyDTO.setStatus(PolicyStatus.ACTIVE);
            }

        });
        return new PageImpl<>(policyDTOList, pageable, commissionPolicies.getTotalElements());
    }

    public CommissionPolicyDTO getCommissionPolicy(Long id) {
        CommissionPolicy commissionPolicy = commissionPolicyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Policy not found"));
        commissionPolicy.getPolicyAssignments().forEach(ServicePolicyAssignment::getService);
        return modelMapper.map(commissionPolicy, CommissionPolicyDTO.class);
    }

    @Transactional
    public void createCommissionPolicy(CommissionPolicyCreateDTO createDTO) {
        // 1 - Check if sum or rates is less than 100
        BigDecimal totalRate = createDTO.getCommissionTiers().stream().map(CommissionTierCreateDTO::getCommissionRate).limit(createDTO.getMaxReferralLevels()).reduce(BigDecimal.ZERO, BigDecimal::add);
        if (totalRate.compareTo(BigDecimal.valueOf(100)) > 0) {
            throw new IllegalArgumentException("Total commission rate cannot exceed 100%");
        }

        // 2 - Crate new policy
        CommissionPolicy commissionPolicy = CommissionPolicy.builder()
                .code(generatePolicyCode(createDTO))
                .startDate(createDTO.getStartDate())
                .endDate(createDTO.getEndDate())
                .maxReferralLevels(createDTO.getMaxReferralLevels())
                .build();
        commissionPolicyRepository.save(commissionPolicy);

        // 3 - Create commission tiers
        List<CommissionTier> commissionTiers = createDTO.getCommissionTiers().stream()
                .map(tierDTO -> CommissionTier.builder()
                        .policy(commissionPolicy)
                        .level(tierDTO.getLevel())
                        .commissionRate(tierDTO.getCommissionRate())
                        .build())
                .limit(createDTO.getMaxReferralLevels())
                .toList();
        commissionTierRepository.saveAll(commissionTiers);


        // 4 - Assign policy to services
        List<com.fai.brofee_fe.entity.Service> services = serviceRepository.findAllById(createDTO.getServiceIds());
        List<ServicePolicyAssignment> servicePolicyAssignments = services.stream()
                .map(service -> ServicePolicyAssignment.builder()
                        .service(service)
                        .policy(commissionPolicy)
                        .build())
                .toList();
        servicePolicyAssignmentRepository.saveAll(servicePolicyAssignments);
    }

    @Transactional
    public void updateCommissionPolicy(CommissionPolicyEditDTO updateDTO) {
        CommissionPolicy commissionPolicy = commissionPolicyRepository.findById(updateDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Policy not found"));

        // 1 - Check if the found commission policy is active, if it is then only update end date
        if (commissionPolicy.getStartDate().isBefore(LocalDateTime.now()) && commissionPolicy.getEndDate().isAfter(LocalDateTime.now())) {
            commissionPolicy.setEndDate(updateDTO.getEndDate());
            commissionPolicyRepository.save(commissionPolicy);
            return;
        }


        // 2 - Check if sum or rates is less than 100
        BigDecimal totalRate = updateDTO.getCommissionTiers().stream().map(CommissionTierCreateDTO::getCommissionRate).limit(commissionPolicy.getMaxReferralLevels()).reduce(BigDecimal.ZERO, BigDecimal::add);
        if (totalRate.compareTo(BigDecimal.valueOf(100)) > 0) {
            throw new IllegalArgumentException("Total commission rate cannot exceed 100%");
        }

        // 3 - Update policy
        commissionPolicy.setStartDate(updateDTO.getStartDate());
        commissionPolicy.setEndDate(updateDTO.getEndDate());
        commissionPolicy.setMaxReferralLevels(updateDTO.getMaxReferralLevels());
        // Update code if start date or max levels changed
        if (!commissionPolicy.getStartDate().isEqual(updateDTO.getStartDate()) || !commissionPolicy.getMaxReferralLevels().equals(updateDTO.getMaxReferralLevels())) {
            commissionPolicy.setCode(generatePolicyCodeWhenEdit(updateDTO));
        }
        commissionPolicyRepository.save(commissionPolicy);

        // 4 - Delete all the old commission tiers
        List<CommissionTier> _commissionTiers = commissionPolicy.getCommissionTiers();
        //commissionTierRepository.deleteAll(_commissionTiers);
        // The deleteAll did not work
        for (CommissionTier _commissionTier : _commissionTiers) {
            commissionTierRepository.delete(_commissionTier);
        }


        // 5 - Create new commission tiers
        List<CommissionTier> commissionTiers = updateDTO.getCommissionTiers().stream()
                .map(tierDTO -> CommissionTier.builder()
                        .policy(commissionPolicy)
                        .level(tierDTO.getLevel())
                        .commissionRate(tierDTO.getCommissionRate())
                        .build())
                .limit(updateDTO.getMaxReferralLevels())
                .toList();
        commissionTierRepository.saveAll(commissionTiers);

        // 5 - Update commission tiers
        List<ServicePolicyAssignment> existingAssignments = commissionPolicy.getPolicyAssignments();
        List<Long> existingServiceIds = existingAssignments.stream().map(assignment -> assignment.getService().getId()).toList();

        // Services to add (present in DTO but not existing assignments)
        List<Long> serviceIdsToAdd = updateDTO.getServiceIds().stream()
                .filter(id -> !existingServiceIds.contains(id))
                .toList();

        // Services to remove (existing assignments but not present in DTO)
        List<Long> serviceIdsToRemove = existingServiceIds.stream()
                .filter(id -> !updateDTO.getServiceIds().contains(id))
                .toList();

        // Create assignments for services to add
        List<ServicePolicyAssignment> assignmentsToAdd = serviceIdsToAdd.stream()
                .map(id -> ServicePolicyAssignment.builder()
                        .service(serviceRepository.findById(id).get()) // Assuming you have a method to find service by ID
                        .policy(commissionPolicy)
                        .build())
                .toList();

        // Delete assignments for services to remove
        servicePolicyAssignmentRepository.deleteAll(existingAssignments.stream()
                .filter(assignment -> serviceIdsToRemove.contains(assignment.getService().getId()))
                .toList());

        // Save new and updated assignments
        servicePolicyAssignmentRepository.saveAll(assignmentsToAdd);

    }

    private String generatePolicyCode(CommissionPolicyCreateDTO createDTO) {
        String prefix = "PLV-";
        String maxLevels = String.format("%02d", createDTO.getMaxReferralLevels()); // Pad with zeros to 2 digits
        String monthYear = DateTimeFormatter.ofPattern("MMyy").format(createDTO.getStartDate());
        String randomSuffix = UUID.randomUUID().toString().substring(0, 8);

        return prefix + maxLevels + "-" + monthYear + "-" + randomSuffix;
    }

    private String generatePolicyCodeWhenEdit(CommissionPolicyEditDTO editDTO) {
        String prefix = "PLV-";
        String maxLevels = String.format("%02d", editDTO.getMaxReferralLevels()); // Pad with zeros to 2 digits
        String monthYear = DateTimeFormatter.ofPattern("MMyy").format(editDTO.getStartDate());
        String randomSuffix = UUID.randomUUID().toString().substring(0, 8);

        return prefix + maxLevels + "-" + monthYear + "-" + randomSuffix;
    }
}
