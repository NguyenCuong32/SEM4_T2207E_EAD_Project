package com.fai.brofee_fe.controller;

import com.fai.brofee_fe.dto.*;
import com.fai.brofee_fe.entity.Category;
import com.fai.brofee_fe.service.CategoryService;
import com.fai.brofee_fe.service.ServiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceService serviceService;
    private final CategoryService categoryService;
    @GetMapping("")
    public String getServicesPage(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size,
            @RequestParam(defaultValue = "0") Long item,
            @RequestParam(required = false) String search
    )
    {

        Page<ServiceDTO> serviceDTOPage = serviceService.getServicePage(page, size, item, search);
        model.addAttribute("services", serviceDTOPage);

        //Get the list of categories that contain products
        Iterable<CategoryDTO> getAllCategoryContainProduct = categoryService.getAllCategories()
                .stream()
                .filter(categoryDTO -> !categoryDTO.getServices().isEmpty())
        .collect(Collectors.toList());
        model.addAttribute("categoryContainProduct", getAllCategoryContainProduct);

        //Get all categories
        Iterable<CategoryDTO> getAllCategories = categoryService.getAllCategories();
        model.addAttribute("categories", getAllCategories);

        model.addAttribute("newServiceCreateDTO", new ServiceCreateDTO());

        return "servicePage/index";

    }

    @PostMapping("/add")
    public String saveService(@Valid @ModelAttribute("newServiceCreateDTO") ServiceCreateDTO newService, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){

            return "servicePage/index";
        } else {
            serviceService.createNewService(newService);
            return "redirect:/services";
        }
    }

    @GetMapping("/edit")
    public String getServiceTOEdit(@RequestParam("serviceId") Long id, Model model) {
        Optional<ServiceEditDTO> serviceEditDTO = serviceService.getServiceEditById(id);
        if (serviceEditDTO.isPresent()) {
            ServiceEditDTO editServiceDTO = serviceEditDTO.get(); // Get the actual ServiceEditDTO object
            model.addAttribute("editServiceDTO", editServiceDTO);
            //Get all categories
            Iterable<CategoryDTO> getAllCategories = categoryService.getAllCategories();
            model.addAttribute("categories", getAllCategories);

            return "servicePage/edit";
        } else {
            return "redirect:/services";
        }
    }

    @PostMapping("/update")
    public String updateService(
            @Valid @ModelAttribute("editServiceDTO") ServiceEditDTO serviceEditDTO,
            BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "servicePage/edit";
        } else {
            serviceService.updateService(serviceEditDTO.getId(), serviceEditDTO);
            return "redirect:/services";
        }
    }

    // DELETE
    @GetMapping("/delete")
    public String deleteService(@RequestParam("serviceId") Long id){
        serviceService.deleteService(id);
        return "redirect:/services";
    }
}
