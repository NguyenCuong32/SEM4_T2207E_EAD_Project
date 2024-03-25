package com.uni.ead_project.controller;

import com.uni.ead_project.entity.CouponEntity;
import com.uni.ead_project.service.CouponsService;
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
@RequestMapping("/coupons")
public class CouponController {
    private final CouponsService couponsService;

    public CouponController(CouponsService couponsService) {
        this.couponsService = couponsService;
    }
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }
    @GetMapping("/list")
    public String GetCoupon(Model model){
        List<CouponEntity> coupon= couponsService.getAllCoupons();
        model.addAttribute("coupon", coupon);
        return "coupon/list";
    }
    @GetMapping("/formAdd")
    public String ShowFormAdd(Model model) {
        CouponEntity coupon= new CouponEntity();
        model.addAttribute("coupon",coupon);
        return "coupon/form";
    }
    @PostMapping("/save")
    public String saveCoupon(@Valid @ModelAttribute("coupon") CouponEntity coupon, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "coupon/form";
        }
        else {
            couponsService.saveCoupon(coupon);
            return "redirect:/coupons/list";
        }
    }
    @GetMapping("formUpdate")
    public String ShowFormUpdate(@RequestParam("couponId") String couponId, Model model){
        Optional<CouponEntity> coupon = couponsService.getCouponById(couponId);
        model.addAttribute("coupon", coupon);
        return "coupon/form";
    }
    @GetMapping("delete")
    public String DeleteCoupon(@RequestParam("couponId") String couponId, Model model){
        couponsService.deleteCoupon(couponId);
        return "redirect:/coupons/list";
    }

}
