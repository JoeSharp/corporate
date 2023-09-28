package com.ratracejoe.timesheet_service.util;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.springframework.http.HttpHeaders;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class MockKeycloak implements BeforeEachCallback, AfterEachCallback {
    public static final int MOCK_KEYCLOAK_PORT = 8081;

    private WireMockServer wireMockServer;

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {

        wireMockServer = new WireMockServer(WireMockConfiguration
                .wireMockConfig()
                .port(MOCK_KEYCLOAK_PORT));
        wireMockServer.start();

        String openIdConfig = IOUtils.toString(
                this.getClass().getResourceAsStream("/json/keycloak/open-id-configuration.json"),
                "UTF-8"
        );
        wireMockServer.stubFor(get(urlEqualTo("/realms/ratracejoe/.well-known/openid-configuration"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(openIdConfig)
                        .withStatus(200)));

        String certs = IOUtils.toString(
                this.getClass().getResourceAsStream("/json/keycloak/certs.json"),
                "UTF-8"
        );
        wireMockServer.stubFor(get(urlEqualTo("/realms/ratracejoe/protocol/openid-connect/certs"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(certs)
                        .withStatus(200)));
    }

    public HttpHeaders authorizedHeaders() throws IOException {
        String userToken = IOUtils.toString(
                this.getClass().getResourceAsStream("/json/keycloak/userToken.json"),
                "UTF-8"
        );

        var headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + userToken);
        return headers;
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        wireMockServer.stop();
    }
}
