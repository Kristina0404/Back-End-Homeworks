package de.ait.events.dto;

import de.ait.events.models.Event;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class EventDto {

    private String title;
    private LocalDate startDate;
    private LocalDate endDate;

    public static EventDto from(Event event) {
       return new EventDto(event.getTitle(),event.getStartDate(),event.getEndDate());
    }

    public static List<EventDto> from(List<Event> events) {
       /* List <EventDto> eventDtos = new ArrayList<>();
        for (Event event : events){
            EventDto eventDto = from(event);
            eventDtos.add(eventDto);
        }
        return eventDtos;*/
        return events.stream()
                .map(EventDto::from).collect(Collectors.toList());
    }
}
