package de.ait.event.services.impl;

import de.ait.event.models.Event;
import de.ait.event.repositories.EventsRepository;
import de.ait.event.services.EventsService;

import java.time.LocalDate;
import java.util.List;

public class EventsServiceImpl implements EventsService {
    private final EventsRepository eventsRepository;

    public EventsServiceImpl(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;

    }

    public Event addEvent(String title, LocalDate startDate, LocalDate endDate) {
        if (title == null || title.equals("") || title.equals(" ")) {
            throw new IllegalArgumentException("Title не может быть пустым");
        }

        if (startDate == null || startDate.equals("") || startDate.equals(" ")) {
            throw new IllegalArgumentException("startDate не может быть пустым");
        }
        if (endDate == null || endDate.equals("") || endDate.equals(" ")) {
            throw new IllegalArgumentException("endDate не может быть пустым");
        }

        Event existedEvent = eventsRepository.findOneByTitle(title);

        if (existedEvent != null) {
            throw new IllegalArgumentException("Событие с таким title уже есть");
        }

        Event event = new Event(title, startDate, endDate);

        eventsRepository.save(event);
        return event;
    }

    @Override
    public List<Event> getAllEvents() {
        return eventsRepository.findAll();
    }
}