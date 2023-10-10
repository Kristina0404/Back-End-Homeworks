package de.ait.events.controllers;

import de.ait.events.dto.*;
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

import javax.validation.Valid;
import java.util.List;

@Tags(value = @Tag(name = " Events"))
@RequiredArgsConstructor
@RestController
//@RequestMapping("/api/events")
@Schema(name = "Event", description = "Описание мероприятия")
public class EventsController {

    private final EventsService eventsService;


    @Operation(summary = "Получение всех мероприятий", description = "Доступно всем пользователям")
    @GetMapping("/api/events")
    public ResponseEntity<List<EventDto>> getAllEvents() {
        return ResponseEntity
                .ok(eventsService.getAllEvents());

    }

    @Operation(summary = "Добавление мероприятия", description = "Доступно администратору")
    @ApiResponses(
            @ApiResponse(responseCode = "201", description = "Мероприятие добавлено успешно",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventDto.class)))

    )
    @PostMapping("/api/events")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EventDto> addEvent(@RequestBody NewEventDto newEvent) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(eventsService.addEvent(newEvent));

    }

    @Operation(summary = "Получение одного мероприятия", description = "Доступно всем пользователям")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Запрос обработан успешно",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "Мероприятие не найдено",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponseDto.class)))
    })
    @GetMapping("/api/events/{events-id}")
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
                    description = "Пользователь не найден",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponseDto.class)))
    })
    @PutMapping("/api/events/{event-id}")

    public ResponseEntity<EventDto> updateEvent(@Parameter(description = "индификатор события", example = "7")
                                                @PathVariable("event-id") Long id,
                                                @RequestBody UpdateEventDto updateEvent) {
        return ResponseEntity
                .ok(eventsService.updateEvent(id, updateEvent));
    }

    @Operation(summary = "Удаление мероприятия", description = "Доступно администратору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Удаление прошло успешно",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "Мероприятие не найдено",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponseDto.class)))
    })
    @DeleteMapping("/api/events/{events-id}")
    public ResponseEntity<EventDto> deleteEvent(@Parameter(description = "индификатор события", example = "7")
                                                @PathVariable("event-id") Long id) {
        return ResponseEntity
                .ok(eventsService.deleteEvent(id));
    }

    @Operation(summary = "Добавление площадки проведения мероприятия",
            description = "Доступно администратору")
    @ApiResponses(
            @ApiResponse(responseCode = "201", description = "Место проведения мероприятия добавлено успешно",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VenueDto.class)))

    )
    @PostMapping("/api/venues")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<VenueDto> addVenue(@RequestBody NewVenueDto newVenue) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(eventsService.addVenue(newVenue));

    }

    @Operation(summary = "Добавление мероприятия на определённой площадке", description = "Доступно администратору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Мероприятие добавлено успешно",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VenueDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "Площадка  не найдена",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponseDto.class)))

            })
    @PostMapping("/api/venues/{venue-id}/events")
    public ResponseEntity<EventDto> addEventToVenue(@PathVariable("venue-id") Long venueId,
                                                    @RequestBody NewEventDto newEvent) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(eventsService.addEventToVenue(venueId, newEvent));
    }

    @Operation(summary = "Получение всех мероприятий на определённой площадке",
            description = "Доступно всем пользователям")
    @ApiResponses(
            @ApiResponse(responseCode = "404",
                    description = "Площадка  не найдена",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponseDto.class)))

    )
    @GetMapping("/api/venues/{venue-id}/events")
    public ResponseEntity<List<EventDto>> getEventsOfVenue(@PathVariable("venue-id") Long venueId) {
        return ResponseEntity
                .ok(eventsService.getEventsOfVenue(venueId));

    }

    @Operation(summary = "Удаление мероприятия из площадки проведения ",
            description = "Доступно администратору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Удаление прошло успешно",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "Мероприятие или площадка его проведения  не найдены",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponseDto.class)))
    })
    @DeleteMapping("/api/venues/{venue-id}/events/{event-id}")
    public ResponseEntity<EventDto> deleteEventFromVenue(@PathVariable("venue-id") Long venueId,
                                                         @PathVariable("event-id") Long eventId) {
        return ResponseEntity
                .ok(eventsService.deleteEventFromVenue(venueId, eventId));

    }

    @Operation(summary = "Обновление мероприятия на площадке его проведения ",
            description = "Доступно администратору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Обновление прошло успешно",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "Мероприятие или площадка  не найдены",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponseDto.class)))
    })

    @PutMapping("/api/venues/{venue-id}/events/{event-id}")
    public ResponseEntity<EventDto> updateEventInVenue(@PathVariable("venue-id") Long venueId,
                                                       @PathVariable("event-id") Long eventId,
                                                       @RequestBody @Valid UpdateEventDto updateEvent) {
        return ResponseEntity
                .ok(eventsService.updateEventInVenue(venueId, eventId, updateEvent));
    }

    @Operation(summary = "Добавление пользователя в мероприятие", description = "Доступно администратору")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "201", description = "Пользователь добавлен успешно",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VenueDto.class))),
            @ApiResponse(responseCode = "404",
                    description = "Мероприятие не найдено",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardResponseDto.class)))
    })
    @PostMapping("/api/events/{event-id}/users")
    public ResponseEntity<List<UserDto>> addUserToEvent(@PathVariable ("event-id") Long eventId,
    @RequestBody UserToEventDto userData) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(eventsService.addUserToEvent(eventId,userData));
    }

    @Operation(summary = "Получение всех пользователей  на определённом мероприятии",
            description = "Доступно всем пользователям")
    @GetMapping("/api/events/{event-id}/users")
    public ResponseEntity<List<UserDto>> getUsersOfEvents(@PathVariable("event-id") Long eventId){
        return ResponseEntity.ok(eventsService.getUsersOfEvents(eventId));
    }
    }
