package com.fai.brofee_fe.controller;

import com.fai.brofee_fe.dto.UserDetailDTO;
import com.fai.brofee_fe.entity.stored_procedure_entity.UserHierarchyItem_SP;
import com.fai.brofee_fe.entity.stored_procedure_entity.User_SP;
import com.fai.brofee_fe.repository.TransactionRepository;
import com.fai.brofee_fe.service.TransactionService;
import com.fai.brofee_fe.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {

    private final UserService userService;
    private final TransactionService transactionService;

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
    public String getCustomerDetailPage(@PathVariable String code, Model model) {
        UserDetailDTO user = userService.getUserByCode(code);
        model.addAttribute("customer", user);

        // Get transaction list
        model.addAttribute("transactions", transactionService.getAllTransactionByCustomer(code));

        // Get parent users
        List<UserHierarchyItem_SP> parentUsers = userService.getParentUsers(code);
        List<UserHierarchyItem_SP> childrenUsers = userService.getChildrenUsers(code);
        model.addAttribute("parents", parentUsers);
        model.addAttribute("children", childrenUsers);

        return "customer/detail";
    }

}
