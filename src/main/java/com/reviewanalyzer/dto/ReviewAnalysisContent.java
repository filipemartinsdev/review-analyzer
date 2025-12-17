package com.reviewanalyzer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewAnalysisContent {
    private int n = 0;
    private int fiPositive;
    private int fiNeutral;
    private int fiNegative;
    private float frPercentPositive;
    private float frPercentNeutral;
    private float frPercentNegative;

    @Override
    public String toString() {
        return "ReviewAnalysisContent{" +
                "n=" + n +
                ", fiPositive=" + fiPositive +
                ", fiNeutral=" + fiNeutral +
                ", fiNegative=" + fiNegative +
                ", frPercentPositive=" + frPercentPositive +
                ", frPercentNeutral=" + frPercentNeutral +
                ", frPercentNegative=" + frPercentNegative +
                '}';
    }
}
