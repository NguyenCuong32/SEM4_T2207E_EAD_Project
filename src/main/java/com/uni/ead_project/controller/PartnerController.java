package com.uni.ead_project.controller;

import com.uni.ead_project.entity.PartnerEntity;
import com.uni.ead_project.service.PartnerService;
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
@RequestMapping("/partners")
public class PartnerController {
    private final PartnerService partnersService;

    public PartnerController(PartnerService partnersService) {
        this.partnersService = partnersService;
    }
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }
    @GetMapping("/list")
    public String GetPartner(Model model){
        List<PartnerEntity> partners = partnersService.getAllPartners();
        model.addAttribute("partners", partners);
        model.addAttribute("partner", new PartnerEntity());
        return "partner/list";
    }
    @PostMapping("/list")
    public String submitPartner(@ModelAttribute PartnerEntity partner, Model model) {
        Optional<PartnerEntity> completePartner = partnersService.getPartnerById(partner.getInviterId());
        System.out.print("invite"+completePartner);
        model.addAttribute("partner", completePartner);
        return "partner/list";
    }
    @GetMapping("/formAdd")
    public String ShowFormAdd(@ModelAttribute Model model) {
        PartnerEntity partner = new PartnerEntity();
        model.addAttribute("partner",partner);
        return "partner/form";
    }
    @PostMapping("/save")
    public String savePartner(@Valid @ModelAttribute("partner") PartnerEntity partner, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "partners/list";
        }
        else {
            partnersService.savePartner(partner);
            return "redirect:/partners/list";
        }
    }
    @GetMapping("formUpdate/{userId}")
    public String ShowFormUpdate(@RequestParam("userId") String userId, Model model){
        Optional<PartnerEntity> partner = partnersService.getPartnerById(userId);
        model.addAttribute("partner", partner);
        return "partners/form";
    }
    @GetMapping("delete")
    public String DeletePartner(@RequestParam("userId") String userId, Model model){
        partnersService.deletePartner(userId);
        return "redirect:/partners/list";
    }

}
