package com.uni.ead_project.service;

import com.uni.ead_project.entity.PartnersEntity;
import com.uni.ead_project.repository.PartnersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartnersService implements IPartnersService{
    private final PartnersRepository partnersRepository;

    public PartnersService(PartnersRepository partnersRepository) {
        this.partnersRepository = partnersRepository;
    }

    @Override
    public List<PartnersEntity> getAllPartner() {
        return partnersRepository.findAll();
    }

    @Override
    public Optional<PartnersEntity> getAllPartnerById(String userId) {
        return partnersRepository.findById(userId);
    }

    @Override
    public void savePartner(PartnersEntity partnersEntity) {
        partnersRepository.save(partnersEntity);
    }

    @Override
    public void deletePartner(String userId) {
        partnersRepository.deleteById(userId);
    }
}