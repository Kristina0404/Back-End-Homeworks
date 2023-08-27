package de.ait.event.repositories.impl;

import de.ait.event.models.Event;
import de.ait.event.repositories.EventsRepository;

import java.util.ArrayList;
import java.util.List;

public class EventsRepositoryListImpl implements EventsRepository {

    private final List<Event> events = new ArrayList<>();
    private Long generatedId = 1L;

    @Override
    public Event findById(Long id) {
        return null;
    }

    @Override
    public List<Event> findAll() {
        return new ArrayList<>(events);
    }

    @Override
    public void save(Event event) {
        events.add(event);

        event.setId(generatedId);
        generatedId++;
    }

    @Override
    public Event findOneByTitle(String title) {

        return events.stream()
                .filter(event -> event.getTitle().equals(title))
                .findFirst()
                .orElse(null);
    }
}