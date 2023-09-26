package de.ait.events.service;


import de.ait.events.dto.EventDto;
import de.ait.events.dto.NewEventDto;
import de.ait.events.dto.UpdateEventDto;
import de.ait.events.exceptions.RestException;
import de.ait.events.models.Event;
import de.ait.events.repository.EventsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static de.ait.events.dto.EventDto.from;
@RequiredArgsConstructor
@Service
public class EventsServiceImpl implements EventsService {
    private final EventsRepository eventsRepository;

    @Override
    public List<EventDto> getAllEvents() {
      return from( eventsRepository.findAll());

    }

    @Override
    public EventDto addEvent(NewEventDto newEvent) {
        Event event = Event.builder()
                .title(newEvent.getTitle())
                .startDate(newEvent.getStartDate())
                .endDate(newEvent.getEndDate())
                        .build();

        eventsRepository.save(event);
        return from(event);
    }

    @Override
    public EventDto getEvent(Long id) {

        Event event = eventsRepository.findById(id)
                .orElseThrow(()->
                        new RestException(HttpStatus.NOT_FOUND,"Event with id < " + id + "not found"));

        return from(event);
    }

    @Override
    public EventDto updateEvent(Long id, UpdateEventDto updateEvent) {
        Event eventForUpdate= eventsRepository.findById(id)
                .orElseThrow(()->
                        new RestException(HttpStatus.NOT_FOUND,"Event with id < " + id + "not found"));
        eventForUpdate.setTitle(updateEvent.getTitle());
        eventForUpdate.setStartDate(updateEvent.getStartDate());
        eventForUpdate.setEndDate(updateEvent.getEndDate());
        eventsRepository.update(eventForUpdate);
        return from(eventForUpdate);
    }

    @Override
    public EventDto deleteEvent(Long id) {
        Event forDelete =eventsRepository.findById(id)
                .orElseThrow(()->
                        new RestException(HttpStatus.NOT_FOUND,"Event with id < " + id + "not found"));
        eventsRepository.deleteById(id);
        return from(forDelete);
    }


}