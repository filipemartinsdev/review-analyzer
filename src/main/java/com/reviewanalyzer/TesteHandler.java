package com.reviewanalyzer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class TesteHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("[Server] Nova conexão recebida!");
        System.out.println("method: "+exchange.getRequestMethod());
        System.out.println("PATH: "+exchange.getRequestURI().getPath());

        String response = "Hello teste";
        exchange.sendResponseHeaders(200, response.length());
        exchange.getResponseBody().write(response.getBytes());

        System.out.println("RESPONSE_STATUS_CODE: "+exchange.getResponseCode());
        System.out.println();

        exchange.close();
    }
}
