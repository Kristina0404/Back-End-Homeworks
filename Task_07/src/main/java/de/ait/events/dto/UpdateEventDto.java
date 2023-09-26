package de.ait.events.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "UpdateEvent",description = "Информация для обновления названия и дат события " )
public class UpdateEventDto {
    @Schema(description = "Название события", example = "Ferien")
    private String title;
    @Schema(description = "Начало события", example = "2023-12-22")
    private LocalDate startDate;
    @Schema(description = "Окончание события", example = "2024-12-06")
    private LocalDate endDate;

}
