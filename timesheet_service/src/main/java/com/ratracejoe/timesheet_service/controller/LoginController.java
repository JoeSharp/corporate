package com.ratracejoe.timesheet_service.controller;

import com.ratracejoe.timesheet_service.model.LoggedInUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {
    private final ClientRegistrationRepository clientRegistrationRepository;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    LoggedInUser login(@RequestParam String username,
                       @RequestParam String password) {

        ClientRegistration reg = clientRegistrationRepository.findByRegistrationId("keycloak");

        log.info("from Repo" + reg);

        return new LoggedInUser(username, "LETMEIN");
    }
}
