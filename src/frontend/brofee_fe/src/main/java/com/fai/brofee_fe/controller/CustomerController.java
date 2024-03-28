package com.fai.brofee_fe.controller;

import com.fai.brofee_fe.dto.CommissionDTO;
import com.fai.brofee_fe.dto.TransactionDTO;
import com.fai.brofee_fe.dto.UserDetailDTO;
import com.fai.brofee_fe.entity.stored_procedure_entity.CommissionService_SP;
import com.fai.brofee_fe.entity.stored_procedure_entity.UserHierarchyItem_SP;
import com.fai.brofee_fe.entity.stored_procedure_entity.User_SP;
import com.fai.brofee_fe.repository.TransactionRepository;
import com.fai.brofee_fe.service.CommissionService;
import com.fai.brofee_fe.service.TransactionService;
import com.fai.brofee_fe.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {

    private final UserService userService;
    private final TransactionService transactionService;
    private final CommissionService commissionService;

    @GetMapping
    public String getCustomerPage(
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "") String searchTerm,
            Model model
    ) {
        List<User_SP> users = userService.getUsersWithPagination(size, page, searchTerm);
        model.addAttribute("customers", users);
        model.addAttribute("totalPages", users.get(0).getTotalPages() != null ? users.get(0).getTotalPages() : 1);
        model.addAttribute("totalElements", users.get(0).getTotalItems() != null ? users.get(0).getTotalItems() : 0);
        model.addAttribute("size", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("searchTerm", searchTerm);
        return "customer/list";
    }

    @GetMapping("/{code}")
    public String getCustomerDetailPage(
            @PathVariable String code,
            Model model
    ) {
        UserDetailDTO user = userService.getUserByCode(code);
        model.addAttribute("customer", user);

        // Get transaction list
        List<TransactionDTO> transactions = transactionService.getAllTransactionByCustomer(code);
        BigDecimal totalAmount = transactions.stream()
                .map(TransactionDTO::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        model.addAttribute("transactions", transactions);
        model.addAttribute("totalTransaction", totalAmount);

        // Get commission services unpaid this mount
        model.addAttribute("unpaidCommissions", transactionService.getUnpaidCommissionServiceCurrentMonth(code));

        // Get commission list
        List<CommissionDTO> commissions = commissionService.getCommissionByUser(code);
        BigDecimal totalCommission = commissions.stream()
                .map(CommissionDTO::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        model.addAttribute("commissions", commissions);
        model.addAttribute("totalCommission", totalCommission);

        // Get parent and children users
        List<UserHierarchyItem_SP> parentUsers = userService.getParentUsers(code);
        List<UserHierarchyItem_SP> childrenUsers = userService.getChildrenUsers(code);
        Set<UserHierarchyItem_SP> parentsAndChildrenUsers = Stream.concat(parentUsers.stream(), childrenUsers.stream()).collect(Collectors.toSet());
        // Sort the set by id
        parentsAndChildrenUsers = parentsAndChildrenUsers.stream()
                .sorted(Comparator.comparingLong(UserHierarchyItem_SP::getId))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        model.addAttribute("parents", parentUsers);
        model.addAttribute("children", childrenUsers);
        model.addAttribute("parentsAndChildren", parentsAndChildrenUsers);

        return "customer/detail";
    }

    @PostMapping("/create-commission-payment")
    public String createCommissionPayment(@ModelAttribute(name = "userCode") String userCode) {
        String a = userCode;
        commissionService.createCommissionPaymentForUser(userCode);
        return "redirect:/customer/" + userCode;
    }

}
