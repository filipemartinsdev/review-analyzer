package com.reviewanalyzer.controller;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.io.IOException;

import com.google.gson.JsonSyntaxException;
import com.reviewanalyzer.dto.*;
import com.reviewanalyzer.service.ReviewService;

import com.reviewanalyzer.service.nlp.gpt.GptClient;
import com.reviewanalyzer.service.nlp.NLPClient;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ReviewController implements HttpHandler {
    public static Gson gson = new Gson();
    private static final NLPClient nlpClient = new GptClient();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
//        System.out.println("[Server] Nova conexao recebida!");

        final String requestMethod = httpExchange.getRequestMethod();
        final String requestBody = new String(
             httpExchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8
        );
        final String requestPath = httpExchange.getRequestURI().getPath();

        httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, OPTIONS");
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
        httpExchange.getResponseHeaders().add("Content-Type", "application/json");

        ApiResponse apiResponse;

        if("OPTIONS".equals(requestMethod)){
            apiResponse = ApiResponse.builder()
                    .noContent()
                    .responseCode(200)
                    .build();
        }

        else if("POST".equals(requestMethod)){
            if ("/process".equals(requestPath)) {
                apiResponse = processRequest(requestBody);
            }
            else {
                apiResponse = ApiResponse.builder()
                            .responseCode(400)
                            .noContent()
                            .build();
            }
        }

        else {
            apiResponse = ApiResponse.builder()
                    .responseCode(405)
                    .noContent()
                    .build();
        }

        String response = gson.toJson(apiResponse);

        httpExchange.sendResponseHeaders(apiResponse.getResponseCode(), response.length());
        httpExchange.getResponseBody().write(response.getBytes());
        httpExchange.close();
    }

//    Processar REQUEST POST
    private static ApiResponse processRequest(String requestBody) throws IOException {
        if (requestBody.isBlank()){
            return ApiResponse.builder()
                    .noContent()
                    .responseCode(400)
                    .build();
        }

        Type listType = new TypeToken<List<String>>(){}.getType();
        List<String> requestList;

        try {
            requestList = gson.fromJson(requestBody, listType);
        } catch (JsonSyntaxException ee){
            return ApiResponse.builder()
                            .noContent()
                            .responseCode(500)
                            .build();
        }
        if (requestList.isEmpty()){
            return ApiResponse.builder()
                    .noContent()
                    .responseCode(400)
                    .build();
        }


        ReviewService reviewService = new ReviewService(nlpClient);

        ReviewAnalysisContent apiResponseContent = reviewService.analyzeReviews(requestList);
        ApiResponse apiResponse = ApiResponse.builder()
                .body(apiResponseContent)
                .responseCode(200)
                .build();
        return apiResponse;
    }
}
