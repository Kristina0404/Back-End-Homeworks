package de.ait.events.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class NewVenueDto {

    @Schema(description = "название места проведения мероприятия", example = "Eishallе")
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 50)
    private String nameOfVenue;
    @Schema(description = "адрес  проведения мероприятия", example = "11111 город, улица дом")
    @Size(max = 200)
    private String address;
    @Schema(description = "номер телефона места проведения мероприятия", example = "0111111111")
    @Size(max =15)
    private String phone;
}
