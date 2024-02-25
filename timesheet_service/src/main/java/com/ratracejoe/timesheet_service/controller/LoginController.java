package com.ratracejoe.timesheet_service.controller;

import com.ratracejoe.timesheet_service.model.LoggedInUser;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    LoggedInUser login(@RequestParam String username,
                       @RequestParam String password) {

        return new LoggedInUser(username, "LETMEIN");
    }
}
