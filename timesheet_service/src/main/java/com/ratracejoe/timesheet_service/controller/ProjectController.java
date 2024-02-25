package com.ratracejoe.timesheet_service.controller;

import com.ratracejoe.timesheet_service.entity.Project;
import com.ratracejoe.timesheet_service.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/hello/{name}")
    public String generateGreeting(@PathVariable("name") String name) {
        return projectService.generateGreeting(name);
    }

    @GetMapping("/")
    public List<Project> getAllProjects() {
        return projectService.getAll();
    }

    @PostMapping("/")
    public Project createProject(@RequestBody Project project) {
        Project p = projectService.createProject(project);
        p.setName("Foo");
        return p;
    }
}
