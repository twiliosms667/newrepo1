package com.blogger4.blogger4.payload;

import java.util.Date;

public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String detail;

    public ErrorDetails(Date timestamp, String message, String detail) {
        this.timestamp = timestamp;
        this.message = message;
        this.detail = detail;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetail() {
        return detail;
    }
}
