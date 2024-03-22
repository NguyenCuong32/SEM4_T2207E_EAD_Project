package org.example.brofee.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.example.brofee.dto.AccountDto;
import org.example.brofee.entities.Account;
import org.example.brofee.entities.Role;
import org.example.brofee.repositories.IRoleRepository;
import org.example.brofee.services.IAccountService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private IAccountService iAccountService;
    private IRoleRepository iRoleRepository;

    public RegisterController(IAccountService iAccountService, IRoleRepository iRoleRepository) {
        this.iAccountService = iAccountService;
        this.iRoleRepository = iRoleRepository;
    }

    @GetMapping("/showRegister")
    public String showRegister(Model model){
        AccountDto accountDto = new AccountDto();
        model.addAttribute("accountDto", accountDto);
        return "Register";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute AccountDto accountDto,
                      BindingResult bindingResult,
                      Model model,
                      HttpSession httpSession){
        String username = accountDto.getUserName();
        if(bindingResult.hasErrors()){  //Form validation
            return "Register";
        }

        //Check if the account exist
        Account accountExisting = iAccountService.findByUserName(username);
        if(accountExisting!=null){
            model.addAttribute("accountDto", new AccountDto());
            model.addAttribute("my_error", "Account already exists.");
            return "Register";
        }

        //If the account does not exist yet => save
        Account account = new Account();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        account.setUsername(accountDto.getUserName());
        account.setPassword(bCryptPasswordEncoder.encode(accountDto.getPassword()));
        account.setEnabled(true);

        //Role Default
        Role roleDefault = iRoleRepository.findByName("ROLE_USER");
        Collection<Role> roles = new ArrayList<>();
        roles.add(roleDefault);
        account.setRoles(roles);

        iAccountService.save(account);

        //Notification success
        httpSession.setAttribute("my_account", account);
        return "Confirmation";
    }
}
