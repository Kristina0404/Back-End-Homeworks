package de.ait.events.service;


import de.ait.events.dto.*;
import de.ait.events.exceptions.RestException;
import de.ait.events.models.Event;
import de.ait.events.models.Venue;
import de.ait.events.repository.EventsRepository;
import de.ait.events.repository.VenuesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static de.ait.events.dto.EventDto.from;
import static de.ait.events.dto.VenueDto.from;

@RequiredArgsConstructor
@Service
public class EventsServiceImpl implements EventsService {
    private final EventsRepository eventsRepository;
    private final VenuesRepository venuesRepository;

    @Override
    public List<EventDto> getAllEvents() {
        List<Event> events = eventsRepository.findAll();
        return from((Set<Event>) events);

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
                .orElseThrow(() ->
                        new RestException(HttpStatus.NOT_FOUND, "Event with id < " + id + "not found"));

        return from(event);
    }

    @Override
    public EventDto updateEvent(Long id, UpdateEventDto updateEvent) {
        Event eventForUpdate = eventsRepository.findById(id)
                .orElseThrow(() ->
                        new RestException(HttpStatus.NOT_FOUND, "Event with id < " + id + "not found"));
        eventForUpdate.setTitle(updateEvent.getTitle());
        eventForUpdate.setStartDate(updateEvent.getStartDate());
        eventForUpdate.setEndDate(updateEvent.getEndDate());
        eventsRepository.save(eventForUpdate);

        return from(eventForUpdate);
    }

    @Override
    public EventDto deleteEvent(Long id) {
        Event forDelete = eventsRepository.findById(id)
                .orElseThrow(() ->
                        new RestException(HttpStatus.NOT_FOUND, "Event with id < " + id + "not found"));
        eventsRepository.deleteById(id);
        return from(forDelete);
    }
    @Override
    public VenueDto addVenue(NewVenueDto newVenue) {
        Venue venue = Venue.builder()
                .nameOfVenue(newVenue.getNameOfVenue())
                .address(newVenue.getAddress())
                .phone(newVenue.getPhone())
                .build();

        venuesRepository.save(venue);
        return from(venue);
    }

    @Override
    public EventDto addEventToVenue(Long venueId, NewEventDto newEvent) {
        Venue venue = venuesRepository.findById(venueId)
                .orElseThrow(() ->
                        new RestException(HttpStatus.NOT_FOUND, "Venue with id < " + venueId + " > not found"));

        Event event = Event.builder()
                .title(newEvent.getTitle())
                .startDate(newEvent.getStartDate())
                .endDate(newEvent.getEndDate())
                .venue(venue)
                .build();
        eventsRepository.save(event);
        return from(event);
    }
    @Override
    public List<EventDto> getEventsOfVenue(Long venueId) {
        Venue venue =venuesRepository.findById(venueId)
                .orElseThrow(()->new RestException(HttpStatus.NOT_FOUND,
                        "Venue with id< " + venueId + "> not found" ));
        Set<Event> events = venue.getEvents();
        return from(events);
    }
}