package com.reviewanalyzer;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {

        HttpServer server = HttpServer.create(new InetSocketAddress( 8080), 0);


        HttpContext test = server.createContext("/test", new TesteHandler());

//        HttpContext ep = server.createContext("/test/");

        server.start();
        System.out.println("Server online: http://localhost:8080/test");
    }
}
