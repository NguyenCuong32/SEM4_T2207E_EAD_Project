package com.uni.ead_project.controller;

import com.uni.ead_project.entity.UserEntity;
import com.uni.ead_project.service.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QRCodeController {
    @Autowired
    QRCodeService qrCodeService;

    @PostMapping(value = "generateQRCode")
    public String generateQRCode(@RequestBody UserEntity user){
        return qrCodeService.generateQRCode(user);
    }

}
