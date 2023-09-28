package de.ait.events.repository;

import de.ait.events.models.User;
import org.springdoc.core.providers.JavadocProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);
}
