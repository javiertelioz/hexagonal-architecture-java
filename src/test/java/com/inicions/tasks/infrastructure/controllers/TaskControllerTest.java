package com.inicions.tasks.infrastructure.controllers;

import com.inicions.tasks.TasksApplication;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(
        classes = TasksApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles(value = "test")
public class TaskControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    /*@Test
    @WithMockUser(roles = "INVITED")
    public void testGetAllTasks() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2UiLCJpYXQiOjE3MDc5Nzk3OTYsImV4cCI6MTcwODA2NjE5Nn0.2Tczkl9FChsZrpOD4gG02fguw5eNtAf_V90orgLeklg";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<List> response = this.restTemplate.exchange(
                "http://localhost:" + port + "/api/v1/tasks",
                HttpMethod.GET,
                entity,
                List.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, response.getBody().size());
    }*/

}
