package com.infy.CoolPick.Exceptions;

import com.infy.CoolPick.DTO.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> exceptionHandler(Exception ex){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorMessage.setErrorMessage(ex.getMessage());
        return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorMessage> productNotFoundException(ProductNotFoundException ex){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorMessage.setErrorMessage(ex.getMessage());
        return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        ErrorMessage error = new ErrorMessage();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setErrorMessage(ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(", ")));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorMessage> handleConstraintValidationExceptions(
            ConstraintViolationException ex) {



        ErrorMessage error = new ErrorMessage();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setErrorMessage(  ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", ")));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }




}
