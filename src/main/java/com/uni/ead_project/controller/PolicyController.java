package com.uni.ead_project.controller;

import com.uni.ead_project.entity.PolicyEntity;
import com.uni.ead_project.service.PolicyService;
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
public class  PolicyController {
     private final PolicyService policiesService;

    public PolicyController(PolicyService policiesService) {
        this.policiesService = policiesService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }
    @GetMapping("/list")
    public String GetPolicy(Model model){
        List<PolicyEntity> policies = policiesService.getAllPolicies();
        model.addAttribute("policies", policies);
        model.addAttribute("policy", new PolicyEntity());
        return "policy/list";
    }
//    @GetMapping("/formAdd")
//    public String ShowFormAdd(Model model) {
//        PolicyEntity policy = new PolicyEntity();
//        model.addAttribute("policy",policy);
//        return "policy/form";
//    }
    @PostMapping("/save")
    public String savePolicy(@Valid @ModelAttribute("policy") PolicyEntity policy, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "policies/list";
        }
        else {
            policiesService.savePolicy(policy);
            return "redirect:/policies/list";
        }
    }
    @GetMapping("formUpdate")
    public String ShowFormUpdate(@RequestParam("policyId") String policyId, Model model){
        Optional<PolicyEntity> policy = policiesService.getPolicyById(policyId);
        model.addAttribute("policy", policy);
        return "policy/form";
    }
    @GetMapping("delete/{policyId}")
    public String DeletePolicie(@RequestParam("policyId") String policyId, Model model){
        policiesService.deletePolicyId(policyId);
        return "redirect:/policies/list";
    }
}
