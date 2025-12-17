package com.reviewanalyzer.service;

import com.reviewanalyzer.dto.*;
import com.reviewanalyzer.service.nlp.NLPClient;

import java.util.List;

// Classe principal do SERVICE
public class ReviewService {
    private final NLPClient nlpClient;

    public ReviewService(NLPClient nlpClient){
        this.nlpClient = nlpClient;
    }

    public ReviewAnalysisContent analyzeReviews(List<String> reviewList){
        NLPClientResponse nlpClientResponse = this.nlpClient.send(reviewList);
        List<String> reviewSentimentList = nlpClientResponse.getSentiments();

        int n = 0;
        int fiPositive = 0;
        int fiNeutral = 0;
        int fiNegative = 0;

        for (String reviewSentiment:reviewSentimentList){
            Sentiment sentiment = SentimentService.getSentiment(reviewSentiment);
            switch (sentiment){
                case POSITIVE:
                    fiPositive++;
                    break;
                case NEUTRAL:
                    fiNeutral++;
                    break;
                case NEGATIVE:
                    fiNeutral++;
                    break;
                default:
                    fiNeutral++;
            }
            n++;
        }
        ReviewAnalysisContent responseContent = new ReviewAnalysisContent();
        // fr(%) = (fi/n)*100
        responseContent.setN(n);
        responseContent.setFiPositive(fiPositive);
        responseContent.setFiNeutral(fiNeutral);
        responseContent.setFiNegative(fiNegative);
        responseContent.setFrPercentPositive( (fiPositive/(float)n) *100F);
        responseContent.setFrPercentNeutral( (fiNeutral/(float)n) *100F);
        responseContent.setFrPercentNegative( (fiNegative/(float)n) *100F);
        return responseContent;
    }
}
