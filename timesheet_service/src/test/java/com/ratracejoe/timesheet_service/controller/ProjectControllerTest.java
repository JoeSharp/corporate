package com.ratracejoe.timesheet_service.controller;

import com.ratracejoe.timesheet_service.entity.Project;
import com.ratracejoe.timesheet_service.util.ProjectUtils;
import dasniko.testcontainers.keycloak.KeycloakContainer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProjectControllerTest {

    static KeycloakContainer keycloak;
    static {
        keycloak = new KeycloakContainer().withRealmImportFile("keycloak/realm-export.json");
        keycloak.start();
    }

    @DynamicPropertySource
    static void registerResourceServerIssuerProperty(DynamicPropertyRegistry registry) {
        registry.add(
                "spring.security.oauth2.resourceserver.jwt.issuer-uri",
                () -> keycloak.getAuthServerUrl() + "/realms/ratracejoe");
    }

    @Autowired
    private TestRestTemplate restTemplate;

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

    private HttpHeaders authorizedHeaders() {
        URI authorizationURI = URI.create(keycloak.getAuthServerUrl() + "/realms/ratracejoe/protocol/openid-connect/token");
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "password");
        formData.add("client_id", "timesheets-service");
        formData.add("username", "joe.sharp@ratracejoe.co.uk");
        formData.add("password", "s3cr3t");
        formData.add("client_secret", "rX0uyWb89PxdeclkQoLMtmRtCLRxFlKy");

        HttpHeaders loginHeaders = new HttpHeaders();
        loginHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        var result = restTemplate.exchange(authorizationURI,
                HttpMethod.POST,
                new HttpEntity<>(formData, loginHeaders),
                String.class);

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        String authToken = "Bearer " + jsonParser.parseMap(result.getBody())
                .get("access_token")
                .toString();

        var headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, authToken);
        return headers;
    }

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        var response = restTemplate.exchange("/project/hello/Joe",
                HttpMethod.GET,
                new HttpEntity<>(null, authorizedHeaders()),
                String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Hello Joe", response.getBody());
    }

    @Test
    public void getProjects() throws Exception {
        var response =
                restTemplate.exchange("/project/",
                HttpMethod.GET,
                new HttpEntity<>(null, authorizedHeaders()),
                new ParameterizedTypeReference<List<Project>>() {
                });

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(5, response.getBody().size());
    }
}