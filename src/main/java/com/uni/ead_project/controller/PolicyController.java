package com.uni.ead_project.controller;

import com.uni.ead_project.entity.PoliciesEntity;
import com.uni.ead_project.service.PoliciesService;
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
@RequestMapping("/policies")
public class PolicyController {
     private final PoliciesService policiesService;

    public PolicyController(PoliciesService policiesService) {
        this.policiesService = policiesService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }
    @GetMapping("/list")
    public String GetPolicy(Model model){
        List<PoliciesEntity> policies = policiesService.getAllPolicies();
        model.addAttribute("policies", policies);
        return "policy/list";
    }
    @GetMapping("/formAdd")
    public String ShowFormAdd(Model model) {
        PoliciesEntity policy = new PoliciesEntity();
        model.addAttribute("policy",policy);
        return "policy/form";
    }
    @PostMapping("/save")
    public String savePolicy(@Valid @ModelAttribute("policy") PoliciesEntity policy, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "policy/form";
        }
        else {
            policiesService.savePolicyId(policy);
            return "redirect:/policies/list";
        }
    }
    @GetMapping("formUpdate")
    public String ShowFormUpdate(@RequestParam("policyId") String policyId, Model model){
        Optional<PoliciesEntity> policy = policiesService.getPolicyById(policyId);
        model.addAttribute("policy", policy);
        return "policy/form";
    }
    @GetMapping("delete")
    public String DeletePolicie(@RequestParam("policyId") String policyId, Model model){
        policiesService.deletePolicyId(policyId);
        return "redirect:/policies/list";
    }
}
