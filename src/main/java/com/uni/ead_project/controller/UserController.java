package com.uni.ead_project.controller;

import com.uni.ead_project.entity.UserEntity;
import com.uni.ead_project.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService usersService;

    public UserController(UserService usersService) {
        this.usersService = usersService;
    }
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }
    @GetMapping("/list")
    public String GetUsers(Model model){
        List<UserEntity> users = usersService.getAllUsers();
        model.addAttribute("users", users);
        return "user/list";
    }
    @GetMapping("/formAdd")
    public String ShowFormAdd(Model model) {
        UserEntity user = new UserEntity();
        model.addAttribute("user",user);
        return "user/form";
    }
    @PostMapping("/save")
    public String saveUser(@Valid @ModelAttribute("user") UserEntity user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "user/form";
        }
        else {
            usersService.saveUser(user);
            return "redirect:/user/list";
        }
    }
    @GetMapping("formUpdate")
    public String ShowFormUpdate(@RequestParam("userId") String userId, Model model){
        Optional<UserEntity> user = usersService.getUserById(userId);
        model.addAttribute("user", user);
        return "user/form";
    }
    @GetMapping("delete")
    public String DeleteUser(@RequestParam("userId") String userId, Model model){
        usersService.deleteUser(userId);
        return "redirect:/user/list";
    }
}
