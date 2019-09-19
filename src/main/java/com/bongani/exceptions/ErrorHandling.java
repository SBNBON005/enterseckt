package com.bongani.exceptions;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;



@ControllerAdvice
public class ErrorHandling {

    private Logger logger = LoggerFactory.getLogger(ErrorHandling.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> genericExceptionHandler(Exception ex, WebRequest request) {
        logger.error("Exception : ", ex);
        ErrorDetails errorDetails = new ErrorDetails("Server Error", 500);
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> handleMissingParams(MissingServletRequestParameterException ex) {
        logger.error("Exception : ", ex);
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), 400);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
