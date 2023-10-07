package de.ait.events.dto;

import de.ait.events.models.Event;
import de.ait.events.models.Venue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VenueDto {
    @Schema(description = "идентификатор места проведения мероприятия", example = "1")
    private Long id;
    @Schema(description = "название места проведения мероприятия", example = "Eishallе")
    private String nameOfVenue;
    @Schema(description = "адрес  проведения мероприятия", example = "11111 город, улица дом")
    private String address;
    @Schema(description = "номер телефона места проведения мероприятия", example = "0111111111")
    private String phone;

    public static VenueDto from(Venue venue) {
        return VenueDto.builder()
                .id(venue.getId())
                .nameOfVenue(venue.getNameOfVenue())
                .address(venue.getAddress())
                .phone(venue.getPhone())
                .build();
    }

    public static List<VenueDto> from(List<Venue> venues) {
        return venues.stream()
                .map(VenueDto::from)
                .collect(Collectors.toList());
    }


}
