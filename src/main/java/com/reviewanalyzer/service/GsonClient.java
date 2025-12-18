package com.reviewanalyzer.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.reviewanalyzer.exception.JsonParserException;

import java.lang.reflect.Type;
import java.util.List;

public class GsonClient implements JsonParser {
    private final Gson gson;

    public GsonClient(){
        this.gson = new Gson();
    }

    @Override
    public String toJson(Object obj) throws JsonParserException {
        try{
            return this.gson.toJson(obj);
        } catch (RuntimeException e){
            throw new JsonParserException("Error at parse Object to Json", e);
        }
    }

    @Override
    public <T> T fromJson(String json, Class<T> clazz) throws JsonParserException{
        try {
            return this.gson.fromJson(json, clazz);
        } catch (RuntimeException e){
            throw new JsonParserException("Error at parse Json to "+clazz, e);
        }
    }

    @Override
    public <T> List<T> fromJsonList(String json, Class<T> elementClass) throws JsonParserException{
        try {
            Type listType = TypeToken.getParameterized(List.class, elementClass).getType();
            return this.gson.fromJson(json, listType);
        } catch (RuntimeException e){
            throw new JsonParserException("Error at parse Json to List<"+elementClass+">", e);
        }
    }
}
