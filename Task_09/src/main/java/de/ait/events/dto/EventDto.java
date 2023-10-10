package de.ait.events.dto;

import de.ait.events.models.Event;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class EventDto {
    @Schema(description = "идентификатор мероприятия", example = "1")
    private Long id;
    @Schema(description = "название мероприятия", example = "отпуск")
    private String title;
    @Schema(description = "дата начала", example = "2022-02-02")
    private LocalDate startDate;
    @Schema(description = "дата окончания", example = "2022-02-02")
    private LocalDate endDate;
    @Schema(description = "идентификатор места проведения события", example = "1")
    private Long venueId;

    public static EventDto from(Event event) {
        return EventDto.builder()
                .id(event.getId())
                .title(event.getTitle())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .venueId(event.getVenue().getId())
                .build();
    }

    public static List<EventDto> from(Set<Event> events) {
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
