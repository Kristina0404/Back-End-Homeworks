package de.ait.event.services;

import de.ait.event.models.Event;


import java.time.LocalDate;
import java.util.List;
public interface EventsService {
    Event addEvent(String title, LocalDate startDate, LocalDate endDate);

    List<Event> getAllEvents();
}