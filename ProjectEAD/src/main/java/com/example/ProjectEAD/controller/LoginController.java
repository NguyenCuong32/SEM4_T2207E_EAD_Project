package com.example.ProjectEAD.controller;

import com.example.ProjectEAD.entity.Role;
import com.example.ProjectEAD.entity.User;
import com.example.ProjectEAD.entity.UserRole;
import com.example.ProjectEAD.service.RoleService;
import com.example.ProjectEAD.service.UserRoleService;
import com.example.ProjectEAD.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class LoginController {
    private final UserService userService;
    private final UserRoleService userRoleService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public LoginController(UserService userService, UserRoleService userRoleService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("login")
    public String Login(){
        return "loginPage";
    }

    @GetMapping("register")
    public String RegisterForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("register")
    public String Register(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model){

        //Validated
        if(bindingResult.hasErrors()){
            return "register";
        }

        //Check USERNAME
        Optional<User> userCheckOptional = userService.getUserByUsername(user.getUsername());
        if(userCheckOptional.isPresent()){
            String exception = "Username has been existed";
            model.addAttribute("exception", exception);
            return "register";
        }

        //Check PHONE
        Optional<User> userPhoneOptional = userService.getUserByPhone(user.getPhone());
        if(userPhoneOptional.isPresent()){
            String exception = "Phone Number has been existed";
            model.addAttribute("exceptionP", exception);
            return "register";
        }

        //Add USER
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userService.add(user);

        //Add USER_ROLE
        UserRole userRole = new UserRole();
        userRole.setUser(userService.getUserByUsername(user.getUsername()).get());
        userRole.setRole(roleService.getRoleById(1).get());
        userRoleService.add(userRole);

        return "redirect:/loginPage";
    }

}
