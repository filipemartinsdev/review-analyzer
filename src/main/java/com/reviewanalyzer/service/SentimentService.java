package com.reviewanalyzer.service;

import com.reviewanalyzer.service.nlp.NLPClient;

public class SentimentService {
    public static Sentiment getSentiment(String sentimentString) {
        if (sentimentString == null || sentimentString.trim().isBlank()) {
            return Sentiment.NEUTRAL;
        }

        return switch (sentimentString) {
            case "positive" -> Sentiment.POSITIVE;
            case "neutral" -> Sentiment.NEUTRAL;
            case "negative" -> Sentiment.NEGATIVE;
            default -> Sentiment.NEUTRAL;
        };
    }
}