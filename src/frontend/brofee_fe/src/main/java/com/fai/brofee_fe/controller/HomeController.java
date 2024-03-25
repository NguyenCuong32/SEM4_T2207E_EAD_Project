package com.fai.brofee_fe.controller;

import com.fai.brofee_fe.dto.TransactionCreateNewCustomerDTO;
import com.fai.brofee_fe.dto.TransactionCreateOldCustomerDTO;
import com.fai.brofee_fe.service.CategoryAndServiceService;
import com.fai.brofee_fe.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final CategoryAndServiceService categoryAndServiceService_;
    private final TransactionService transactionService;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("categories", categoryAndServiceService_.getAllCategoriesWithDetail());
        model.addAttribute("transactionForNewCustomer", new TransactionCreateNewCustomerDTO());
        model.addAttribute("transactionForOldCustomer", new TransactionCreateOldCustomerDTO());

        // Get the list of transactions today
        LocalDateTime startOfToday = LocalDate.now().atStartOfDay();
        LocalDateTime endOfToday = LocalDate.now().plusDays(1).atStartOfDay();
        model.addAttribute("transactions", transactionService.getAllTransactionByDate(startOfToday, endOfToday));

        return "index";
    }

}
