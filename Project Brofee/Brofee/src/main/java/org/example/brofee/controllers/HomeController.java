package org.example.brofee.controllers;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class HomeController {
    @GetMapping("/default")
    public String successPage(HttpServletRequest request){
        if(request.isUserInRole("ADMIN")){
            return "redirect:/indexAdmin";
        }
        return "redirect:/indexUser";
    }

    @GetMapping("/indexAdmin")
    public String indexAdmin(){
        return "Index-Admin";
    }

    @GetMapping("/indexUser")
    public String indexUser(){
        return "Index-User";
    }
}
