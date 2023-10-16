package de.ait.events.service;


import de.ait.events.dto.*;
import de.ait.events.exceptions.RestException;
import de.ait.events.models.Event;
import de.ait.events.models.User;
import de.ait.events.models.Venue;
import de.ait.events.repository.EventsRepository;
import de.ait.events.repository.UsersRepository;
import de.ait.events.repository.VenuesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static de.ait.events.dto.EventDto.from;
import static de.ait.events.dto.VenueDto.from;
import static de.ait.events.dto.UserDto.from;

@RequiredArgsConstructor
@Service
public class EventsServiceImpl implements EventsService {
    private final EventsRepository eventsRepository;
    private final VenuesRepository venuesRepository;
    private final UsersRepository usersRepository;

    @Override
    public List<EventDto> getAllEvents() {
        List<Event> events = eventsRepository.findAll();
        return from( events);

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
        Venue venue = getVenueOfThrow(venueId);
        Event event;

        if (newEvent.getExistsEventId() == null) {

            event = Event.builder()
                    .title(newEvent.getTitle())
                    .startDate(newEvent.getStartDate())
                    .endDate(newEvent.getEndDate())
                    .venue(venue)
                    .build();
        } else {
            event = eventsRepository.findById(newEvent.getExistsEventId())
                    .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND,
                            "Event with id < " + newEvent.getExistsEventId() + " > not found"));
            event.setVenue(venue);
        }
        eventsRepository.save(event);
        return from(event);
    }

    @Override
    public List<EventDto> getEventsOfVenue(Long venueId) {
        Venue venue = getVenueOfThrow(venueId);
        Set<Event> events = eventsRepository.findAllByVenueOrderById(venue);
        return from(events);
    }

    @Override
    public EventDto deleteEventFromVenue(Long venueId, Long eventId) {
        Venue venue = getVenueOfThrow(venueId);
        Event event = eventsRepository.findByVenueAndId(venue, eventId)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND,
                        "Event with id < " + eventId + " > not found in venue with id < " + venueId + " >"));
        event.setVenue(null);
        eventsRepository.save(event);
        return from(event);

    }

    @Override
    public EventDto updateEventInVenue(Long venueId, Long eventId, UpdateEventDto updateEvent) {

        Venue venue = getVenueOfThrow(venueId);
        Event event = eventsRepository.findByVenueAndId(venue, eventId)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND,
                        "Event with id < " + eventId + "> not found in venue with id < " + venueId + ">"));
        event.setTitle(updateEvent.getTitle());
        if (event.getStartDate() != null) {
            event.setStartDate(updateEvent.getStartDate());
        } else {
            event.setStartDate(null);
        }
        if (event.getEndDate() != null) {
            event.setEndDate(updateEvent.getEndDate());
        } else {
            event.setStartDate(null);
        }
        eventsRepository.save(event);
        return from(event);
    }

    @Override
    public List<UserDto> addUserToEvent(Long eventId, UserToEventDto userData) {
        Event event = getEventOrThrow(eventId);
        User user = usersRepository.findById(userData.getUserId())
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND,
                        "User with id < " + userData.getUserId() + "> not found"));
        if (!user.getEvents().add(event)) {
            throw new RestException(HttpStatus.BAD_REQUEST, "User with id < " + user.getId() +
                    " > already in event with id < " + event.getId() + " > ");
        }
        usersRepository.save(user);
        Set<User> usersOfEvent = usersRepository.findAllByEventsContainsOrderById(event);
        return from(usersOfEvent);
    }

    @Override
    public List<UserDto> getUsersOfEvents(Long eventId) {
        Event event = getEventOrThrow(eventId);
        Set<User> usersOfEvents = usersRepository.findAllByEventsContainsOrderById(event);
        return from(usersOfEvents);
    }

    private Event getEventOrThrow(Long eventId) {
        return eventsRepository.findById(eventId)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND,
                        "Event with id < " + eventId + "> not found "));
    }

    private Venue getVenueOfThrow(Long venueId) {
        return venuesRepository.findById(venueId)
                .orElseThrow(() ->
                        new RestException(HttpStatus.NOT_FOUND, "Venue with id < " + venueId + " > not found"));
    }
}