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
        //Si en dado caso da 400 en lugar de un 200
        wireMockServer.stubFor(any(anyUrl()).atPriority(10).willReturn(aResponse()
                                        .withStatus(400)
                                        .withHeader("Content-Type", "PrubaXSD")
                        )
        );

        return wireMockServer;
    }
}