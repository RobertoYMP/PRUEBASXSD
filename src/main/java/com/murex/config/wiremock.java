package com.murex.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@Configuration
public class wiremock {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public WireMockServer wireMockServer() {
        WireMockServer wireMockServer = new WireMockServer(8089);

        wireMockServer.stubFor(post(urlEqualTo("/MXeventXSD"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "PruebaXSD con Wiremock")
                )

        );

        return wireMockServer;
    }
}