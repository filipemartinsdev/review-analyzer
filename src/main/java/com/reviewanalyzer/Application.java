package com.reviewanalyzer;

import com.reviewanalyzer.controller.ReviewController;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {
//        Pegar PORT do RailWays
        String portEnv = System.getenv("PORT");
        int port = portEnv != null ? Integer.parseInt(portEnv) : 8080;

        HttpServer server = HttpServer.create(new InetSocketAddress("0.0.0.0", port), 0);
        server.setExecutor(Executors.newFixedThreadPool(10));

        HttpContext homeContext = server.createContext("/", new ReviewController());

        server.start();
        System.out.println("Server online: PORT "+port);
    }
}
