package com.reviewanalyzer.service;

import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.reviewanalyzer.exception.JsonParserException;

import java.lang.reflect.Type;
import java.util.List;

public interface JsonParser {
    String toJson(Object obj) throws JsonParserException;

    <T> T fromJson(String json, Class<T> clazz) throws JsonParserException;

    <T> List<T> fromJsonList(String json, Class<T> elementClass) throws JsonParserException;
}
