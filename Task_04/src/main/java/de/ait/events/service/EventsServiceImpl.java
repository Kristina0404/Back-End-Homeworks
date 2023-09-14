package de.ait.events.service;


import de.ait.events.dto.EventDto;
import de.ait.events.dto.NewEventDto;
import de.ait.events.models.Event;
import de.ait.events.repository.EventsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static de.ait.events.dto.EventDto.from;
@RequiredArgsConstructor
@Service
public class EventsServiceImpl implements EventsService {
    private final EventsRepository eventsRepository;



  /*  public Event addEvent(String title, LocalDate startDate, LocalDate endDate) {
        if (title == null || title.equals("") || title.equals(" ")) {
            throw new IllegalArgumentException("Title не может быть пустым");
        }

        if (startDate == null || startDate.equals("") || startDate.equals(" ")) {
            throw new IllegalArgumentException("startDate не может быть пустым");
        }
        if (endDate == null || endDate.equals("") || endDate.equals(" ")) {
            throw new IllegalArgumentException("endDate не может быть пустым");
        }

        Event existedEvent = eventsRepository.findOneByTitle(title);

        if (existedEvent != null) {
            throw new IllegalArgumentException("Событие с таким title уже есть");
        }

        Event event = new Event(title, startDate, endDate);

        eventsRepository.save(event);
        return event;
    }*/

    @Override
    public List<EventDto> getAllEvents() {
      return from( eventsRepository.findAll());

    }

    @Override
    public EventDto addEvent(NewEventDto newEvent) {
        Event event = Event.builder()
                .title(newEvent.getTitle())
                .startDate(newEvent.getStartDate())
                .endDate(newEvent.getEndDate())
                        .build();

        eventsRepository.save(event);
        return from(event);
    }


}