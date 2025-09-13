package com.reviewanalyzer.model;

public class ReviewResponse {
    private float frPercentPositive;
    private float frPercentNeutral;
    private float frPercentNegative;

    private int fiPositive;
    private int fiNeutral;
    private int fiNegative;

    public ReviewResponse(){}

    public void incrementFiPositive(){
        this.fiPositive++;
    }
    public void incrementFiNeutral(){
        this.fiPositive++;
    }
    public void incrementFiNegative(){
        this.fiPositive++;
    }

//    >>>>>>>>>> GETTERS AND SETTERS <<<<<<<<<<

    public float getFrPercentPositive() {
        return frPercentPositive;
    }

    public void setFrPercentPositive(float frPercentPositive) {
        this.frPercentPositive = frPercentPositive;
    }

    public float getFrPercentNeutral() {
        return frPercentNeutral;
    }

    public void setFrPercentNeutral(float frPercentNeutral) {
        this.frPercentNeutral = frPercentNeutral;
    }

    public float getFrPercentNegative() {
        return frPercentNegative;
    }

    public void setFrPercentNegative(float frPercentNegative) {
        this.frPercentNegative = frPercentNegative;
    }

    public int getFiPositive() {
        return fiPositive;
    }

    public void setFiPositive(int fiPositive) {
        this.fiPositive = fiPositive;
    }

    public int getFiNeutral() {
        return fiNeutral;
    }

    public void setFiNeutral(int fiNeutral) {
        this.fiNeutral = fiNeutral;
    }

    public int getFiNegative() {
        return fiNegative;
    }

    public void setFiNegative(int fiNegative) {
        this.fiNegative = fiNegative;
    }
}

