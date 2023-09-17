package com.ratracejoe.timesheet_service.controller;

import com.ratracejoe.timesheet_service.entity.Project;
import com.ratracejoe.timesheet_service.util.ProjectUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProjectControllerTest {

    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProjectUtils utils;

    @BeforeEach
    public void beforeEach() {
        utils.injectProjects(5);
    }

    @AfterEach
    public void afterEach() {
        utils.clearProjects();
    }

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        var response = restTemplate.exchange("/project/hello/Joe",
                HttpMethod.GET,
                new HttpEntity<>(null, new HttpHeaders()),
                String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Hello Joe", response.getBody());
    }

    @Test
    public void getProjects() throws Exception {
        var response =
                restTemplate.exchange("/project/",
                HttpMethod.GET,
                new HttpEntity<>(null, new HttpHeaders()),
                new ParameterizedTypeReference<List<Project>>() {
                });

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(5, response.getBody().size());
    }
}