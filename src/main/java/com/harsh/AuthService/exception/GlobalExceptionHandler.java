package com.harsh.AuthService.exception;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


//Since using for microservices and Having RESTAPI's its best(@ControllerAdvice+ @ResponseBody)
//perfect for json response 
@RestControllerAdvice
public class GlobalExceptionHandler {

    //Handle DTO validation errors(from @Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationErrors(MethodArgumentNotValidException ex)
    {
        Map<String , String> errors= new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->errors.put(error.getField() ,error.getDefaultMessage()));
        
       ApiError apiError = new ApiError(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation Error",
                "Invalid request parameters",
               errors
        );
        return ResponseEntity.badRequest().body(apiError);

    }

    //User Not found
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError>   handleUserNotFound(UserNotFoundException ex)
    {
        ApiError  apiError= new ApiError(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                "User Not Found",
                ex.getMessage(),
                null);

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }
    //Handlde Custom invalid data exception

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ApiError> handleInvalidData(InvalidDataException ex)
    {
        ApiError  apiError = new ApiError( Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                "Invalid Data",
                ex.getMessage(),
                null);

            return ResponseEntity.badRequest().body(apiError);
    }

    //Invalid Role Exception
      @ExceptionHandler(InvalidRoleException.class)
    public ResponseEntity<ApiError> handleInvalidData(InvalidRoleException ex)
    {
        ApiError  apiError = new ApiError( Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                "Invalid Role ",
                ex.getMessage(),
                null);

            return ResponseEntity.badRequest().body(apiError);
    }

    //User Already Exist  Exception
      @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ApiError> handleUserAlreadyExist(UserAlreadyExistException ex)
    {
        ApiError  apiError = new ApiError( Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                "User Already Exist ",
                ex.getMessage(),
                null);

            return ResponseEntity.badRequest().body(apiError);
    }

     // 3 Invalid JSON or request body not readable
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleUnreadableRequest(HttpMessageNotReadableException ex) {
         ApiError  apiError = new ApiError( Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                "Malformed JSON Request\", \"Request body is invalid or missing\" ",
                ex.getMessage(),
                null);

            return ResponseEntity.badRequest().body(apiError);
    }

}
