package com.fai.brofee_fe.controller;

import com.fai.brofee_fe.dto.*;
import com.fai.brofee_fe.service.CommissionPolicyService;
import com.fai.brofee_fe.service.ServiceInPolicyService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/policy")
@AllArgsConstructor
public class PolicyController {

    private final CommissionPolicyService commissionPolicyService;
    private final ServiceInPolicyService serviceInPolicyService_;

    @GetMapping
    public String policyPage(
            Model model,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("startDate").descending());
        Page<CommissionPolicyDTO> policies = commissionPolicyService.getCommissionPolicies(pageable);
        model.addAttribute("policies", policies);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", policies.getTotalPages());

        model.addAttribute("services", serviceInPolicyService_.getAllServices());
        return "policy/list";
    }

    @GetMapping("/create")
    public String createPolicyPage(Model model) {
        List<ServiceDetailDTO> allServices = serviceInPolicyService_.getAllServices();
        LocalDateTime defaultStartDate = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);;
        LocalDateTime defaultEndDate = defaultStartDate.withMonth(defaultStartDate.getMonthValue() + 1).withDayOfMonth(1).withHour(23).withMinute(59).withSecond(59).minusNanos(1);

        for (ServiceDetailDTO service : allServices) {
            service.setConflicted(hasPolicyConflict(service, defaultStartDate, defaultEndDate));
        }

        model.addAttribute("services", allServices);

        CommissionPolicyCreateDTO policy = new CommissionPolicyCreateDTO();
        policy.setMaxReferralLevels(5);
        List<CommissionTierCreateDTO> tiers = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            tiers.add(new CommissionTierCreateDTO(i, BigDecimal.ZERO)); // Set initial rate to 0
        }
        policy.setCommissionTiers(tiers);
        model.addAttribute("policy", policy);

        return "policy/add";
    }

    @PostMapping("/create")
    public String createPolicy(
            @ModelAttribute("policy") @Valid CommissionPolicyCreateDTO policyCreateDTO,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("services", serviceInPolicyService_.getAllServices());
            return "policy/add";
        }
        try {
            commissionPolicyService.createCommissionPolicy(policyCreateDTO);
        } catch (Exception e) {
            model.addAttribute("services", serviceInPolicyService_.getAllServices());
            model.addAttribute("error", e.getMessage());
            return "policy/add";
        }
        return "redirect:/policy";
    }

    // Make excludePolicyId optional
    @GetMapping("/services")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<ServiceDetailDTO> getServices(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDate,
            @RequestParam(value = "excludePolicyId", required = false) Long excludePolicyId
    ) {
        List<ServiceDetailDTO> allServices = serviceInPolicyService_.getAllServices();

        for (ServiceDetailDTO service : allServices) {
            if (excludePolicyId != null)
                service.setConflicted(hasPolicyConflictWhenEdit(service, startDate, endDate, excludePolicyId));
            else
                service.setConflicted(hasPolicyConflict(service, startDate, endDate));
        }
        return allServices;
    }

    @GetMapping("/edit/{id}")
    public String editPolicyPage(@PathVariable Long id, Model model) {
        CommissionPolicyDTO policy = commissionPolicyService.getCommissionPolicy(id);
        CommissionPolicyEditDTO policyEdit = CommissionPolicyEditDTO.builder()
                .id(policy.getId())
                .startDate(policy.getStartDate())
                .endDate(policy.getEndDate())
                .maxReferralLevels(policy.getMaxReferralLevels())
                .serviceIds(policy.getPolicyAssignments().stream().map(assignment -> assignment.getService().getId()).toList())
                .build();
        List<CommissionTierCreateDTO> tiers = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            if (i <= policy.getCommissionTiers().size()) {
                tiers.add(new CommissionTierCreateDTO(i, policy.getCommissionTiers().get(i - 1).getCommissionRate()));
            } else {
                tiers.add(new CommissionTierCreateDTO(i, BigDecimal.ZERO)); // Set initial rate to 0
            }
        }
        policyEdit.setCommissionTiers(tiers);
        model.addAttribute("policy", policyEdit);

        // Check policy's status
        if (policy.getEndDate().isBefore(LocalDateTime.now())) {
            model.addAttribute("status", "expired");
        } else if (policy.getStartDate().isAfter(LocalDateTime.now())) {
            model.addAttribute("status", "pending");
        } else {
            model.addAttribute("status", "active");
        }

        // Pass services to view
        List<ServiceDetailDTO> allServices = serviceInPolicyService_.getAllServices();
        for (ServiceDetailDTO service : allServices) {
            service.setConflicted(hasPolicyConflictWhenEdit(service, policy.getStartDate(), policy.getEndDate(), id));
        }
        model.addAttribute("services", allServices);

        return "policy/edit";
    }

    @PostMapping("/edit")
    public String editPolicy(
            @ModelAttribute("policy") @Valid CommissionPolicyEditDTO policyEditDTO,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            // Pass services to view
            List<ServiceDetailDTO> allServices = serviceInPolicyService_.getAllServices();
            for (ServiceDetailDTO service : allServices) {
                service.setConflicted(hasPolicyConflictWhenEdit(service, policyEditDTO.getStartDate(), policyEditDTO.getEndDate(), policyEditDTO.getId()));
            }
            model.addAttribute("services", allServices);
            return "policy/edit";
        }
        try {
            commissionPolicyService.updateCommissionPolicy(policyEditDTO);
        } catch (Exception e) {
            // Pass services to view
            List<ServiceDetailDTO> allServices = serviceInPolicyService_.getAllServices();
            for (ServiceDetailDTO service : allServices) {
                service.setConflicted(hasPolicyConflictWhenEdit(service, policyEditDTO.getStartDate(), policyEditDTO.getEndDate(), policyEditDTO.getId()));
            }
            model.addAttribute("services", allServices);
            model.addAttribute("error", e.getMessage());
            return "policy/edit";
        }
        return "redirect:/policy";
    }

    private boolean hasPolicyConflict(ServiceDetailDTO service, LocalDateTime startDate, LocalDateTime endDate) {
        return service.getPolicyAssignments().stream()
                .map(PolicyServiceAssignmentDTO::getPolicy)
                .anyMatch(policy -> {
                    // Adjust logic for conflict detection as needed
                    return !policy.getEndDate().isBefore(startDate) &&
                            !policy.getStartDate().isAfter(endDate);
                });
    }

    private boolean hasPolicyConflictWhenEdit(ServiceDetailDTO service, LocalDateTime startDate, LocalDateTime endDate, Long excludePolicyId) {
        return service.getPolicyAssignments().stream()
                .map(PolicyServiceAssignmentDTO::getPolicy)
                .filter(policy -> !policy.getId().equals(excludePolicyId))
                .anyMatch(policy -> {
                    // Adjust logic for conflict detection as needed
                    return !policy.getEndDate().isBefore(startDate) &&
                            !policy.getStartDate().isAfter(endDate);
                });
    }
}
