package com.example.itau.catapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Timestamp;

public class JsonResponse {
    private static ObjectMapper jsonMapper = new ObjectMapper();
    public static String STATUS_OK = "OK";
    public static String STATUS_FAILED = "FALHA";
    public static String INTERNAL_ERROR = "Erro Interno";
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    private String status;
    private String errorMessage;

    public JsonResponse() {
        this.status = STATUS_FAILED;
        this.errorMessage = INTERNAL_ERROR;
    }

    public JsonResponse(String status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static String getFailJsonWithMapperError()
    {
        return "{status:\""+STATUS_FAILED+"\",\nerrorMessage:\""+"INTERNAL_ERROR"+"\"}";
    }

    @Override
    public String toString() {
        try {
            return jsonMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
           return getFailJsonWithMapperError();
        }
    }
}
