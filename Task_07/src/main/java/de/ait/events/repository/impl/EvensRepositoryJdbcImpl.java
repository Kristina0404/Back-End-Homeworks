package de.ait.events.repository.impl;

import de.ait.events.models.Event;
import de.ait.events.repository.EventsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Handler;

@RequiredArgsConstructor
@Repository
public class EvensRepositoryJdbcImpl implements EventsRepository {
    //language = SQL
    private static final String SQL_SELECT_ALL ="select * from events";

    //language = SQL
    private static final String SQL_SELECT_BY_ID ="select* from events where id=?";
    //language = SQL
    private static final String SQL_UPDATE_BY_ID = "update from events set title =?," +
            "startDate =?, endDate =? where id=?";

    // language =SQL
    private static final String SQL_DELETE_BY_ID ="delete  from events where id=?";
    private final DataSource dataSource;
    JdbcTemplate jdbcTemplate;

    private static final RowMapper<Event> EVENT_ROW_MAPPER =(row,rowNum) ->{
      Long id= row.getLong("id");
      String title = row.getString("title");
      LocalDate startDate = row.getDate("startDate").toLocalDate();
      LocalDate endDate = row.getDate("endDate").toLocalDate();

      return Event.builder()
              .id(id)
              .startDate(startDate)
              .endDate(endDate)
              .build();
    };
    @Override
    public Event findById(Long id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_ID,EVENT_ROW_MAPPER,id);
    }

    @Override
    public List<Event> findAll() {
       return jdbcTemplate.query(SQL_SELECT_ALL,EVENT_ROW_MAPPER);
    }

    @Override
    public void save(Event model) {

        SimpleJdbcInsert jdbcInsert= new SimpleJdbcInsert(dataSource)
                .usingGeneratedKeyColumns("id");

        jdbcInsert.withTableName("events");
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("title",model.getTitle());
        parameters.put("startDate",model.getStartDate());
        parameters.put("endDate",model.getEndDate());

        long generatedID = jdbcInsert.executeAndReturnKey(parameters).longValue();

        model.setId(generatedID);
    }

    @Override
    public Event findOneByTitle(String title) {
        return null;
    }

    @Override
    public Event findOneById(Long id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_ID,EVENT_ROW_MAPPER,id);
    }

    @Override
    public void update(Event model) {
        jdbcTemplate.update(SQL_UPDATE_BY_ID,model.getTitle(),
                model.getStartDate(),model.getEndDate(),model.getId());
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID,id);

    }
}
