package de.ait.events.repository;

import de.ait.events.models.Event;
import de.ait.events.models.User;
import org.springdoc.core.providers.JavadocProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface UsersRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);

    Set<User> findAllByEventsContainsOrderById(Event event);

    Optional<User> findByEmail(String email);
}
