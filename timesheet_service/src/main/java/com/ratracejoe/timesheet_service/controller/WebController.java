package com.ratracejoe.timesheet_service.controller;

import com.ratracejoe.timesheet_service.entity.Project;
import com.ratracejoe.timesheet_service.service.ProjectService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class WebController {

    @Autowired
    private ProjectService projectService;

    @GetMapping(path = "/web")
    public String index() {
        return "external";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws Exception {
        request.logout();
        return "redirect:/";
    }

    @GetMapping(path = "/projects")
    public String projects(Principal principal, Model model) {
        addProjects();
        Iterable<Project> projects = projectService.getAll();
        model.addAttribute("projects", projects);
        model.addAttribute("username", principal.getName());
        return "projects";
    }

    // add customers for demonstration
    public void addProjects() {
        projectService.createProject(new Project(UUID.randomUUID(), "Foo1", "First guy"));
        projectService.createProject(new Project(UUID.randomUUID(), "Foo2", "Second guy"));
        projectService.createProject(new Project(UUID.randomUUID(), "Foo3", "Third guy"));
    }
}
