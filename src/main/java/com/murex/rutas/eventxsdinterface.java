package com.murex.rutas;

import com.murex.Extraccion.Mxmlextracion;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import jakarta.inject.Inject;

@Component
public class eventxsdinterface extends RouteBuilder {

    @Inject
    private Mxmlextracion mxmlextracion;

    @Override
    public void configure() {
        from("file://{{pruebaevenXSD.input.folder}}?noop=true")
                .routeId("MainRoute")

                .process(mxmlextracion)

                .log("JSON generado:\n${body}")
                .to("file://src/main/resources/output?fileName=Prueba.json")
                .setHeader(Exchange.HTTP_METHOD,
                        constant("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json")).to("http://localhost:8089/eventos").log("Respuesta WireMock: ${body}");
    }
}