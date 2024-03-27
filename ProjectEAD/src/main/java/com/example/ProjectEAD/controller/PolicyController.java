package com.example.ProjectEAD.controller;

import com.example.ProjectEAD.entity.Policy;
import com.example.ProjectEAD.service.PolicyService;
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
@RequestMapping("admin/policy")
public class PolicyController {
    private final PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("list")
    public String List(Model model){
        List<Policy> policies = policyService.getAllPolicy();
        model.addAttribute("policies", policies);
        return "admin/policy/index";
    }

    @GetMapping("addForm")
    public String addForm(Model model){
        Policy policy = new Policy();
        model.addAttribute("policy", policy);
        return "admin/policy/add";
    }

    @PostMapping("add")
    public String save(@Valid @ModelAttribute Policy policy, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "admin/policy/add";
        }
        policyService.add(policy);
        return "redirect:/admin/policy/list";
    }

    @GetMapping("editForm/{id}")
    public String editForm(@PathVariable int id, Model model){
        Optional<Policy> policyOptional = policyService.getPolicyById(id);
        if(policyOptional.isPresent()){
            Policy policy = policyOptional.get();
            model.addAttribute("policy", policy);
            return "admin/policy/edit";
        }
        return "redirect:/admin/policy/list";
    }

    @PostMapping("edit")
    public String edit(@Valid @ModelAttribute Policy policy, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "admin/policy/edit";
        }
        policyService.add(policy);
        return "redirect:/admin/policy/list";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id){
        policyService.deleteById(id);
        return "redirect:/admin/policy/list";
    }
}
