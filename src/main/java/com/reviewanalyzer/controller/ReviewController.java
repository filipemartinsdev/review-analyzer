package com.reviewanalyzer.controller;

import com.reviewanalyzer.model.ReviewRequest;
import com.reviewanalyzer.model.ReviewResponse;
import com.reviewanalyzer.service.ReviewService;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;

import com.google.gson.Gson;

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

        String PROTOCOL = exchange.getProtocol();
        String METHOD = exchange.getRequestMethod();
        String PATH = exchange.getRequestURI().getPath();

        System.out.println("Protocol: "+PROTOCOL);
        System.out.println("Method: "+METHOD);
        System.out.println("Path: "+PATH);

        Gson gson = new Gson();

        String requestBody;
//        TODO: IMPLEMENTAR JSON PARSER
        String[] requestArrTest = new String[]{"review1", "review2"};

        ReviewRequest requestOBJ = new ReviewRequest(requestArrTest);
        ReviewResponse responseOBJ = new ReviewResponse();

        ReviewService.analyzeReviews(requestOBJ.getStrings(), responseOBJ);

//        TODO: IMPLEMENTAR JSON PARSER
        String responseJson = "{" +
                "\"n\":"+responseOBJ.getN()+"," +
                "\"frPercentPositive\":"+responseOBJ.getFrPercentPositive()+"," +
                "\"frPercentNeutral\":"+responseOBJ.getFrPercentNeutral()+"," +
                "\"frPercentNegative\":"+responseOBJ.getFrPercentNegative()+"," +
                "\"fiPositive\":"+responseOBJ.getFiPositive()+"," +
                "\"fiNeutral\":"+responseOBJ.getFiNeutral()+"," +
                "\"fiNegative\":"+responseOBJ.getFiNegative()+
                "}";

        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

        exchange.sendResponseHeaders(200, responseJson.length());
        exchange.getResponseBody().write(responseJson.getBytes());
    }
}
