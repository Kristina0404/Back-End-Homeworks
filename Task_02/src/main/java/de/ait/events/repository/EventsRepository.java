package de.ait.events.repository;


import de.ait.events.models.Event;

public interface EventsRepository extends CrudRepository<Event> {

    Event findOneByTitle(String title);
}