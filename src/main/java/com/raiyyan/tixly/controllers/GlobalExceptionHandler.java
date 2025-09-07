package com.raiyyan.tixly.controllers;

import com.raiyyan.tixly.domain.dtos.ErrorDto;
import com.raiyyan.tixly.exceptions.UserNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ErrorDto> handleUserNotFoundException(UserNotFoundException exception) {
    log.error("Caught User not found: ", exception);
    ErrorDto errorDto = new ErrorDto();
    errorDto.setError("User not found");
    return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorDto> handleMethodArgumentNoValidException(MethodArgumentNotValidException exception) {
    log.error("Caught MethodArgumentNotValidException: ", exception);
    ErrorDto errorDto = new ErrorDto();
    BindingResult bindingResult = exception.getBindingResult();
    List<FieldError> fieldErrors = bindingResult.getFieldErrors();
    String validationErrorMessage = fieldErrors.stream().findFirst().map(DefaultMessageSourceResolvable::getDefaultMessage).orElse("validation error occurred");
    errorDto.setError(validationErrorMessage);
    return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorDto> handleConstrainViolationException(ConstraintViolationException exception) {
    log.error("Caught ConstraintViolationException: ", exception);
    ErrorDto errorDto = new ErrorDto();
    String constrainViolationErrorMessage = exception.getConstraintViolations().stream().findFirst().map(ConstraintViolation::getMessage).orElse("Constrain violation error occurred");
    errorDto.setError(constrainViolationErrorMessage);
    return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDto> handleException(Exception ex) {
    log.error("Caught error: ", ex);
    ErrorDto errorDto = new ErrorDto();
    errorDto.setError("An unknown error occurred");
    return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
