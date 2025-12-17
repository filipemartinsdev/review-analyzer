package com.reviewanalyzer.service.nlp;

public class SentimentAnalyzer {
    public static Sentiment analyzeSentiment(String text, NLPService nlpService) {
//        Checar String vazia/nula
        if (text == null || text.trim().isBlank()) {
            return Sentiment.NEUTRAL;
        }
        return nlpService.getSentiment(text);
    }
}