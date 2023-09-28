package com.ratracejoe.timesheet_service.util;

import com.ratracejoe.timesheet_service.entity.Project;
import com.ratracejoe.timesheet_service.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import java.util.UUID;
import java.util.stream.IntStream;

@Configuration
public class ProjectUtils {

    @Autowired
    private ProjectRepository projectRepository;

    public void injectProjects(int count) {
        IntStream
                .range(0, count)
                .mapToObj(i -> Project.builder()
                        .id(UUID.randomUUID())
                        .name(String.format("TestProject-%d", i))
                        .description(String.format("A witty description %d", i))
                        .build())
                .forEach(projectRepository::save);
    }

    public void clearProjects() {
        projectRepository.deleteAll();
    }
}
