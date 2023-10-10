package de.ait.events.repository;

import de.ait.events.models.User;
import de.ait.events.models.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenuesRepository extends JpaRepository<Venue,Long> {
}
