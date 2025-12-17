package com.reviewanalyzer.service.nlp.gpt;

import com.google.gson.Gson;
import com.reviewanalyzer.dto.NLPClientResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GptResponse implements NLPClientResponse {
    public GptResponse(){}

    List<Choice> choices;

    @Override
    public List<String> getSentiments() {
        Gson gson = new Gson();
        String content = this.choices.get(0).getMessage().getContent();

        List<String> sentiments = gson.fromJson(content, List.class);
        return sentiments;
    }
}

