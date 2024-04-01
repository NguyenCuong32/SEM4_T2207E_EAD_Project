package com.example.spa_website.controller;

import com.example.spa_website.dto.CommissionDTO;
import com.example.spa_website.dto.TransactionDTO;
import com.example.spa_website.dto.UserEditDTO;
import com.example.spa_website.entity.CustomUserDetail;
import com.example.spa_website.entity.User;
import com.example.spa_website.entity.stored_procedure_entity.UserHierarchyItem_SP;
import com.example.spa_website.service.CommissionService;
import com.example.spa_website.service.TransactionService;
import com.example.spa_website.service.UserService;
import jakarta.persistence.EntityExistsException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    private final UserService userService;
    private final TransactionService transactionService;
    private final CommissionService commissionService;

    @GetMapping
    public String profilePage(
            Model model,
            Authentication authentication
    ) {
        CustomUserDetail userDetail = (CustomUserDetail) authentication.getPrincipal();
        User user = userDetail.getUser();
        UserEditDTO userEditDTO = userService.getUserEditDTOById(user.getId());
        model.addAttribute("user", userEditDTO);

        // Get parent and children users
        List<UserHierarchyItem_SP> parentUsers = userService.getParentUsers(user.getCode());
        List<UserHierarchyItem_SP> childrenUsers = userService.getChildrenUsers(user.getCode());
        Set<UserHierarchyItem_SP> parentsAndChildrenUsers = Stream.concat(parentUsers.stream(), childrenUsers.stream()).collect(Collectors.toSet());
        // Sort the set by id
        parentsAndChildrenUsers = parentsAndChildrenUsers.stream()
                .sorted(Comparator.comparingLong(UserHierarchyItem_SP::getId))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        model.addAttribute("parentsAndChildren", parentsAndChildrenUsers);
        model.addAttribute("phone", user.getPhone());


        return "account-profile";
    }

    @PostMapping("/update-profile")
    public String updateProfile(
            @ModelAttribute("user") @Valid UserEditDTO userEditDTO,
            BindingResult bindingResult,
            Model model,
            Authentication authentication
    ) {
        if (bindingResult.hasErrors()) {
            return "account-profile";
        }

        try {
            CustomUserDetail userDetail = (CustomUserDetail) authentication.getPrincipal();
            User user = userDetail.getUser();
            userService.updateUser(user.getId(), userEditDTO);
        } catch (EntityExistsException e) {
            model.addAttribute("error", e.getMessage());
            return "account-profile";
        } catch (Exception e) {
            model.addAttribute("error", "Something wrong. Please try again.");
            return "account-profile";
        }
        return "redirect:/account";
    }

    @GetMapping("/transactions")
    public String transactionsCommissionPage(Model model, Authentication authentication) {
        CustomUserDetail userDetail = (CustomUserDetail) authentication.getPrincipal();
        String code = userDetail.getUser().getCode();

        // Get transaction list
        List<TransactionDTO> transactions = transactionService.getAllTransactionByCustomer(code);
        BigDecimal totalAmount = transactions.stream()
                .map(TransactionDTO::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        model.addAttribute("transactions", transactions);
        model.addAttribute("totalTransaction", totalAmount);

        // Get commission list
        List<CommissionDTO> commissions = commissionService.getCommissionByUser(code);
        BigDecimal totalCommission = commissions.stream()
                .map(CommissionDTO::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        model.addAttribute("commissions", commissions);
        model.addAttribute("totalCommission", totalCommission);

        return "account-transaction";
    }

}
