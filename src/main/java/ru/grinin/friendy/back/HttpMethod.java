package ru.grinin.friendy.back;

public enum HttpMethod {
    GET("get"),
    POST("post"),
    PUT("head"),
    PATCH("head"),
    DELETE("head"),
    HEAD("head"),
    OPTIONS("options");


    private String method;

    HttpMethod(String method) {
        this.method = method;
    }

    public String getMethod(){
        return method;
    }
}
