package org.example.brofee.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.brofee.dto.BranchDto;
import org.example.brofee.entities.Branch;
import org.example.brofee.entities.Category;
import org.example.brofee.repositories.IBranchRepository;
import org.example.brofee.services.BranchService;
import org.example.brofee.services.IBranchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("branches")
@RequiredArgsConstructor
public class BranchController {
    private final IBranchService iBranchService;
    private final IBranchRepository iBranchRepository;

    @GetMapping("/list")
    public String listBranches(Model model){
        List<Branch> branches = iBranchService.getAllBranches();
        model.addAttribute("branches", branches);
        return "html/branch/Index";
    }

    @GetMapping("/add")
    public String add(Model model){
        BranchDto branchDto = new BranchDto();
        model.addAttribute("branch", branchDto);
        return "html/branch/Add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("branch") BranchDto branchDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "html/branch/Add";
        }

        //Check ExistingBranch
        Branch existingBranch = iBranchRepository.findBranchByName(branchDto.getName());
        if(existingBranch !=null){
            model.addAttribute("errorMessage", "Branch's Name already exists.");
            return "html/branch/Add";
        }
        iBranchService.save(branchDto);
        return "redirect:/branches/list";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") UUID id, Model model){
        Optional<Branch> branchOptional = iBranchService.getBranchById(id);
        if(branchOptional.isPresent()){
            Branch branch = branchOptional.get();
            model.addAttribute("branch", branch);
            return "html/branch/Edit";
        }
        return "html/branch/Edit";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam("id") UUID id, Model model,@Valid @ModelAttribute("branch") BranchDto branchDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "html/branch/Edit";
        }
        //Sau nay can viet them doan kiem tra id cua edit co ton tai khong -> dua ra trang xu ly loi rieng
        Optional<Branch> branchOptional = iBranchService.getBranchById(id);
        if(branchOptional.isPresent()){
            iBranchService.update(id, branchDto);
            model.addAttribute("branch", branchDto);
            return "redirect:/branches/list";
        }
        return "redirect:/branches/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id")UUID id, Model model){
        //Bao gio su dung sau
//        Optional<Branch> findBranch = iBranchService.getBranchById(id);
//        if(findBranch.isEmpty()){
//            model.addAttribute("errorMessage", "Branch not found.");
//            return "Page-403";
//        }
        iBranchService.delete(id);
        return "redirect:/branches/list";
    }
}
