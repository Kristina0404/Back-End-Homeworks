package de.ait.events.controllers;

import de.ait.events.models.Event;
import de.ait.events.service.EventsService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class EventsController {

    private final EventsService eventsService;

    public EventsController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    @PostMapping("/events")
    public String addEvent(@RequestParam("title")String title,
                           @RequestParam("startDate")  @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate startDate,
                           @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate endDate){
        eventsService.addEvent(title, startDate, endDate);

        return "redirect:/success_add.html";

    }
    @GetMapping("/events")
    public String getEventsPage(Model model){
        List<Event> events = eventsService.getAllEvents();
        model.addAttribute("eventsList",events);
        return "events_page";
    }
}
