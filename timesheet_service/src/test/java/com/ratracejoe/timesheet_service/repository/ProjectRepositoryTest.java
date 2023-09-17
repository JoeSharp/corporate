package com.ratracejoe.timesheet_service.repository;

import com.ratracejoe.timesheet_service.entity.Project;
import com.ratracejoe.timesheet_service.util.ProjectUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProjectRepositoryTest {
    @Autowired
    private ProjectRepository repository;

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
    public void orderByName() {
        List<Project> results = repository.findAllByOrderByName();
        assertThat(results.size()).isEqualTo(5);
        assertEquals("TestProject-0", results.get(0).getName());
        assertEquals("TestProject-4", results.get(4).getName());
    }
}
