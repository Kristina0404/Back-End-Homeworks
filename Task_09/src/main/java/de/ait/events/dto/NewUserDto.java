package de.ait.events.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Schema(name = "NewUser", description = "Данные для регистрации")
public class NewUserDto {
    @Email
    @NotNull
    @Schema (description = "Users email", example = "user@mail.com")
    private String email;
    @NotNull
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$")
    @Schema(description = "Users password", example = "Qwerty007!")
    private String password;
}
