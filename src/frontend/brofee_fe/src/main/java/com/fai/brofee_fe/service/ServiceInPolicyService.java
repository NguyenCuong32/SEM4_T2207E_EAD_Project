package com.fai.brofee_fe.service;

import com.fai.brofee_fe.dto.ServiceDetailDTO;
import com.fai.brofee_fe.repository.ServiceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceInPolicyService {

    private final ServiceRepository serviceRepository;
    private final ModelMapper modelMapper;

    public List<ServiceDetailDTO> getAllServices() {
        List<com.fai.brofee_fe.entity.Service> services = serviceRepository.findAll();
        services.forEach(com.fai.brofee_fe.entity.Service::getPolicyAssignments);
        return services
                .stream()
                .map(service -> modelMapper.map(service, ServiceDetailDTO.class))
                .toList();
    }
}
