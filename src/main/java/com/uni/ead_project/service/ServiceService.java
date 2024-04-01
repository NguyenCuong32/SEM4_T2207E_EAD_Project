package com.uni.ead_project.service;

import com.uni.ead_project.entity.ServiceEntity;
import com.uni.ead_project.repository.IServiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceService implements IServiceService {
    private final IServiceRepository serviceRepository;

    public ServiceService(IServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public List<ServiceEntity> getAllServices() {
        return serviceRepository.findAll();
    }

    @Override
    public Optional<ServiceEntity> getServiceById(String serviceId) {
        return serviceRepository.findById(serviceId);
    }

    @Override
    @Transactional
    public void saveService(ServiceEntity serviceEntity) {
        serviceRepository.save(serviceEntity);
    }

    @Override
    public void updateService() {

    }

    @Override
    public void deleteService(String serviceId) {
        serviceRepository.deleteById(serviceId);
    }
}
