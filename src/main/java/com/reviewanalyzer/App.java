package com.reviewanalyzer;

import com.reviewanalyzer.controller.ReviewController;

import com.reviewanalyzer.service.GsonClient;
import com.reviewanalyzer.service.JsonParser;
import com.reviewanalyzer.service.ReviewService;
import com.reviewanalyzer.service.nlp.NLPClient;
import com.reviewanalyzer.service.nlp.gpt.GptClient;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        String portEnv = System.getenv("PORT");
        int port = portEnv != null ? Integer.parseInt(portEnv) : 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress("0.0.0.0", port), 0);
        server.setExecutor(Executors.newFixedThreadPool(10));

        JsonParser jsonParser = new GsonClient();
        NLPClient nlpClient = new GptClient();
        ReviewService reviewService = new ReviewService(nlpClient);
//        Pegar PORT do RailWays
        HttpContext homeContext = server.createContext("/", new ReviewController(reviewService, jsonParser));

        server.start();
        System.out.println("Server online: PORT "+port);
    }
}
