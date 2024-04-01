package com.example.spa_website.controller;

import com.example.spa_website.dto.UserCreateDTO;
import com.example.spa_website.dto.UserDTO;
import com.example.spa_website.service.UserService;
import jakarta.persistence.EntityExistsException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new UserCreateDTO());
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @ModelAttribute("user") @Valid UserCreateDTO userCreateDTO,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        try {
            userService.createUser(userCreateDTO);
        } catch (EntityExistsException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        } catch (Exception e) {
            model.addAttribute("error", "Something wrong. Please try again.");
            return "register";
        }

        return "login";
    }


    @PostMapping(value = "/search-user", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<UserDTO> searchUser(@RequestParam("codeOrPhone") String codeOrPhone) {
        Optional<UserDTO> user = userService.getUserByCodeOrPhone(codeOrPhone);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
