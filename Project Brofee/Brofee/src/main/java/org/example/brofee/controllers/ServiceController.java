package org.example.brofee.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/service")
public class ServiceController {
    @GetMapping("/list")
    public String listService(){
        return "Brofee/html/services/listservice";
    }
}
