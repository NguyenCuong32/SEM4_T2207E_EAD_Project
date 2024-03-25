package com.uni.ead_project.service;

import com.uni.ead_project.entity.ServicesEntity;
import com.uni.ead_project.repository.ServicesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicesService implements IServicesService{
    private final ServicesRepository servicesRepository;

    public ServicesService(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }

    @Override
    public List<ServicesEntity> getAllServices() {
        return servicesRepository.findAll();
    }

    @Override
    public Optional<ServicesEntity> getServiceById(String serviceId) {
        return servicesRepository.findById(serviceId);
    }

    @Override
    public void saveFormService(ServicesEntity servicesEntity) {
        servicesRepository.save(servicesEntity);
    }

    @Override
    public void deleteService(String serviceId) {
        servicesRepository.deleteById(serviceId);
    }
}
