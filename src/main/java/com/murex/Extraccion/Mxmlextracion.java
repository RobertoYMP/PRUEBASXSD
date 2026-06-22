package com.murex.Extraccion;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Component
public class Mxmlextracion implements Processor {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void process(Exchange exchange) throws Exception {

        File file = exchange.getIn().getBody(File.class); //Se toma el archivo

        System.out.println("Procesando archivo: " + file.getAbsolutePath());
        //Se lee el arhcivo
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(false);

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(file);

        XPath xpath = XPathFactory.newInstance().newXPath();//navegamos en el archivo

        Map<String, String> campos = new HashMap<>();//guardamos los siguientes campos

        campos.put("partyName", xpath.evaluate("//partyName", doc));
        campos.put("systemDate", xpath.evaluate("//systemDate", doc));
        campos.put("producerId", xpath.evaluate("//producerId/internalId", doc));
        campos.put("internalId", xpath.evaluate("//tradeInternalId", doc));
        campos.put("creatorId", xpath.evaluate("//producerId/creatorId", doc));
        //convertimos a JSOn
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(campos);
        exchange.getIn().setBody(json);
    }
}