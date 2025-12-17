package com.reviewanalyzer.service.nlp;

import com.reviewanalyzer.dto.NLPClientResponse;
import com.reviewanalyzer.service.Sentiment;

import java.util.List;

public interface NLPClient {
    NLPClientResponse send(List<String> reviewList);
}
