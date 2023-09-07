package de.ait.event.repositories;

import java.util.List;

public interface CrudRepository<T> {
    T findById(Long id);

    List<T> findAll();

    void save(T event);


}