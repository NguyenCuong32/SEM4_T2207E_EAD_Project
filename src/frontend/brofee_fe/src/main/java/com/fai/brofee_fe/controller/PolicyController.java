package com.fai.brofee_fe.controller;

import com.fai.brofee_fe.dto.*;
import com.fai.brofee_fe.service.CommissionPolicyService;
import com.fai.brofee_fe.service.ServiceService_hung;
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
    private final ServiceService_hung serviceService_hung;

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
        return "policy/list";
    }

    @GetMapping("/create")
    public String createPolicyPage(Model model) {
        List<ServiceDetailDTO_hung> allServices = serviceService_hung.getAllServices();
        LocalDateTime defaultStartDate = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);;
        LocalDateTime defaultEndDate = defaultStartDate.withMonth(defaultStartDate.getMonthValue() + 1).withDayOfMonth(1).withHour(23).withMinute(59).withSecond(59).minusNanos(1);

        for (ServiceDetailDTO_hung service : allServices) {
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
            @ModelAttribute("policy") CommissionPolicyCreateDTO policyCreateDTO,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("services", serviceService_hung.getAllServices());
            return "policy/add";
        }
        try {
            commissionPolicyService.createCommissionPolicy(policyCreateDTO);
        } catch (Exception e) {
            model.addAttribute("services", serviceService_hung.getAllServices());
            model.addAttribute("error", e.getMessage());
            return "policy/add";
        }
        return "redirect:/policy";
    }

    @GetMapping("/services")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<ServiceDetailDTO_hung> getServices(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDate
    ) {
        List<ServiceDetailDTO_hung> allServices = serviceService_hung.getAllServices();

        for (ServiceDetailDTO_hung service : allServices) {
            service.setConflicted(hasPolicyConflict(service, startDate, endDate));
        }
        return allServices;
    }

    private boolean hasPolicyConflict(ServiceDetailDTO_hung service, LocalDateTime startDate, LocalDateTime endDate) {
        return service.getPolicyAssignments().stream()
                .map(PolicyServiceAssignmentDTO::getPolicy)
                .anyMatch(policy -> {
                    // Adjust logic for conflict detection as needed
                    return !policy.getEndDate().isBefore(startDate) &&
                            !policy.getStartDate().isAfter(endDate);
                });
    }
}
