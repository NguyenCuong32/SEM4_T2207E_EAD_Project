package com.uni.ead_project.controller;

import com.uni.ead_project.entity.ServiceEntity;
import com.uni.ead_project.service.ServiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/services")
public class ServiceController {
    private final ServiceService serviceService;
    @Autowired
    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }
    @GetMapping("/list")
    public String GetServices(Model model){
        List<ServiceEntity> services = serviceService.getAllServices();
        model.addAttribute("services", services);
        return "service/list";
    }
    @PostMapping("/save")
    public String saveService(@Valid @ModelAttribute("service") ServiceEntity service, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "service/form";
        }
        else {
            serviceService.saveService(service);
            return "redirect:/services/list";
        }
    }
    @GetMapping("formUpdate")
    public String ShowFormUpdate(@RequestParam("serviceId") String serviceId, Model model){
        Optional<ServiceEntity> service = serviceService.getServiceById(serviceId);
        model.addAttribute("service", service);
        return "service/form";
    }
    @GetMapping("getById/{serviceId}")
    public Optional<ServiceEntity> getServiceById(@PathVariable("serviceId") String serviceId){
        System.out.println("finding service "+serviceId);
        Optional<ServiceEntity> service = serviceService.getServiceById(serviceId);
        return service;
    }
    @GetMapping("delete")
    public String DeleteService(@RequestParam("serviceId") String serviceId, Model model){
        serviceService.deleteService(serviceId);
        return "redirect:/services/list";
    }
}
