package com.ratracejoe.timesheet_service.controller;

import com.ratracejoe.timesheet_service.model.LoggedInUser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/getUser")
    public String getUser(HttpServletRequest request) {
        return request.getUserPrincipal().getName();
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request) throws Exception {
        request.logout();
    }
}
