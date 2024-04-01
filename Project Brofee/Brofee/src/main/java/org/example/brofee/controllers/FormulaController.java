package org.example.brofee.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.brofee.dto.FormulaDto;
import org.example.brofee.entities.Formula;
import org.example.brofee.repositories.IFormulaRepository;
import org.example.brofee.services.IFormulaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("formulas")
@RequiredArgsConstructor
public class FormulaController {
    private final IFormulaService iFormulaService;
    private final IFormulaRepository iFormulaRepository;
    @GetMapping("/list")
    public String list(Model model){
        List<Formula> formulas = iFormulaService.getAllFormulas();
        model.addAttribute("formulas", formulas);
        return "html/formula/Index";
    }

    @GetMapping("/add")
    public String add(Model model){
        FormulaDto formulaDto = new FormulaDto();
        model.addAttribute("formula", formulaDto);
        return "html/formula/Add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("formula") FormulaDto formulaDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "html/formula/Add";
        }

        //Check formula existing
        Formula formulaExisting = iFormulaRepository.findFormulaByName(formulaDto.getName());
        if(formulaExisting!=null){
            model.addAttribute("errorMessage", "Formula's Name already exists.");
            return "html/formula/Add";
        }
        iFormulaService.save(formulaDto);
        return "redirect:/formulas/list";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id")UUID id,Model model){
        Optional<Formula> formulaOptional = iFormulaService.getFormulaById(id);
        if(formulaOptional.isPresent()){
            Formula formula = formulaOptional.get();
            model.addAttribute("formula", formula);
            return "html/formula/Edit";
        }
        return "html/formula/Edit";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam("id")UUID id,Model model,@Valid @ModelAttribute("formula")FormulaDto formulaDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "html/formula/Edit";
        }

        //Check formula existing
        Formula formulaExisting = iFormulaRepository.findFormulaByName(formulaDto.getName());
        if(formulaExisting!=null){
            model.addAttribute("errorMessage", "Formula's Name already exists.");
            return "html/formula/Edit";
        }

        Optional<Formula> formulaOptional = iFormulaService.getFormulaById(id);
        if(formulaOptional.isPresent()){
            iFormulaService.update(id,formulaDto);
            model.addAttribute("formula", formulaDto);
            return "redirect:/formulas/list";
        }
        return "redirect:/formulas/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") UUID id){
        iFormulaService.delete(id);
        return "redirect:/formulas/list";
    }
}
