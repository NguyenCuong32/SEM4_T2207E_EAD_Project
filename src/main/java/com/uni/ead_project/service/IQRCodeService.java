package com.uni.ead_project.service;

import com.uni.ead_project.entity.UserEntity;

public interface IQRCodeService {
    String generateQRCode(UserEntity user);
}
