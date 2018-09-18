package com.lab;

import java.net.HttpURLConnection;
import java.util.List;

import static java.util.Arrays.asList;

public class Response {
    public static final List<Integer> validStatus = asList(
            HttpURLConnection.HTTP_OK,
            HttpURLConnection.HTTP_CREATED,
            HttpURLConnection.HTTP_ACCEPTED,
            HttpURLConnection.HTTP_NO_CONTENT,
            HttpURLConnection.HTTP_RESET,
            HttpURLConnection.HTTP_PARTIAL,
            HttpURLConnection.HTTP_NOT_MODIFIED
    );

    private Integer codeStatus;
    private String message;

    public Response(Integer codeStatus) {
        this(codeStatus, "");
    }

    public Response(Integer codeStatus, String message) {
        this.codeStatus = codeStatus;
        this.message = message;
    }

    public Integer getCodeStatus() {
        return codeStatus;
    }

    public void setCodeStatus(Integer codeStatus) {
        this.codeStatus = codeStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Response{" +
                "codeStatus=" + codeStatus +
                ", message='" + message + '\'' +
                '}';
    }
}
