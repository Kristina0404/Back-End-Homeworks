package de.ait.events.repository;

import java.util.List;

public interface CrudRepository<T> {
    T findById(Long id);

    List<T> findAll();

    void save(T event);


}