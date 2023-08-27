package de.ait.event;

import de.ait.event.controllers.EventsController;
import de.ait.event.repositories.EventsRepository;
import de.ait.event.repositories.impl.EventsRepositoryFileImpl;
import de.ait.event.repositories.impl.EventsRepositoryListImpl;
import de.ait.event.services.EventsService;
import de.ait.event.services.impl.EventsServiceImpl;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EventsRepository eventsRepositoryList = new EventsRepositoryListImpl();
        EventsRepository eventsRepositoryFile = new EventsRepositoryFileImpl("events.txt");
        EventsService eventsService = new EventsServiceImpl(eventsRepositoryFile);
        EventsController eventsController = new EventsController(scanner, eventsService);

        boolean isRun = true;

        while (isRun) {
            String command = scanner.nextLine();
            switch (command) {
                case "/addEvent" ->
                        eventsController.addEvent();
                case "/events" ->
                        eventsController.getAllEvents();
                case "/exit" -> isRun = false;
            }
        }
    }
}