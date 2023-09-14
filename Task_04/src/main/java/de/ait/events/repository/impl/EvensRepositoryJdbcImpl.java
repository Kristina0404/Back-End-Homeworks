package de.ait.events.repository.impl;

import de.ait.events.models.Event;
import de.ait.events.repository.EventsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Handler;

@RequiredArgsConstructor
@Repository
public class EvensRepositoryJdbcImpl implements EventsRepository {

    private final DataSource dataSource;
    @Override
    public Event findById(Long id) {
        return null;
    }

    @Override
    public List<Event> findAll() {
        return null;
    }

    @Override
    public void save(Event event) {

        SimpleJdbcInsert jdbcInsert= new SimpleJdbcInsert(dataSource)
                .usingGeneratedKeyColumns("id");

        jdbcInsert.withTableName("events");
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("title",event.getTitle());
        parameters.put("startDate",event.getStartDate());
        parameters.put("endDate",event.getEndDate());

        jdbcInsert.execute(parameters);
    }

    @Override
    public Event findOneByTitle(String title) {
        return null;
    }
}
