package com.reviewanalyzer.service;

import com.reviewanalyzer.service.nlp.Analyzer;
import com.reviewanalyzer.service.nlp.Sentiment;

public class ReviewAnalyzer {
    public static Sentiment getSentiment(String review){
        return Analyzer.analyzeSentiment(review);
    }
}
