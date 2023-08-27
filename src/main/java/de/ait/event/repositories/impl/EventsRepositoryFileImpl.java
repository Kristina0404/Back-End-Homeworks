package de.ait.event.repositories.impl;

import de.ait.event.models.Event;
import de.ait.event.repositories.EventsRepository;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

public class EventsRepositoryFileImpl implements EventsRepository {

    private final String fileName;

    public EventsRepositoryFileImpl(String fileName) {
        this.fileName = fileName;
    }

    private Long generatedId = 1L;

    @Override
    public Event findById(Long id) {
        return null;
    }

    @Override
    public List<Event> findAll() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            return reader.lines()
                    .map(line -> line.split("\\|"))
                    .map(parsed -> new Event(Long.parseLong(parsed[0]), parsed[1],
                            LocalDate.parse(parsed[2]),
                            LocalDate.parse(parsed[3])))
                    .collect(Collectors.toList());


        } catch (IOException e) {
            throw new IllegalStateException("Проблемы с чтением из файла: " + e.getMessage());
        }
    }

    @Override
    public void save(Event event) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {

            event.setId(generatedId);

            writer.write(event.getId() + "|" + event.getTitle() + "|"
                    + event.getStartDate() + "|" + event.getEndDate());
            writer.newLine();


        } catch (IOException e) {
            throw new IllegalStateException("Проблемы с записью в файл: " + e.getMessage());
        }
        generatedId++;
    }

    @Override
    public Event findOneByTitle(String title) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            return reader.lines()
                    .map(line -> line.split("\\|"))
                    .filter(parsed -> parsed[1].equals(title))
                    .findFirst()
                    .map(parsed -> {
                        try {
                            LocalDate startDate = LocalDate.parse(parsed[2], dateFormatter);
                            LocalDate endDate = LocalDate.parse(parsed[3], dateFormatter);
                            return new Event(
                                    Long.parseLong(parsed[0]),
                                    parsed[1],
                                    startDate,
                                    endDate
                            );
                        } catch (DateTimeParseException e) {
                            System.err.println("Error parsing date: " + e.getMessage());
                            return null;
                        }
                    })
                    .orElse(null);

        } catch (IOException e) {
            throw new IllegalStateException("Проблемы с чтением из файла: " + e.getMessage());
        }
    }
}