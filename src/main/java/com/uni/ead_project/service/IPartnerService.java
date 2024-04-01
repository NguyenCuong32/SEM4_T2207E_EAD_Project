package com.uni.ead_project.service;

import com.uni.ead_project.entity.PartnerEntity;

import java.util.List;
import java.util.Optional;

public interface IPartnerService {
    List<PartnerEntity> getAllPartners();
    Optional<PartnerEntity> getPartnerById(String userId);
    void savePartner(PartnerEntity partner);
    void updatePartner();
    void deletePartner(String userId);

}
