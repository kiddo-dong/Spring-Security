package com.example.ch2_ex1;

public class ResponseDto {
    private String Message;
    private String status;

    public ResponseDto(String message, String status) {
        Message = message;
        this.status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
