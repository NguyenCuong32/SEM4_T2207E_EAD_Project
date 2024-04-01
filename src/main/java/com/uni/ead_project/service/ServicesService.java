package com.uni.ead_project.service;

import com.uni.ead_project.entity.ServiceEntity;
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
    public List<ServiceEntity> getAllServices() {
        return servicesRepository.findAll();
    }

    @Override
    public Optional<ServiceEntity> getServiceById(String serviceId) {
        return servicesRepository.findById(serviceId);
    }

    @Override
    public void saveFormService(ServiceEntity serviceEntity) {
        servicesRepository.save(serviceEntity);
    }

    @Override
    public void deleteService(String serviceId) {
        servicesRepository.deleteById(serviceId);
    }
}
