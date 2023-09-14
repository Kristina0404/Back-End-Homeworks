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

public class Event {
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;

}
