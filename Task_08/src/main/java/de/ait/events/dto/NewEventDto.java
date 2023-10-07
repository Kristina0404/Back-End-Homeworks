package de.ait.events.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
@Data
@Schema(name = "NewEvent")
public class NewEventDto {
    @Schema(description = "название мероприятия", example = "Java")
    @NotNull
    @NotBlank
    @NotEmpty
    private String title;
    @Schema(description = "дата начала", example = "2022-02-02")
    @Pattern(regexp = "^(?:(?:19|20)\\d\\d)-(?:0[1-9]|1[0-2])-(?:0[1-9]|1\\d|2\\d|3[0-1])$")
    private LocalDate startDate;
    @Schema(description = "дата окончания", example = "2022-02-02")
    @Pattern(regexp = "^(?:(?:19|20)\\d\\d)-(?:0[1-9]|1[0-2])-(?:0[1-9]|1\\d|2\\d|3[0-1])$")
    private LocalDate endDate;


}
