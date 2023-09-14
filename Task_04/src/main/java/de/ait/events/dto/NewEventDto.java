package de.ait.events.dto;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class NewEventDto {
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
}
