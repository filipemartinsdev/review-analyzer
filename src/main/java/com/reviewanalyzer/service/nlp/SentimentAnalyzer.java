package com.reviewanalyzer.service.nlp;

public class SentimentAnalyzer {
    public static Sentiment analyzeSentiment(String text){
//        Checar String vazia/nula
        if (text == null || text.trim().isBlank()){
            return Sentiment.NEUTRAL;
        }

        String[] tokenizedText = tokenizer(text);
        String[] cleanTokenizedText = removeStopWords(tokenizedText);

        return classify(cleanTokenizedText);
    }

    private static String[] tokenizer(String text){
        // TODO: IMPLEMENTAR METODO
        return new String[]{};
    }

    private static String[] removeStopWords(String[] textTokens){
        // TODO: IMPLEMENTAR METODO
        return new String[]{};
    }

    private static Sentiment classify(String[] textTokens){
        // TODO: IMPLEMENTAR METODO
        return Sentiment.NEUTRAL;
    }
}