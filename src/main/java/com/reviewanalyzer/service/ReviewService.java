package com.reviewanalyzer.service;

import com.reviewanalyzer.model.ReviewResponse;
import com.reviewanalyzer.service.nlp.Sentiment;
import com.reviewanalyzer.service.nlp.SentimentAnalyzer;

import java.util.List;

// Classe principal do SERVICE
// Camada de negócio

public class ReviewService {
    public static void analyzeReviews(List<String> reviewList, ReviewResponse response){
        int n = reviewList.size(); // <-- Tamanho da amostra

        for (String text:reviewList){
            Sentiment sentiment = SentimentAnalyzer.analyzeSentiment(text);

            switch (sentiment){
                case POSITIVE:
                    response.incrementFiPositive();
                    break;
                case NEUTRAL:
                    response.incrementFiNeutral();
                    break;
                case NEGATIVE:
                    response.incrementFiNegative();
                    break;
            }
            response.incrementN();
        }

        // fr(%) = (fi/n)*100
        response.setFrPercentPositive( (response.getFiPositive()/(float)n) *100F);
        response.setFrPercentNeutral( (response.getFiNeutral()/(float)n) *100F);
        response.setFrPercentNegative( (response.getFiNegative()/(float)n) *100F);
    }
}
