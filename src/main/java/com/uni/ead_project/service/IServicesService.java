package com.uni.ead_project.service;

import com.uni.ead_project.entity.ServicesEntity;

import java.util.List;
import java.util.Optional;

public interface IServicesService {
    List<ServicesEntity> getAllServices();
    Optional<ServicesEntity> getServiceById(String serviceId);
    void saveFormService(ServicesEntity servicesEntity);
    void deleteService(String serviceId);
}
