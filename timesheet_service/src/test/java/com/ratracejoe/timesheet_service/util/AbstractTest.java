package com.ratracejoe.timesheet_service.util;

import dasniko.testcontainers.keycloak.KeycloakContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AbstractTest {
    static KeycloakContainer keycloak;

    static {
        keycloak = new KeycloakContainer().withRealmImportFile("keycloak/realm-export.json");
        keycloak.start();
    }

    @Autowired
    protected TestRestTemplate restTemplate;
    @Autowired
    protected ProjectUtils utils;

    @DynamicPropertySource
    static void registerResourceServerIssuerProperty(DynamicPropertyRegistry registry) {
        registry.add(
                "spring.security.oauth2.resourceserver.jwt.issuer-uri",
                () -> keycloak.getAuthServerUrl() + "/realms/ratracejoe");
    }

    protected static HttpHeaders authorizedHeaders(TestRestTemplate restTemplate) {
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
}
