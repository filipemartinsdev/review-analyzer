package com.reviewanalyzer.service.nlp;

import com.google.gson.Gson;
import io.github.cdimascio.dotenv.Dotenv;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GptClient implements NLPService{
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";


    public Sentiment getSentiment(String text){
        String model = "gpt-5-nano";

        Message message1 = new Message("user", text);
        Message message2 = new Message("system", "responda com a categoria que melhor se encaixa esse review ('positive', 'neutral', 'negative')");
        GptRequest gptRequest = new GptRequest(model, message1, message2);
        GptResponse gptResponse;

        Gson gson = new Gson();

        String GPT_API_KEY = System.getenv("GPT_API");
        try {
            if (GPT_API_KEY == null) {
                GPT_API_KEY = Dotenv.load().get("API_KEY");
            }
        } catch (NullPointerException e){
            System.out.println("Null pointer");
            e.printStackTrace();
        } catch (Exception e){
            System.out.println("Erro  desconhecido");
            e.printStackTrace();
        }

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Authorization", GPT_API_KEY)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(gptRequest)))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            gptResponse = gson.fromJson(response.body(), GptResponse.class);
            gptRequest.getMessages().forEach((m)-> System.out.println(m.content));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String messageContent = gptResponse.getMessageContent();

        return switch (messageContent) {
            case "positive" -> Sentiment.POSITIVE;
            case "negative" -> Sentiment.NEGATIVE;
            default -> Sentiment.NEUTRAL; // TODO: implement fallback
        };
    }

    private static class Choice {
        private Message message;

        public Choice(Message message){
            this.message = message;
        }

        public Choice(){}

        public Message getMessage() {
            return this.message;
        }
    }

    private static class Message {
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

    private static class GptResponse {
        private List<Choice> choices;

        public GptResponse() {}

        public GptResponse(List<GptClient.Choice> choices) {
            this.choices = new ArrayList<>();
            this.choices.addAll(choices);
        }

        public String getMessageContent(){
            String messageContent = this.choices.get(0).getMessage().getContent();

            if (this.choices != null && !choices.isEmpty()) {
                return messageContent;
            }
            return "";
        }

        public List<GptClient.Choice> getChoices(){
            return this.choices;
        }
    }

    private static class GptRequest {
        private String model;

        private List<Message> messages;

        public GptRequest(String model, Message...message){
            this.model = model;
            this.messages = new ArrayList<>();

            this.messages.addAll(Arrays.asList(message));
        }

        public String getModel(){
            return this.model;
        }

        public List<Message> getMessages(){
            return this.messages;
        }
    }

}
