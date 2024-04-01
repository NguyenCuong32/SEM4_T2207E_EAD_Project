package com.uni.ead_project.controller;

import com.uni.ead_project.entity.EventEntity;
import com.uni.ead_project.service.EventService;
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
@RequestMapping("/events")
public class  EventController {
   private final EventService eventService;

   public EventController(EventService eventService) {
      this.eventService = eventService;
   }
   @InitBinder
   public void initBinder(WebDataBinder dataBinder) {
      StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
      dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
   }
   @GetMapping("/list")
   public String GetEvent(Model model){
      List<EventEntity> events = eventService.getAllEvents();
      model.addAttribute("events", events);
      model.addAttribute("event",new EventEntity());
      return "event/list";
   }
//   @GetMapping("/formAdd")
//   public String ShowFormAdd(Model model) {
//      EventEntity event = new EventEntity();
//      model.addAttribute("event",event);
//      return "event/form";
//   }
   @PostMapping("/save")
   public String saveEvent(@Valid @ModelAttribute("event") EventEntity event, Model model, BindingResult bindingResult){
      if (bindingResult.hasErrors()) {
         return "events/list";
      }
      else {
         eventService.saveEvent(event);
         return "redirect:/events/list";
      }
   }
   @GetMapping("formUpdate/{eventId}")
   public String ShowFormUpdate(@RequestParam("eventId") String eventId, Model model){
      Optional<EventEntity> event = eventService.getEventById(eventId);
      model.addAttribute("event", event);
      return "event/form";
   }
   @GetMapping("delete/{eventId}")
   public String DeleteEvent(@RequestParam("eventId") String eventId, Model model){
     eventService.deleteEvent(eventId);
      return "redirect:/events/list";
   }
}