package com.uni.ead_project.service;

import com.uni.ead_project.entity.PartnerEntity;

import java.util.List;
import java.util.Optional;

public interface IPartnersService {
    List<PartnerEntity> getAllPartners();
    Optional<PartnerEntity> getPartnerById(String userId);
    void savePartner(PartnerEntity partnerEntity);
    void deletePartner(String userId);
}
