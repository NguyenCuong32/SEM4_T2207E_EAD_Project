package com.fai.brofee_fe.controller;

import com.fai.brofee_fe.dto.UserDTO;
import com.fai.brofee_fe.dto.UserDetail.RootDetailUserDTO;
import com.fai.brofee_fe.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("user/index")
    public String index(Model model,
                        @RequestParam(value = "page",defaultValue = "1") int page,
                        @RequestParam(value = "search", defaultValue = "") String search
    ) {
       int currentPage = page;
        List<UserDTO> users = userService.SearchUser((page - 1 ),20 , search);
        long totalPages = userService.CountSearchUser(search);
        model.addAttribute("users", users);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", (int)Math.ceil((double) totalPages / 20));

        return "user/index.html";
    }


    @GetMapping("user/detail/{id}")
    public String index(Model model,@PathVariable long id)
    {
        RootDetailUserDTO detailUser = userService.detailUser(id);
        model.addAttribute("user_detail", detailUser);
        return "user/detail.html";
    }

}
