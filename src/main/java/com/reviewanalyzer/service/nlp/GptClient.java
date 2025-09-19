package com.reviewanalyzer.service.nlp;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class GptClient {
//    static String GPT_API = "";
    static String API_URL = "https://api.openai.com/v1/chat/completions";


//    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//        String str;
//        while (true){
//            System.out.print("REVIEW: ");
//            str = scan.nextLine();
//            GptResponse response = getSentiment(str);
//            System.out.println("RESPONSE: "+response.getMessageContent());
//        }

//        GptResponse response = getSentiment("");
//        System.out.println(response.getMessageContent());
//    }


    public static GptResponse getSentiment(String text){
        String model = "gpt-5-nano";

        Message message1 = new Message("user", text);
        Message message2 = new Message("system", "responda com a categoria que melhor se encaixa esse review ('positive', 'neutral', 'negative')");

        GptRequest gptRequest = new GptRequest(model, message1, message2);

        Gson gson = new Gson();

        GptResponse gptResponse;
        String GPT_API = System.getenv("GPT_API");
        if (GPT_API == null || GPT_API.isBlank() ){
            List<Choice> choices = new ArrayList<>();
            choices.add(new Choice(new Message("service", "neutral")));
            System.out.println("[Server] GPT_API env not Found");
            return new GptResponse(choices);
        }

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Authorization", GPT_API)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(gptRequest)))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());


            gptResponse = gson.fromJson(response.body(), GptResponse.class);



//               ====== LOG ======
            System.out.println("REQUEST MESSAGES: ");
            gptRequest.getMessages().forEach((m)-> System.out.println(m.content));
//
//            System.out.println();
            System.out.println("RESPONSE: ");
            System.out.println(gptResponse.getMessageContent());
//            System.out.println(response.body());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return gptResponse;
    }


    public static class Choice {
        private Message message;

        public Choice(Message message){
            this.message = message;
        }

        public Choice(){}

        public Message getMessage() {
            return this.message;
        }
    }

    public static class Message {
        private String role;
        private String content;

        public Message(String role, String content){
            this.role = role;
            this.content = content;
        }

        public Message(){}

        public String getRole() {
            return role;
        }

        public String getContent() {
            return content;
        }
    }
}
