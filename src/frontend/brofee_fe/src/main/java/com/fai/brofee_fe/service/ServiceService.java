package com.fai.brofee_fe.service;

import com.fai.brofee_fe.dto.*;
import com.fai.brofee_fe.entity.Category;
import com.fai.brofee_fe.entity.Service;
import com.fai.brofee_fe.repository.CategoryRepository;
import com.fai.brofee_fe.repository.ServiceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Transactional
public class ServiceService implements IServiceService{

    private final ServiceRepository serviceRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;

    @Override
    public List<ServiceDTO> getAllServices() {
        List<Service> serviceList = serviceRepository.findAll();
        if(serviceList.isEmpty())
            return Collections.emptyList();

        return serviceList.stream().map(service ->
            modelMapper.map(service, ServiceDTO.class)
        ).collect(Collectors.toList());
    }

    @Override
    public Page<ServiceDTO> getServicePage(int page, int size, Long categoryId,String search) {

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Service> servicesPage;
        if (search != null && !search.isEmpty()) {
            servicesPage = serviceRepository.findByNameContaining(search, pageRequest);
        }
        else {
            if (categoryId == 0) {
                servicesPage = serviceRepository.findAll(pageRequest);
            }
            else {
                servicesPage = serviceRepository.findByCategory_Id(categoryId,pageRequest);
            }
        }
        return servicesPage.map(
                service -> modelMapper.map(service, ServiceDTO.class)
        );
    }



    @Override
    public Optional<ServiceDTO> getServiceById(Long id) {
        return serviceRepository.findById(id).map(
                service -> modelMapper.map(service, ServiceDTO.class)
        );
    }

    @Override
    public Optional<ServiceEditDTO> getServiceEditById(Long id) {
        return serviceRepository.findById(id).map(service -> {
                    ServiceEditDTO serviceEditDTO = modelMapper.map(service, ServiceEditDTO.class);
                    serviceEditDTO.setOldThumbnail(service.getThumbnail());

                    return serviceEditDTO;
                }
        );
    }

    @Override
    public void createNewService(ServiceCreateDTO serviceCreateDTO) {
        try {
            String iconUrl = null;
            if (serviceCreateDTO.getThumbnail() != null) {
                iconUrl = cloudinaryService.upload(serviceCreateDTO.getThumbnail());
            }
            Service newService= modelMapper.map(serviceCreateDTO, Service.class);
            newService.setThumbnail(iconUrl);

            // Assign the category object to the new service
            Category category = categoryRepository.findById(serviceCreateDTO.getCategoryId()).orElseThrow(() -> new RuntimeException("Class not found"));
            newService.setCategory(category);

            newService.setCreatedAt(LocalDateTime.now());
            serviceRepository.save(newService);
        } catch (IOException ex) {
            throw new RuntimeException("Failed to upload image: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void updateService(Long id, ServiceEditDTO serviceEditDTO) {
        Optional<Service> existedService = serviceRepository.findById(id);
        existedService.ifPresentOrElse(
                service -> {
                    modelMapper.map(serviceEditDTO, service);

                    String iconUrl = updateServiceThumbnail(serviceEditDTO);
                    service.setThumbnail(iconUrl);

                    service.setUpdatedAt(LocalDateTime.now());
                    serviceRepository.save(service);
                },
                () -> {
                    // if service not existed
                    Service newService = modelMapper.map(serviceEditDTO, Service.class);
                    newService.setId(id);

                    String iconUrl = updateServiceThumbnail(serviceEditDTO);
                    newService.setThumbnail(iconUrl);

                    newService.setCreatedAt(LocalDateTime.now());
                    serviceRepository.save(newService);
                }
        );
    }

    private String updateServiceThumbnail(ServiceEditDTO serviceEditDTO) {
        try {
            String iconUrl = null;
            if (serviceEditDTO.getNewThumbnail() != null) {
                iconUrl = cloudinaryService.upload(serviceEditDTO.getNewThumbnail());
            }
            return iconUrl;
        } catch (IOException ex) {
            throw new RuntimeException("Failed to upload image: " + ex.getMessage(), ex);
        }

    }

    @Override
    public void deleteService(Long id) {
        categoryRepository.deleteById(id);
    }
}
