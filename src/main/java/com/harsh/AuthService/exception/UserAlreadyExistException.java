package com.harsh.AuthService.exception;

public class UserAlreadyExistException  extends RuntimeException{

public UserAlreadyExistException(String message)
{
    super(message);
}
}
