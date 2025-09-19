package com.reviewanalyzer.service.nlp;

import java.util.ArrayList;
import java.util.List;

public class GptResponse {
    private List<GptClient.Choice> choices;

    public GptResponse() {}

    public GptResponse(List<GptClient.Choice> choices) {
        this.choices = new ArrayList<>();
        this.choices.addAll(choices);
    }


    public String getMessageContent(){
        String messageContent = this.choices.get(0).getMessage().getContent();

        if (this.choices != null && !choices.isEmpty()) {
            return messageContent;
        }
        return "";
    }

    public List<GptClient.Choice> getChoices(){
        return this.choices;
    }
}
