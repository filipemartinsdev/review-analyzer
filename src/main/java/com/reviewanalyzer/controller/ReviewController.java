package com.reviewanalyzer.controller;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.io.IOException;

import com.google.gson.JsonSyntaxException;
import com.reviewanalyzer.dto.*;
import com.reviewanalyzer.service.GsonClient;
import com.reviewanalyzer.service.JsonParser;
import com.reviewanalyzer.service.ReviewService;

import com.reviewanalyzer.service.nlp.gpt.GptClient;
import com.reviewanalyzer.service.nlp.NLPClient;
import com.reviewanalyzer.service.nlp.gpt.GptResponse;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ReviewController implements HttpHandler {
    public final JsonParser jsonParser;
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService, JsonParser jsonParser){
        this.jsonParser = jsonParser;
        this.reviewService = reviewService;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        final String requestMethod = this.readRequestMethod(httpExchange);
        final String requestBody = this.readRequestBody(httpExchange);
        final String requestPath = this.readRequestPath(httpExchange);

        this.configureCors(httpExchange);
        ApiResponse apiResponse;

        if("OPTIONS".equals(requestMethod)){
            apiResponse = this.handleOptions();
        }
        else if("POST".equals(requestMethod)){
            apiResponse = this.handlePost(requestPath, requestBody);

        }

        else {
            apiResponse = ApiResponse.builder().methodNotAllowed().build();
        }

        String response = this.jsonParser.toJson(apiResponse);

        httpExchange.sendResponseHeaders(apiResponse.getResponseCode(), response.length());
        httpExchange.getResponseBody().write(response.getBytes());
        httpExchange.close();
    }

    private ApiResponse handlePost(String requestPath, String requestBody){
        if ("/process".equals(requestPath)) {
            if (requestBody.isBlank()){
                return ApiResponse.builder().badRequest().build();
            }
            List<String> requestList;
            try {
                requestList = this.jsonParser.fromJsonList(requestBody, String.class);
            } catch (RuntimeException e){
                return ApiResponse.builder().badRequest().build();
            }

            ReviewAnalysisContent apiResponseContent = this.reviewService.analyzeReviews(requestList);
            return ApiResponse.builder()
                    .body(apiResponseContent)
                    .ok()
                    .build();
        }
        else {
            return ApiResponse.builder().notFound().build();
        }
    };

    private ApiResponse handleOptions(){
        return ApiResponse.builder()
                .ok()
                .build();
    };

    private ApiResponse handleDefault(){
        return ApiResponse.builder().methodNotAllowed().build();
    };

    private void configureCors(HttpExchange httpExchange){
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, OPTIONS");
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
        httpExchange.getResponseHeaders().add("Content-Type", "application/json");
    }

    private String readRequestMethod(HttpExchange httpExchange){
        return httpExchange.getRequestMethod();
    }

    private String readRequestBody(HttpExchange httpExchange) throws IOException{
        return new String(
                httpExchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8
        );
    }

    private String readRequestPath(HttpExchange httpExchange){
        return httpExchange.getRequestURI().getPath();
    }
}
