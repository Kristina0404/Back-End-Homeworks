package de.ait.events.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Endpoint/ Events is works: ")
class EventsIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Nested
    @DisplayName("GET/events")

        public class GetEvents{
        @Test
        @Sql(scripts={"/sql/schema.sql"})
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)

            public void return_list_of_events() throws Exception{
            mockMvc.perform(get("/api/events"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.size()",is (3)))
                    .andExpect(jsonPath("$.[0].id",is(1)))
                    .andExpect(jsonPath("$.[1].id",is(2)))
                    .andExpect(jsonPath("$.[2].id",is(3)));
        }
        }
        @Nested
    @DisplayName("POST/ events")
        public class PostEvents {
        @Test
        @Sql(scripts={"/sql/schema.sql"})
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
            public void return_created_event() throws Exception{
            mockMvc.perform((post("/api/events"))
                    .contentType("application/json")
                    .content("{\n"+
                            "\"title\": \"Heiko Fischer Pokal\",\n" +
                            "\"startDate\":\"2024-02-09\",\n" +
                            "\"endDate\":\"2024-02-13\"\n" +
        "}"))
            .andExpect(jsonPath("$.id",is(4)))
                    .andExpect(status().isCreated());

        }
        }


}