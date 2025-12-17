package com.reviewanalyzer.service.nlp.gpt;

import lombok.Getter;

@Getter
public class Choice {
    private Message message;

    public Choice(){}

    public Choice(Message message){
        this.message = message;
    }
}
