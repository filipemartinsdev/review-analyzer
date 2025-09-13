package com.reviewanalyzer.service;

import com.reviewanalyzer.model.ReviewResponse;
import com.reviewanalyzer.service.nlp.Sentiment;

// Classe principal do SERVICE

public class ReviewService {
    public static void analyzeReviews(String[] reviewList, ReviewResponse response){
        int n = reviewList.length; // <-- Tamanho da amostra

        String[] out;

        for (String text:reviewList){
            Sentiment sentiment = ReviewAnalyzer.getSentiment(text);

            switch (sentiment){
                case POSITIVE -> response.incrementFiPositive();
                case NEUTRAL -> response.incrementFiNeutral();
                case NEGATIVE -> response.incrementFiNegative();
            }
        }

        // fr(%) = (fi/n)*100
        response.setFrPercentPositive( (response.getFiPositive()/(float)n) *100F);
        response.setFrPercentNeutral( (response.getFiNeutral()/(float)n) *100F);
        response.setFrPercentNegative( (response.getFiNegative()/(float)n) *100F);
    }
}
