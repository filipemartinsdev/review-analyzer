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
        private ReviewAnalysisContent content;

        public Builder body(ReviewAnalysisContent content){
            this.content = content;
            return this;
        }

        public Builder responseCode(int responseCode){
            this.responseCode = responseCode;
            return this;
        }

        public Builder ok(){
            this.responseCode = 200;
            return this;
        }

        public Builder noContent(){
            this.responseCode = 204;
            this.content =null;
            return this;
        }

        public Builder badRequest(){
            this.responseCode = 400;
            this.content = null;
            return this;
        }

        public Builder notFound(){
            this.responseCode  = 404;
            this.content = null;
            return this;
        }

        public Builder methodNotAllowed(){
            this.responseCode = 405;
            this.content = null;
            return this;
        }

        public Builder internalServerError(){
            this.responseCode = 500;
            this.content = null;
            return this;
        }

        public ApiResponse build(){
            return new ApiResponse(this.responseCode, this.content);
        }
    }
}
