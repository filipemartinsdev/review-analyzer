package com.reviewanalyzer.dto;

import java.util.List;

public class ReviewRequest {
    public ReviewRequest(List<String> reviews){
        this.reviews = reviews;
    }

    private List<String> reviews; // Arrays de Strings que vem do JSON

    public List<String> getStrings(){
        return this.reviews;
    } // Arrays de Strings que vem do JSON
}
