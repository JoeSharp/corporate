package com.ratracejoe.timesheet_service.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProjectServiceTest {
    @Autowired
    private ProjectService projectService;

    @Test
    void testGreeting() {
        // When
        String result = projectService.generateGreeting("Joe");

        // Then
        assertThat(result).isEqualTo("Hello Joe");
    }
}