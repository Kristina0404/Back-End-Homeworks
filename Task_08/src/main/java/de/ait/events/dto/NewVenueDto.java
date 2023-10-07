package de.ait.events.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class NewVenueDto {

    @Schema(description = "название места проведения мероприятия", example = "Eishallе")
    @NotNull
    @NotBlank
    @NotEmpty
    private String nameOfVenue;
    @Schema(description = "адрес  проведения мероприятия", example = "11111 город, улица дом")
    private String address;
    @Schema(description = "номер телефона места проведения мероприятия", example = "0111111111")
    private String phone;
}
