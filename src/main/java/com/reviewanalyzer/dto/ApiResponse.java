package com.reviewanalyzer.dto;

import java.net.http.HttpResponse;

public class ApiResponse<T> {
    public Builder<T> build(){
        return new Builder<>();
    }

    public static class Builder<T> {
        private int responseCode;
        private String content;

        public Builder<T> noContent(){
            this.responseCode = 200;
            return this;
        }
    }
}
