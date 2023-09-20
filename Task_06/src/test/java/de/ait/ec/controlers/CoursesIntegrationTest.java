package de.ait.ec.controlers;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Endpoin / courses is works:")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@ActiveProfiles("test")
class CoursesIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Nested
    @DisplayName("GET /courses:")
    public class GetCourses {

        @Test
        public void return_empty_list_of_courses() throws Exception {
            mockMvc.perform(get("/api/courses"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.size()", is(0)));

        }

        @Test
        @Sql(scripts = {"/sql/data.sql"})
        void return_list_of_courses() throws Exception {

            mockMvc.perform(get("/api/courses"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.size()", is(3)))
                    .andExpect(jsonPath("$[0].id", is(1)))
                    .andExpect(jsonPath("$[1].id", is(2)))
                    .andExpect(jsonPath("$[2].id", is(3)));
        }
    }

    @Nested
    @DisplayName("POST /courses:")
    public class PostCourse {
        @Test
        @Sql(scripts = {"/sql/data.sql"})
        void return_created_courses() throws Exception {

            mockMvc.perform(post("/api/courses")
                            .contentType("application/json")
                            .content("{\n" +
                                    "  \"title\": \"new Course4\",\n" +
                                    "  \"description\": \"description4\",\n" +
                                    "  \"beginDate\": \"2023-01-02\",\n" +
                                    "  \"endDate\": \"2023-12-31\",\n" +
                                    "  \"price\": \"1000\",\n" +
                                    "  \"state\": \"DRAFT\"\n" +
                                    "}"))
                    .andExpect(jsonPath("$.id",is(4)))
                    .andExpect(status().isCreated());

        }
    }
}