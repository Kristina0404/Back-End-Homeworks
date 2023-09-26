package de.ait.events.controllers;

import de.ait.events.dto.EventDto;
import de.ait.events.dto.NewEventDto;
import de.ait.events.dto.StandardResponseDto;
import de.ait.events.dto.UpdateEventDto;
import de.ait.events.models.Event;
import de.ait.events.service.EventsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tags(value = @Tag(name = " Events"))
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/events")
@Schema(name = "Event", description = "Описание события")
public class EventsController {

    private final EventsService eventsService;


    @Operation(summary = "Получение всех событий", description = "Доступно всем пользователям")
    @GetMapping
    public ResponseEntity<List<EventDto>> getAllEvents() {
        return ResponseEntity
                .ok(eventsService.getAllEvents());

    }

    @Operation(summary = "Добавление события", description = "Доступно администратору")
    @ApiResponses(
            @ApiResponse(responseCode = "201", description = "Событие добавлено успешно",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventDto.class)))

    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EventDto> addEvent(@RequestBody NewEventDto newEvent) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(eventsService.addEvent(newEvent));

    }

    @Operation(summary = "Получение одного события", description = "Доступно всем пользователям")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Запрос обработан успешно",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "Событие не найдено",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponseDto.class)))
    })
    @GetMapping("/{events-id}")
    public ResponseEntity<EventDto> getEvent(@Parameter(description = "индификатор события", example = "7")
                                             @PathVariable("event-id") Long id) {
        return ResponseEntity
                .ok(eventsService.getEvent(id));
    }


    @Operation(summary = "Обновление пользователя", description = "Доступно администратору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Обновление прошло успешно",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "Событие не найдено",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponseDto.class)))
    })
    @PutMapping("/{event-id}")

    public ResponseEntity<EventDto> updateEvent(@Parameter(description = "индификатор события", example = "7")
                                @PathVariable("event-id") Long id,
                                @RequestBody UpdateEventDto updateEvent) {
        return ResponseEntity
                .ok(eventsService.updateEvent(id, updateEvent));
    }

    @Operation(summary = "Удаление события", description = "Доступно администратору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Удаление прошло успешно",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "Событие не найдено",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponseDto.class)))
    })
    @DeleteMapping("/{events-id}")
    public ResponseEntity<EventDto> deleteEvent(@Parameter(description = "индификатор события", example = "7")
                                @PathVariable("event-id") Long id) {
        return ResponseEntity
                .ok(eventsService.deleteEvent(id));
    }
}
