package com.ratracejoe.timesheet_service.service;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectService {
    public String generateGreeting(String whoTo) {
        return String.format("Hello %s", whoTo);
    }
}
