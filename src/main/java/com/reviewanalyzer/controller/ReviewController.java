package com.reviewanalyzer.controller;

import com.reviewanalyzer.model.ReviewResponse;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class ReviewController implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        System.out.println("[Server] Nova conexão recebida!");

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

//        405 Method Not Allowed
        else {
            httpExchange.sendResponseHeaders(405, 0);
            httpExchange.close();
        }
    }

//    Processar REQUEST POST
    private static void processReview(HttpExchange exchange) throws IOException {

        String PROTOCOL = exchange.getProtocol();
        String METHOD = exchange.getRequestMethod();
        String PATH = exchange.getRequestURI().getPath();

        System.out.println("Protocol: "+PROTOCOL);
        System.out.println("Method: "+METHOD);
        System.out.println("Path: "+PATH);

        String requestBody;

        ReviewResponse requestOBJ = new ReviewResponse();
        ReviewResponse responseOBJ = new ReviewResponse();


        String response = "{\"status\":\"API OK\"}";

        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

        exchange.sendResponseHeaders(200, response.length());
        exchange.getResponseBody().write(response.getBytes());


        System.out.println(response);
        System.out.println();
    }
}
