package com.reviewanalyzer.service;

import com.reviewanalyzer.nlp.Analyzer;
import com.reviewanalyzer.nlp.Sentiment;

public class ReviewAnalyzer {
    public static Sentiment getSentiment(String review){
        return Analyzer.analyzeSentiment(review);
    }
}
