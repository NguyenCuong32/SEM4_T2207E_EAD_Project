package com.fai.brofee_fe.controller;

import com.fai.brofee_fe.dto.UserDTO;
import com.fai.brofee_fe.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequestMapping("/user_h")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<UserDTO> searchUser(@RequestParam("codeOrPhone") String codeOrPhone) {
        Optional<UserDTO> user = userService.getUserByCodeOrPhone(codeOrPhone);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
