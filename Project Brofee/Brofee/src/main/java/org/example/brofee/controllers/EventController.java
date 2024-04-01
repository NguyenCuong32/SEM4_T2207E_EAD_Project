package org.example.brofee.controllers;

import lombok.RequiredArgsConstructor;
import org.example.brofee.dto.EventDto;
import org.example.brofee.entities.Event;
import org.example.brofee.services.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping("/list")
    public String listEvent(Model model){
        List<Event> events = eventService.getAllEvent();
        model.addAttribute("events",events);
        return "/html/event/Event";
    }
    @GetMapping("/add")
    public String addEvent(Model model){
        Event event = new Event();
        model.addAttribute("event",event);
        return "/html/event/CreateEvent";
    }

    @PostMapping("/save")
    public String saveEvent(@ModelAttribute("event") EventDto event,@RequestParam("bannerUpload") MultipartFile bannerUpload) throws IOException {
        eventService.createEvent(event,bannerUpload);
        return "redirect:/event/list";
    }

    @PostMapping("/addE")
    public String saveEEvent(@ModelAttribute("event") EventDto event, @RequestParam("bannerUpload") MultipartFile bannerUpload, @RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("timeStart") LocalDateTime timeStart, @RequestParam("timeEnd") LocalDateTime timeEnd, @RequestParam("status") int status) throws IOException {
        eventService.saveEventToDB(bannerUpload,name,description,timeStart,timeEnd,status);
        return "redirect:/event/list";
    }

    @GetMapping("/edit")
    public String editEvent(@RequestParam("id") UUID id, Model model){
        Event event = eventService.getEventById(id);
        model.addAttribute("event",event);
        return "/html/event/EditEvent";
    }

    @PostMapping("/update")
    public String updateEvent(@RequestParam("id") UUID id, @ModelAttribute("event") EventDto event, @RequestParam("bannerUpload") MultipartFile bannerUpload, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            // Có lỗi validation, xử lý thông báo lỗi
            return "html/service/EditService";
        }
        eventService.updateEvent(id,event,bannerUpload);
        return "redirect:/event/list";
    }

    @GetMapping("/remove")
    public String removeEvent(@RequestParam("id") UUID id){
        eventService.deleteEvent(id);
        return "redirect:/event/list";
    }

}
