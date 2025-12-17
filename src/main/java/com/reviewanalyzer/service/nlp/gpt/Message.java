package com.reviewanalyzer.service.nlp.gpt;

import lombok.Getter;

@Getter
public class Message {
    private String role;
    private String content;

    public Message(String role, String content){
        this.role = role;
        this.content = content;
    }

    public Message(){}
}