package com.uni.ead_project.service;

import com.uni.ead_project.entity.UserEntity;
import com.uni.ead_project.utils.GenerateQRCode;
import org.springframework.stereotype.Service;

@Service
public class QRCodeService implements IQRCodeService{
    private static final int ORDER_QR_CODE_SIZE_WIDTH = 300;
    private static final int ORDER_QR_CODE_SIZE_HEIGHT = 300;
    @Override
    public String generateQRCode(UserEntity user) {
        String prettyData = GenerateQRCode.prettyObject(user);

        String qrCode = GenerateQRCode.generateQRCode(prettyData, ORDER_QR_CODE_SIZE_WIDTH, ORDER_QR_CODE_SIZE_WIDTH);
        return qrCode;
    }
}
