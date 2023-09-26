package de.ait.events.service;



import de.ait.events.dto.EventDto;
import de.ait.events.dto.NewEventDto;
import de.ait.events.dto.UpdateEventDto;
import de.ait.events.models.Event;

import java.time.LocalDate;
import java.util.List;
public interface EventsService {
   // Event addEvent(String title, LocalDate startDate, LocalDate endDate);

    List<EventDto> getAllEvents();

    EventDto addEvent(NewEventDto newEvent);

    EventDto getEvent(Long id);

    EventDto updateEvent(Long id, UpdateEventDto updateEvent);

    EventDto deleteEvent(Long id);
}