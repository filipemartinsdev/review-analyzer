package com.reviewanalyzer.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class ReviewController implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        if ("GET".equals(httpExchange.getRequestMethod())){
            getReview(httpExchange);
        }

        else if("POST".equals(httpExchange.getRequestMethod())){
            postReview(httpExchange);
        }
    }

    private static void getReview(HttpExchange exchange) throws IOException {
        System.out.println("[Server] Nova conexão recebida!");
        System.out.println(exchange.getProtocol()+" GET: "+" "+exchange.getRequestURI().getPath());

        String response = "{\"text\":\"Hello World\"}";

        exchange.sendResponseHeaders(200, response.length());
        exchange.close();
        System.out.println();
    }

    private static void postReview(HttpExchange exchange) throws IOException {
        System.out.println("[Server] Nova conexão recebida!");
        System.out.println(exchange.getProtocol()+" "+exchange.getRequestMethod()+" "+exchange.getRequestURI().getPath());

//        String response = "{\"error\":\"Hello World\"}";

        exchange.sendResponseHeaders(400, 0);
        exchange.close();
        System.out.println();
    }
}
