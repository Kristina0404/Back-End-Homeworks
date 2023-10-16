package de.ait.events.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
@DisplayName("Endpoint/ Users is works: ")
//@DisplayNameGeneration(value = DisplayNameGeneration.ReplaceUnderscores.class)
@ActiveProfiles("test")
public class UsersIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Nested
    @DisplayName("POST/users/register: ")
    public class RegisterUser{

        @Test
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        public void return_created_user() throws Exception {
            mockMvc.perform(post("/api/users/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\n" +
                            "  \"email\": \"romanova.kristina@gmail.com\",\n" +
                            "  \"password\": \"Qwerty007!\"\n" +
                            "}"))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id",is(1)))
                    .andExpect(jsonPath("$.role",is("USER")));
        }
        @Test
        public void return_400_for_bad_format_email() throws Exception {
            mockMvc.perform(post("/api/users/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                    "  \"email\": \"romanova.kristinagmail.com\",\n" +
                                    "  \"password\": \"Qwerty007!\"\n" +
                                    "}"))
                    .andExpect(status().isBadRequest());

        }
        @Test
        @Sql(scripts = "/sql/data.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        public void return_409_for_existed_email() throws Exception {
            mockMvc.perform(post("/api/users/register")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                    "  \"email\": \"romanova.kristina@gmail.com\",\n" +
                                    "  \"password\": \"Qwerty007!\"\n" +
                                    "}"))
                    .andExpect(status().isConflict());

        }
    }

}