package com.ratracejoe.timesheet_service.util;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

public class MockKeycloak implements BeforeEachCallback, AfterEachCallback {
    private WireMockServer wireMockServer;

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        wireMockServer = new WireMockServer(WireMockConfiguration
                .wireMockConfig()
                .port(8081));
        wireMockServer.start();
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        wireMockServer.stop();
    }
}
