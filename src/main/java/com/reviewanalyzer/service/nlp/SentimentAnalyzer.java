package com.reviewanalyzer.service.nlp;

public class SentimentAnalyzer {
    public static Sentiment analyzeSentiment(String text) {
//        Checar String vazia/nula
        if (text == null || text.trim().isBlank()) {
            return Sentiment.NEUTRAL;
        }

        GptResponse response = GptClient.getSentiment(text);
        String messageContent = response.getMessageContent();

        if ("positive".equals(messageContent)){
            return Sentiment.POSITIVE;
        }

        if ("negative".equals(messageContent)) {
            return Sentiment.NEGATIVE;
        }

        if ("neutral".equals(messageContent)){
            return Sentiment.NEUTRAL;
        }

        System.out.println("Response Error");
        return Sentiment.NEUTRAL;
    }
}