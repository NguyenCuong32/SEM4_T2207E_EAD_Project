package org.example.brofee.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.brofee.dto.CategoryDto;
import org.example.brofee.dto.ServiceDto;
import org.example.brofee.entities.Service;
import org.example.brofee.repositories.ICategoryRepository;
import org.example.brofee.repositories.IServiceRepository;
import org.example.brofee.services.ServicesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller

@RequestMapping("/service")
@RequiredArgsConstructor
public class ServiceController {
    private final IServiceRepository serviceRepository;
    private final ServicesService servicesService;
    private final ICategoryRepository categoryRepository;


    @GetMapping("/list")
    public String listService(Model model){
        List<Service> services = servicesService.getAllServices();
        model.addAttribute("services",services);
        return "html/service/Service";
    }

    @GetMapping("/add")
    public String addService(Model model){
        ServiceDto service = new ServiceDto();
        model.addAttribute("service",service);
        return "html/service/CreateService";
    }

    @PostMapping("/save")
    public String saveService(@ModelAttribute("service") @Valid ServiceDto service, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            // Có lỗi validation, xử lý thông báo lỗi
            return "html/service/CreateService";
        }
        Service findNameService = serviceRepository.findServicesByName(service.getName());
        if(findNameService!=null){
            model.addAttribute("errorMessage", "Name Service already exists");
            return "html/service/CreateService";
        }else{
            servicesService.saveService(service);
            return "redirect:/service/list";
        }
    }

    @GetMapping("/edit")
    public String editService(@RequestParam("id")UUID id, Model model){
        Service service = servicesService.getServiceById(id);
        model.addAttribute("service",service);
        model.addAttribute("errorMessage", model.asMap().get("errorMessage"));
        return "html/service/EditService";
    }

    @PostMapping("/update")
    public String updateService(@RequestParam("id") UUID id, @ModelAttribute("service") @Valid ServiceDto service, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            // Có lỗi validation, xử lý thông báo lỗi
            return "html/service/EditService";
        }

        Service findNameService = serviceRepository.findServicesByName(service.getName());
        if(findNameService != null && !findNameService.getId().equals(id)){
            redirectAttributes.addFlashAttribute("errorMessage", "Category name already exists");
            return "redirect:/service/edit?id=" + id;
        }else {
            servicesService.updateService(id, service);
            return "redirect:/service/list";
        }
    }

    @GetMapping("/remove")
    public String removeService(@RequestParam("id") UUID id){
        servicesService.deleteService(id);
        return "redirect:/service/list";
    }
}
