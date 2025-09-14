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

    private static String[] testDataTraine = {
            // Positivos
            "positive\tÓtimo produto!",
            "positive\tGostei demais!",
            "positive\tUma belezinha",
            "positive\tChegou bem antes do prazo!",
            "positive\tExcelente qualidade",
            "positive\tRecomendo muito",
            "positive\tSuperou minhas expectativas",
            "positive\tAmei o produto",
            "positive\tMuito bom mesmo",
            "positive\tPerfeito para o que eu precisava",

            // Neutros
            "neutral\tQualidade mediana",
            "neutral\tEntrega na data prometida",
            "neutral\tNada demais",
            "neutral\tNada mal",
            "neutral\tProduto comum",
            "neutral\tAtendeu as expectativas",
            "neutral\tNormal",
            "neutral\tOk, sem mais",

            // Negativos
            "negative\tPéssima qualidade!",
            "negative\tChegou muito depois do prazo",
            "negative\tEmbalagem veio com lacre violado!",
            "negative\tProduto frágil",
            "negative\tNão recomendo",
            "negative\tQualidade horrível",
            "negative\tDinheiro jogado fora",
            "negative\tMuito ruim",
            "negative\tDecepcionante",
            "negative\tPior compra que já fiz"
    };

    private static String[] STOP_WORDS = {
            "o", "a", "um", "e", "ou", "mas", "é", "no", "na", "para", "de",
            "do", "com", "por", "e", "são", "era", "foi", "ser", "sendo", "tem",
            "possui", "contém", "do", "faz", "fez", "fará", "faria"
    };
}