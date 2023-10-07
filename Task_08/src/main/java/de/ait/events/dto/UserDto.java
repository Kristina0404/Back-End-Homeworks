package de.ait.events.dto;

import de.ait.events.models.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "UserDto", description = "Данные пользователя")
public class UserDto {

    @Schema(description = "User's identifier", example= "1")
    private Long id;
    @Schema(description = "User's first name ",example = "Kristina")
    private String firstName;
    @Schema(description = "User's last name ",example = "Romanova")
    private String lastName;
    @Schema(description = "User's email",example = "user@mail.com")
    private String email;
    @Schema(description = "user's role", example = "USER")
    private String role;

    public static UserDto from(User user){
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole().toString())
                .build();
    }
}
