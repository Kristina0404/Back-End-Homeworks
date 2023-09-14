package de.ait.events.controllers;

import de.ait.events.dto.EventDto;
import de.ait.events.dto.NewEventDto;
import de.ait.events.models.Event;
import de.ait.events.service.EventsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@RequiredArgsConstructor
@Controller
public class EventsController {

    private final EventsService eventsService;

  /*  @PostMapping("/events")
    public String addEvent(@RequestParam("title")String title,
                           @RequestParam("startDate")  @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate startDate,
                           @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate endDate){
        eventsService.addEvent(title, startDate, endDate);

        return "redirect:/success_add.html";

    }*/
    @GetMapping("/events")
    @ResponseBody
    public List<EventDto> getAllEvents(){
       return eventsService.getAllEvents();

    }
    @PostMapping("/events")
    @ResponseBody
    public EventDto addEvent(@RequestBody NewEventDto newEvent) {
      return eventsService.addEvent(newEvent);

    }


}
