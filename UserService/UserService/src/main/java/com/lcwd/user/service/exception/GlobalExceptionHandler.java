package com.lcwd.user.service.exception;

import com.lcwd.user.service.payLoad.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse>handlerResourceException(ResourceNotFoundException ex){
      String message =  ex.getMessage();
      ApiResponse res =new ApiResponse();
      res.setMessage(message);
      res.setSuccess(true);
      res.setStatus(HttpStatus.NOT_FOUND);
      return new ResponseEntity<>(res,HttpStatus.OK);
    }
}
