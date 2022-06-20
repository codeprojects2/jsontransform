package com.exercise.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private int errorCode;
    private String message;
    private String messageId;

    @JsonCreator
    public ErrorResponse(@JsonProperty("ErrorCode") int errorCode, @JsonProperty("ErrorMessage") String errorMessage) {
        this.errorCode = errorCode;
        this.message = errorMessage;
    }

    public ErrorResponse(int errorCode, String messageId, String errorMessage) {
        this.errorCode = errorCode;
        this.messageId = messageId;
        this.message = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
