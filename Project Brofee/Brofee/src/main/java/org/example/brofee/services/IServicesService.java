package org.example.brofee.services;

import org.example.brofee.dto.ServiceDto;
import org.example.brofee.entities.Service;

import java.util.List;
import java.util.UUID;

public interface IServicesService {
    List<Service> getAllServices();
    Service getServiceById (UUID id);
    Service saveService(ServiceDto service);
    Service updateService(UUID id, ServiceDto service);
    void deleteService(UUID id);
}
