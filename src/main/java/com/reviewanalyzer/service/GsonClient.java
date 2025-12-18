package com.reviewanalyzer.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class GsonClient implements JsonParser {
    private final Gson gson;

    public GsonClient(){
        this.gson = new Gson();
    }

    @Override
    public String toJson(Object obj) {
        return this.gson.toJson(obj);
    }

    @Override
    public <T> T fromJson(String json, Class<T> clazz) {
        return this.gson.fromJson(json, clazz);
    }

    @Override
    public <T> List<T> fromJsonList(String json, Class<T> elementClass){
        Type listType = TypeToken.getParameterized(List.class, elementClass).getType();
        return this.gson.fromJson(json, listType);
    }
}
