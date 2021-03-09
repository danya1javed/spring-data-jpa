package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
  /**
   * ControllerAdvice - works as a interceptor for all exception thrown in RequestMapping Annotation.
   * ExceptionHandler - Specifies the method as exception handler - important else it wont be registered
   * in as a handler....
   * We create a ResponseEntity with specific exception type -> map it to our ExceptionResponse
   * -> return ResponseEntity.
   */

//  Already Exists Exception
  @ExceptionHandler
  public ResponseEntity<ExceptionResponse> alreadyExistsResponseEntity(AlreadyExistsException ex) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(
            ex.getMessage(),
            "CONFLICT",
            LocalDateTime.now()
    );

    return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.CONFLICT);
  }

//  Not Found Exception
  @ExceptionHandler
  public ResponseEntity<ExceptionResponse> notFoundResponseEntity(NotFoundException ex) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(
            ex.getMessage(),
            "NOT_FOUND",
            LocalDateTime.now()
    );

    return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
  }

//  Bad Request Exception
  @ExceptionHandler
  public ResponseEntity<ExceptionResponse> badRequestResponseEntity(BadRequestException ex) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(
            ex.getMessage(),
            "BAD_REQUEST",
            LocalDateTime.now()
    );

    return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }

}
