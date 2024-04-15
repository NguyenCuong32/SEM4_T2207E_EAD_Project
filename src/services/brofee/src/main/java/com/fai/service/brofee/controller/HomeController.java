package com.fai.service.brofee.controller;

import com.fai.service.brofee.dto.CategoryDTO;
import com.fai.service.brofee.dto.CategoryDetailDTO;
import com.fai.service.brofee.dto.EventDTO;
import com.fai.service.brofee.dto.EventDetailDTO;
import com.fai.service.brofee.service.CategoryService;
import com.fai.service.brofee.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class HomeController {

    private final CategoryService categoryService;
    private final EventService eventService;

    @GetMapping("/test")
    //@PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    public String test(Authentication authentication) {
        Authentication auth = authentication;
        return "test";
    }

    @GetMapping("/category")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = categoryService.getCategories();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categories);
    }

    @GetMapping("/event")
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        List<EventDTO> events = eventService.getEvents(1, 2);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(events);
    }


    @GetMapping("/service")
    public ResponseEntity<List<CategoryDetailDTO>> getAllServices() {
        List<CategoryDetailDTO> categories = categoryService.getCategoriesWithDetails();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categories);
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<EventDetailDTO> getEventDetail(@PathVariable Long id) {
        EventDetailDTO eventDetail = eventService.getEventDetail(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(eventDetail);
    }
}
