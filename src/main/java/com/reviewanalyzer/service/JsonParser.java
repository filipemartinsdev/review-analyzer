package com.reviewanalyzer.service;

import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public interface JsonParser {
    String toJson(Object obj);

    <T> T fromJson(String json, Class<T> clazz);

    <T> List<T> fromJsonList(String json, Class<T> elementClass);
}
