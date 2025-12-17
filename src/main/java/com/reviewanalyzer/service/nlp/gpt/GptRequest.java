package com.reviewanalyzer.service.nlp.gpt;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class GptRequest {
    private final String model;

    private final List<Message> messages;

    public GptRequest(String model, Message...message){
        this.model = model;
        this.messages = new ArrayList<>();

        this.messages.addAll(Arrays.asList(message));
    }
}