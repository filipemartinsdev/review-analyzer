package com.reviewanalyzer.service.nlp;

import opennlp.tools.cmdline.tokenizer.SimpleTokenizerTool;
import opennlp.tools.doccat.DocumentSample;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.util.ObjectStreamUtils;
import opennlp.tools.util.TrainingParameters;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.tokenize.SimpleTokenizer;

public class SentimentAnalyzer {
    public static Sentiment analyzeSentiment(String text) {
//        Checar String vazia/nula
        if (text == null || text.trim().isBlank()) {
            return Sentiment.NEUTRAL;
        }



        return Sentiment.NEUTRAL;
    }
}