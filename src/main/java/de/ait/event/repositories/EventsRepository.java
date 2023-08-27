package de.ait.event.repositories;

import de.ait.event.models.Event;


public interface EventsRepository extends CrudRepository<Event> {

    Event findOneByTitle(String title);
}