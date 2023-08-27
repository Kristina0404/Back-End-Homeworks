package de.ait.event.controllers;

import de.ait.event.models.Event;
import de.ait.event.services.EventsService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class EventsController {

    private final Scanner scanner;

    private final EventsService eventsService;

    public EventsController(Scanner scanner, EventsService eventsService) {
        this.scanner = scanner;
        this.eventsService = eventsService;
    }

    public void addEvent() {
        System.out.println("Введите title");
        String title = scanner.nextLine();

        System.out.println("Введите дату начала мероприятия");
        String startDateStr = scanner.nextLine();
        System.out.println("Введите дату окончания мероприятия");
        String endDateStr = scanner.nextLine();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startDate = LocalDate.parse(startDateStr, dateFormatter);
        LocalDate endDate = LocalDate.parse(endDateStr, dateFormatter);
        Event event = eventsService.addEvent(title, startDate, endDate);

        System.out.println(event);
    }

    public void getAllEvents() {
        List<Event> events = eventsService.getAllEvents();
        System.out.println(events);
    }
}