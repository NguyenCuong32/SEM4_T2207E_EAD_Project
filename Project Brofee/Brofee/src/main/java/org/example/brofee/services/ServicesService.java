package org.example.brofee.services;

import lombok.RequiredArgsConstructor;
import org.example.brofee.dto.ServiceDto;
import org.example.brofee.entities.Category;
import org.example.brofee.repositories.ICategoryRepository;
import org.example.brofee.repositories.IServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServicesService implements IServicesService{
    private final IServiceRepository serviceRepository;
    private final ICategoryRepository categoryRepository;

    @Override
    public List<org.example.brofee.entities.Service> getAllServices() {
        return serviceRepository.findAll();
    }

    @Override
    public org.example.brofee.entities.Service getServiceById(UUID id) {
        Optional<org.example.brofee.entities.Service> getServiceById = serviceRepository.findById(id);
        return getServiceById.get();
    }

    @Override
    public org.example.brofee.entities.Service saveService(ServiceDto service) {
        Optional<Category> existingCategory = categoryRepository.findById(service.getCategory());
        org.example.brofee.entities.Service setService = new org.example.brofee.entities.Service();
        setService.setName(service.getName());
        setService.setPrice(service.getPrice());
        setService.setDescription(service.getDescription());
        setService.setStatus(service.getStatus());
        setService.setCategory(existingCategory.get());
        return serviceRepository.save(setService);
    }

    @Override
    public org.example.brofee.entities.Service updateService(UUID id, ServiceDto service) {
        Optional<Category> existingCategory = categoryRepository.findById(service.getCategory());
        org.example.brofee.entities.Service setService = getServiceById(id);
        setService.setName(service.getName());
        setService.setPrice(service.getPrice());
        setService.setDescription(service.getDescription());
        setService.setStatus(service.getStatus());
        setService.setCategory(existingCategory.get());
        return serviceRepository.saveAndFlush(setService);
    }

    @Override
    public void deleteService(UUID id) {
        serviceRepository.deleteById(id);
    }
}
