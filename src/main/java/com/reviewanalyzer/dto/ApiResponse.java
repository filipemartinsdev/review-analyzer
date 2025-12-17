package com.reviewanalyzer.dto;

import lombok.Getter;

@Getter
public class ApiResponse {
    private final Integer responseCode;
    private final ReviewAnalysisContent content;

    private ApiResponse(Integer responseCode, ReviewAnalysisContent content){
        this.responseCode = responseCode;
        this.content = content;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private Integer responseCode;
        private ReviewAnalysisContent body;


        public Builder body(ReviewAnalysisContent content){
            this.body = content;
            return this;
        }

        public Builder responseCode(int responseCode){
            this.responseCode = responseCode;
            return this;
        }

        public Builder noContent(){
            this.responseCode = 204;
            this.body=null;
            return this;
        }

        public ApiResponse build(){
            if (this.responseCode==null){
                throw new RuntimeException("Invalid Response Code");
            }
            return new ApiResponse(this.responseCode, this.body);
        }
    }
}
