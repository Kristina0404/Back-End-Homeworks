package de.ait.event.repositories.impl;

import de.ait.event.models.Event;
import org.junit.jupiter.api.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("EventsRepositoryFileImpl is works ...")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
class EventsRepositoryFileImplTest {

    private static final String TEMP_EVENTS_FILE_NAME = "events_test.txt";
    private EventsRepositoryFileImpl eventsRepository;

    @BeforeEach
    public void setUp() throws Exception {

        createNewFileForTest(TEMP_EVENTS_FILE_NAME);

        eventsRepository = new EventsRepositoryFileImpl(TEMP_EVENTS_FILE_NAME);
    }

    @AfterEach
    public void tearDown() throws Exception {
        deleteFileAfterTest(TEMP_EVENTS_FILE_NAME);
    }

    @DisplayName("save():")
    @Nested
    class Save {

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        LocalDate startDate = LocalDate.parse("04.04.2023", dateFormatter);
        LocalDate endDate = LocalDate.parse("05.04.2023", dateFormatter);

        @Test
        public void writes_correct_line_to_file() throws Exception {


            Event event = new Event("gb", startDate, endDate);

            eventsRepository.save(event);

            String expected = "1|gb|2023-04-04|2023-04-05";

            BufferedReader reader = new BufferedReader(new FileReader(TEMP_EVENTS_FILE_NAME)); // открыли файл для чтения

            String actual = reader.readLine();

            reader.close();

            assertEquals(expected, actual);
        }
    }

    @DisplayName("findAll():")
    @Nested
    class FindAll {

        @Test
        public void returns_correct_list_of_events() throws Exception {

            BufferedWriter writer = new BufferedWriter(new FileWriter(TEMP_EVENTS_FILE_NAME)); // открыли файл для записи

            writer.write("1|gb|2023-04-04|2023-04-05");
            writer.newLine();
            writer.write("2|urlaub|2022-06-05|2022-06-30");
            writer.close();

            List<Event> expected = Arrays.asList(
                    new Event(1L, "gb",
                            LocalDate.parse("2023-04-04"),
                            LocalDate.parse("2023-04-05")),
                    new Event(2L, "urlaub",
                            LocalDate.parse("2022-06-05"),
                            LocalDate.parse("2022-06-30"))
            );

            List<Event> actual = eventsRepository.findAll();

            assertEquals(expected, actual);
        }
    }
    @DisplayName("findOneByTitle():")
    @Nested
    class findOneByTitle {

        @Test
        public void return_event_by_title() throws IOException {
            BufferedWriter writer = new BufferedWriter(new FileWriter(TEMP_EVENTS_FILE_NAME)); // открыли файл для записи

            writer.write("1|gb|04.04.2023|05.04.2023");
            writer.newLine();
            writer.write("2|urlaub|05.06.2022|30.06.2022");
            writer.close();
            Event expected =
                    new Event(1L, "gb",
                            LocalDate.parse("2023-04-04"),
                            LocalDate.parse("2023-04-05"));

            Event actual = eventsRepository.findOneByTitle("gb");


            assertEquals(expected, actual);

        }
    }
    private static void createNewFileForTest(String fileName) throws IOException {

        File file = new File(fileName);

        deleteIfExists(file);

        boolean result = file.createNewFile();

        if (!result) {
            throw new IllegalStateException("Problems with file create");
        }
    }

    private static void deleteFileAfterTest(String fileName) {
        File file = new File(fileName);

        deleteIfExists(file);
    }

    private static void deleteIfExists(File file) {
        if (file.exists()) {

            boolean result = file.delete();

            if (!result) {
                throw new IllegalStateException("Problems with file delete");
            }
        }
    }
}