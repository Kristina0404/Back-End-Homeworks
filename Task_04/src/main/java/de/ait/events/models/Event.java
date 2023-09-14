package de.ait.events.models;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder

public class Event {

    private Long id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;

}
