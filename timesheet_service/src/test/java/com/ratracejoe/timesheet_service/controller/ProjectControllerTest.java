package com.ratracejoe.timesheet_service.controller;

import com.ratracejoe.timesheet_service.entity.Project;
import com.ratracejoe.timesheet_service.util.AbstractTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class ProjectControllerTest extends AbstractTest {

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
                new HttpEntity<>(null, authorizedHeaders(restTemplate)),
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
                        new HttpEntity<>(null, authorizedHeaders(restTemplate)),
                        new ParameterizedTypeReference<List<Project>>() {
                        });

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(5, response.getBody().size());
    }
}