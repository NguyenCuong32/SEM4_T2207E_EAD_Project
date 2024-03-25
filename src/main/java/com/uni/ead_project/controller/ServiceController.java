package com.uni.ead_project.controller;

import com.uni.ead_project.entity.ServicesEntity;
import com.uni.ead_project.service.ServicesService;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/service")
public class ServiceController {
    private final ServicesService servicesService;

    public ServiceController(ServicesService servicesService) {
        this.servicesService = servicesService;
    }
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }
    @GetMapping("/list")
    public String GetPartner(Model model){
        List<ServicesEntity> service=servicesService.getAllServices();
        model.addAttribute("service", service);
        return "service/list";
    }
    @GetMapping("/formAdd")
    public String ShowFormAdd(Model model) {
      ServicesEntity services = new ServicesEntity();
        model.addAttribute("service",services);
        return "service/form";
    }
    @PostMapping("/save")
    public String saveService(@Valid @ModelAttribute ServicesEntity services, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "service/form";
        }
        else {
            servicesService.saveFormService(services);
            return "redirect:/service/list";
        }
    }
    @GetMapping("formUpdate")
    public String ShowFormUpdate(@RequestParam("serviceId") String serviceId, Model model){
        Optional<ServicesEntity> service = servicesService.getServiceById(serviceId);
        model.addAttribute("service", service);
        return "service/form";
    }
    @GetMapping("delete")
    public String DeleteServicer(@RequestParam("serviceId") String serviceId, Model model){
        servicesService.deleteService(serviceId);
        return "redirect:/service/list";
    }
}
