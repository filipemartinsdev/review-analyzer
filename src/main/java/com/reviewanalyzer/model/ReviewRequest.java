package com.reviewanalyzer.model;

public class ReviewRequest {
    public ReviewRequest(String[] reviews){
        this.reviews = reviews;
    }

    private String[] reviews; // Arrays de Strings que vem do JSON

    public String[] getStrings(){
        return this.reviews;
    } // Arrays de Strings que vem do JSON
}
