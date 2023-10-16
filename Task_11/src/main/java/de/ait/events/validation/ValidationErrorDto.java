package de.ait.events.validation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "ValidationError", description = "Описание ошибки валидации")
public class ValidationErrorDto {
    @Schema(description = "название поля, в котором возникла ошибка", example = "startDate")
    private String field;
    @Schema(
            description = "название , которое ввел пользователь и которое было отвергнуто сервером",
            example ="22-22-22" )
    private String rejectedValue;
    @Schema(description ="сообщение, которое мы должны показать пользователю", example = "date format is 2022-01-01" )
    private String message;
}
