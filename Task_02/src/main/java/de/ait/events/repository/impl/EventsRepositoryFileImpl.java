package de.ait.events.repository.impl;


import de.ait.events.models.Event;
import de.ait.events.repository.EventsRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.time.LocalDate;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;
@Repository
public class EventsRepositoryFileImpl implements EventsRepository {

    private final String fileName;

    public EventsRepositoryFileImpl(@Value("C:\\Back-end-codes\\Back-End-Homeworks\\Task_02\\events.txt") String fileName) {
        this.fileName = fileName;
    }
    @Override
    public Event findById(Long id) {
        return null;
    }
    @Override
    public List<Event> findAll() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            return reader.lines()
                    .map(line -> line.split("\\|"))
                    .map(parsed -> new Event( parsed[0],
                            LocalDate.parse(parsed[1]),
                            LocalDate.parse(parsed[2])))
                    .collect(Collectors.toList());


        } catch (IOException e) {
            throw new IllegalStateException("Проблемы с чтением из файла: " + e.getMessage());
        }
    }

    @Override
    public void save(Event event) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {

            writer.write( event.getTitle() + "|"
                    + event.getStartDate() + "|" + event.getEndDate());
            writer.newLine();


        } catch (IOException e) {
            throw new IllegalStateException("Проблемы с записью в файл: " + e.getMessage());
        }

    }

    @Override
    public Event findOneByTitle(String title) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            //DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            return reader.lines()
                    .map(line -> line.split("\\|"))
                    .filter(parsed -> parsed[0].equals(title))
                    .findFirst()
                    .map(parsed -> {
                        try {
                            LocalDate startDate = LocalDate.parse(parsed[1]);
                            LocalDate endDate = LocalDate.parse(parsed[2]);
                            return new Event(
                                    parsed[0],
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