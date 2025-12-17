package com.reviewanalyzer.service.nlp.gpt;

import com.reviewanalyzer.service.nlp.NLPClient;
import io.github.cdimascio.dotenv.Dotenv;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.reviewanalyzer.dto.NLPClientResponse;

public class GptClient implements NLPClient {
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    public NLPClientResponse send(List<String> reviewList){
        Gson gson = new Gson();
        String model = "gpt-5-nano";
        String adminPrompt = "Responda somente com um Array<String>. Para cada review, adicione um 'positive', 'negative' ou neutral ao Array, referente ao review.";

        Message message2 = new Message("system", adminPrompt);
        Message message1 = new Message("user", gson.toJson(reviewList));
        GptRequest gptRequest = new GptRequest(model, message1, message2);
        GptResponse gptResponse;

        String GPT_API_KEY = System.getenv("API_KEY");
        try {
            if (GPT_API_KEY == null) {
                GPT_API_KEY = Dotenv.load().get("API_KEY");
            }
        } catch (NullPointerException e){
            System.out.println("Null pointer");
            throw new RuntimeException("Invalid NLP API Key");
        } catch (Exception e){
            throw new RuntimeException("Erro  desconhecido");
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

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return gptResponse;
    }



}
