package com.harsh.AuthService.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    //Handle DTO validation errors(from @Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String , String>> handleValidationErrors(MethodArgumentNotValidException ex)
    {
        Map<String , String> errors= new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->errors.put(error.getField() ,error.getDefaultMessage()));
        //return 404 with errors map
        return ResponseEntity.badRequest().body(errors);

    }

}
