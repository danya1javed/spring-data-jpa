package com.example.demo.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.time.LocalTime;

// Main Exception Class - Returned in response.

public class ExceptionResponse {

  private String message;
  private String statusCode;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern =  "dd-MM-yyyy hh:mm:ss")
  private LocalDateTime timeStamp;

  public ExceptionResponse(String message, String statusCode, LocalDateTime timeStamp) {
    this.message = message;
    this.statusCode = statusCode;
    this.timeStamp = timeStamp;
  }

  public String getMessage() {
    return message;
  }

  public String getStatusCode() {
    return statusCode;
  }

  public LocalDateTime getTimeStamp() {
    return timeStamp;
  }

}
