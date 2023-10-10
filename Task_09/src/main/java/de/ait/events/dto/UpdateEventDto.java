package de.ait.events.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "UpdateEvent",description = "Информация для обновления названия и дат события " )
public class UpdateEventDto {
    @Schema(description = "Название события", example = "Ferien")
    @NotBlank
    @NotEmpty
    private String title;
    @Schema(description = "Начало события", example = "2023-12-22")
   // @NotBlank
    //@NotEmpty
    @NotNull
    private LocalDate startDate;
    @Schema(description = "Окончание события", example = "2024-12-06")
    //@NotBlank
    //@NotEmpty
    @NotNull
    private LocalDate endDate;

}
