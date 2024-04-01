package com.uni.ead_project.service;

import com.uni.ead_project.entity.ServiceEntity;

import java.util.List;
import java.util.Optional;

public interface IServicesService {
    List<ServiceEntity> getAllServices();
    Optional<ServiceEntity> getServiceById(String serviceId);
    void saveFormService(ServiceEntity serviceEntity);
    void deleteService(String serviceId);
}
