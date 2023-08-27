package de.ait.event.services.impl;

import de.ait.event.models.Event;
import de.ait.event.repositories.EventsRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("EventsServiceImpl is works ...")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
class EventsServiceImplTest {

    private static final String EXIST_EVENT_TITLE = "gb";
    private static final String NOT_EXIST_EVENT_TITLE = "haha";
    private static final LocalDate START_DATE = LocalDate.parse("2023-04-04");
    private static final LocalDate END_DATE = LocalDate.parse("2023-04-05");
    ;
    private static final Event NOT_EXIST_EVENT = new Event(NOT_EXIST_EVENT_TITLE, START_DATE, END_DATE);
    private static final Event EXIST_Event = new Event(EXIST_EVENT_TITLE, START_DATE, END_DATE);

    private EventsServiceImpl eventsService;
    private EventsRepository eventsRepository;

    @BeforeEach
    public void setUp() {
        eventsRepository = Mockito.mock(EventsRepository.class);
        when(eventsRepository.findOneByTitle(EXIST_EVENT_TITLE)).thenReturn(EXIST_Event);
        // когда у usersRepository вызываем findOneByEmail с аргументом marsel@gmail.com возвращается null
        when(eventsRepository.findOneByTitle(NOT_EXIST_EVENT_TITLE)).thenReturn(null);
        this.eventsService = new EventsServiceImpl(eventsRepository);
    }

    @Nested
    @DisplayName(("addUser():"))
    class AddEvent {
        @Test
        public void on_incorrect_title_throws_exception() {
            assertThrows(IllegalArgumentException.class, () -> eventsService.addEvent(null, START_DATE, END_DATE));
        }

        @Test
        public void on_incorrect_startDate_throws_exception() {
            assertThrows(DateTimeParseException.class, () -> eventsService.addEvent("gb", LocalDate.parse(" "), END_DATE));
        }

        @Test
        public void on_existed_event_throws_exception() {
            assertThrows(IllegalArgumentException.class, () -> eventsService.addEvent(EXIST_EVENT_TITLE, START_DATE, END_DATE));
        }

        @Test
        public void returns_created_event() {
            Event actual = eventsService.addEvent(NOT_EXIST_EVENT_TITLE, START_DATE, END_DATE); // на вход подаем пользователя без id

            verify(eventsRepository).save(NOT_EXIST_EVENT);

            assertNotNull(actual);
        }
    }

}