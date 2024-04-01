package com.uni.ead_project.service;

import com.uni.ead_project.entity.ServiceEntity;

import java.util.List;
import java.util.Optional;

public interface IServiceService {
    List<ServiceEntity> getAllServices();
    Optional<ServiceEntity> getServiceById(String serviceId);
    void saveService(ServiceEntity service);
    void updateService();
    void deleteService(String serviceId);
}
