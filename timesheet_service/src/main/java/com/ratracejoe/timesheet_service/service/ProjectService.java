package com.ratracejoe.timesheet_service.service;

import com.ratracejoe.timesheet_service.entity.Project;
import com.ratracejoe.timesheet_service.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public String generateGreeting(String whoTo) {
        return String.format("Hello %s", whoTo);
    }

    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }
}
