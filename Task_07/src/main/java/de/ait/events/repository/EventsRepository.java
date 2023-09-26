package de.ait.events.repository;


import de.ait.events.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepository extends JpaRepository<Event,Long> {

}