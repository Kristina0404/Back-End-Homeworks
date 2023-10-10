package de.ait.events.repository;


import de.ait.events.models.Event;
import de.ait.events.models.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface EventsRepository extends JpaRepository<Event,Long> {

    Set<Event> findAllByVenueOrderById(Venue venue);
    Optional<Event> findByVenueAndId(Venue venue, Long eventId);
}