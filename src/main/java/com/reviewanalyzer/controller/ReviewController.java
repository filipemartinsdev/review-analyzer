package com.reviewanalyzer.controller;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import java.io.IOException;

import com.google.gson.JsonSyntaxException;
import com.reviewanalyzer.model.ReviewRequest;
import com.reviewanalyzer.model.ReviewResponse;
import com.reviewanalyzer.service.ReviewService;

import com.sun.jdi.event.MethodExitEvent;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ReviewController implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        System.out.println("[Server] Nova conexao recebida!");

        httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, OPTIONS");
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

        if("OPTIONS".equals(httpExchange.getRequestMethod())){
            httpExchange.sendResponseHeaders(200, 0);
            httpExchange.close();
        }

        else if("POST".equals(httpExchange.getRequestMethod())){
            if ("/process".equals(httpExchange.getRequestURI().getPath())) {
                processReview(httpExchange);

                System.out.println("Response code: "+httpExchange.getResponseCode());
                httpExchange.close();
            }
            else {
                httpExchange.sendResponseHeaders(400, 0);
                System.out.println("Response code: 400 Bad Request");
                httpExchange.close();
            }
        }

//        405 Method Not Allowed
        else {
            httpExchange.sendResponseHeaders(405, 0);
            httpExchange.close();
        }
    }

//    Processar REQUEST POST
    private static void processReview(HttpExchange exchange) throws IOException {

        String requestBody = new String(exchange.getRequestBody().readAllBytes());

        if (requestBody.isBlank()){
            exchange.sendResponseHeaders(500, 0);
            exchange.close();
            return;
        }

        Gson gson = new Gson();
        Type listType = new TypeToken<List<String>>(){}.getType();
        List<String> listFromJson;

        try {
            listFromJson = gson.fromJson(requestBody, listType);
        } catch (JsonSyntaxException ex){
            exchange.sendResponseHeaders(500, 0);
            exchange.close();
            return;
        }

        if (listFromJson.isEmpty()){
            exchange.sendResponseHeaders(403, 0);
            exchange.close();
            return;
        }

        ReviewRequest requestOBJ = new ReviewRequest(listFromJson);
        ReviewResponse responseOBJ = new ReviewResponse();

        ReviewService.analyzeReviews(requestOBJ.getStrings(), responseOBJ);

        String jsonFromOBJ = gson.toJson(responseOBJ, responseOBJ.getClass());

        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

        exchange.sendResponseHeaders(200, jsonFromOBJ.length());
        exchange.getResponseBody().write(jsonFromOBJ.getBytes());
    }
}
