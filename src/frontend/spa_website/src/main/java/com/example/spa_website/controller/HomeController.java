package com.example.spa_website.controller;

import com.example.spa_website.dto.CategoryDTO;
import com.example.spa_website.dto.CategoryDetailDTO;
import com.example.spa_website.dto.EventDTO;
import com.example.spa_website.dto.EventDetailDTO;
import com.example.spa_website.service.CategoryService;
import com.example.spa_website.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@AllArgsConstructor
public class HomeController {

    private final CategoryService categoryService;
    private final EventService eventService;

    @GetMapping
    public String homePage(Model model) {
        List<CategoryDTO> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);

        List<EventDTO> events = eventService.getEvents(1, 2);
        model.addAttribute("events", events);

        return "index";
    }

    @GetMapping("/services")
    public String servicesPage(Model model) {
        List<CategoryDetailDTO> categories = categoryService.getCategoriesWithDetails();
        model.addAttribute("categories", categories);
        return "services-price";
    }

    @GetMapping("/event/{id}")
    public String eventDetailPage(
            @PathVariable Long id,
            Model model
    ) {
        EventDetailDTO eventDetail = eventService.getEventDetail(id);
        model.addAttribute("eventDetail", eventDetail);
        return "event-detail";
    }
}
