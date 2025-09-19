package com.reviewanalyzer.service.nlp;

import com.reviewanalyzer.service.nlp.GptClient.Message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GptRequest {
    private String model;

    private List<Message> messages;

    public GptRequest(String model, Message...message){
        this.model = model;
        this.messages = new ArrayList<>();

        this.messages.addAll(Arrays.asList(message));
    }

    public String getModel(){
        return this.model;
    }

    public List<Message> getMessages(){
        return this.messages;
    }
}
