package com.guidanz.crud;

public class JsonResponse {
    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
    public JsonResponse(String resp){
        this.response = resp;
    }
}
