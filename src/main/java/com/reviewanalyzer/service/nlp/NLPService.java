package com.reviewanalyzer.service.nlp;

public interface NLPService {
    public abstract Sentiment getSentiment(String text);
}
