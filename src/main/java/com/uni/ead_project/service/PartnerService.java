package com.uni.ead_project.service;

import com.uni.ead_project.entity.PartnerEntity;
import com.uni.ead_project.repository.IPartnerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PartnerService implements IPartnerService {


    private final IPartnerRepository IPartnerRepository;

    public PartnerService(IPartnerRepository IPartnerRepository) {
        this.IPartnerRepository = IPartnerRepository;
    }

    @Override
    public List<PartnerEntity> getAllPartners() {
        return IPartnerRepository.findAll();
    }

    @Override
    public Optional<PartnerEntity> getPartnerById(String userId) {
        return IPartnerRepository.findById(userId);
    }

    @Override
    @Transactional
    public void savePartner(PartnerEntity partnerEntity) {
        IPartnerRepository.save(partnerEntity);
    }

    @Override
    public void updatePartner() {

    }

    @Override
    public void deletePartner(String userId) {
        IPartnerRepository.deleteById(userId);
    }


}
