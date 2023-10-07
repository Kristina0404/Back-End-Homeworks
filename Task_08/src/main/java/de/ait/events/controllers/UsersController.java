package de.ait.events.controllers;

import com.fasterxml.classmate.members.ResolvedParameterizedMember;
import de.ait.events.dto.EventDto;
import de.ait.events.dto.NewUserDto;
import de.ait.events.dto.StandardResponseDto;
import de.ait.events.dto.UserDto;
import de.ait.events.service.UsersService;
import de.ait.events.validation.ValidationErrorsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequiredArgsConstructor
@Tags(
        @Tag(name = "users")
)
@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;

    @Operation(summary = "Регистрация пользователя", description = "Доступно всем пользователям. По умолчанию роль- USER")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Пользователь зарегистрирован",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))
            ),
            @ApiResponse(responseCode = "400",
                    description = "Ошибка валидации ",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorsDto.class))),
            @ApiResponse(responseCode = "409",
                    description = "Пользователь с таким емайл уже есть",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponseDto.class))
            )
    })
    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid NewUserDto newUser){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usersService.register(newUser));
    }
}
