package com.reviewanalyzer.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class ReviewController implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, OPTIONS");
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

        if("OPTIONS".equals(httpExchange.getRequestMethod())){
            httpExchange.sendResponseHeaders(200, 0);
            httpExchange.close();
        }

        else if("POST".equals(httpExchange.getRequestMethod())){
            processReview(httpExchange);
            httpExchange.close();
        }


//        Recusar outros métodos além de POST
        else {
            httpExchange.sendResponseHeaders(405, 0);
            httpExchange.close();
        }
    }

//    Processar REQUEST POST
    private static void processReview(HttpExchange exchange) throws IOException {
        System.out.println("[Server] Nova conexão recebida!");
        System.out.println(
                exchange.getProtocol()+" "+
                exchange.getRequestMethod()+" "+
                exchange.getRequestURI().getPath()
        );

        String response = "{\"status\":\"API OK\"}";

        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, response.length());
        exchange.getResponseBody().write(response.getBytes());


        System.out.println(response);
        System.out.println();
    }
}
