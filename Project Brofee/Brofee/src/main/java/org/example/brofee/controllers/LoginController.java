package org.example.brofee.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class LoginController {
    @GetMapping("/loginPage")
    public String showLoginPage(){
        return "Login";
    }

    @GetMapping("/page-403")
    public String showPage403(){
        return "Page-403";
    }
}
