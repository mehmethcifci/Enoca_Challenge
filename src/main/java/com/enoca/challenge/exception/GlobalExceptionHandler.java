package com.enoca.challenge.exception;

import com.enoca.challenge.exception.custom.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.enoca.challenge.exception.ExceptionType.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    @ResponseBody
    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleCompanyNotFoundException(CompanyNotFoundException exception){
        log.warn("Company is not found. {}",exception.getMessage());
        return createExceptionInfoResponse(COMPANY_NOT_FOUND);
    }
    @ResponseBody
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleEmployeeNotFoundException(EmployeeNotFoundException exception){
        log.warn("Company is not found. {}",exception.getMessage());
        return createExceptionInfoResponse(EMPLOYEE_NOT_FOUND);
    }
    private ResponseEntity<ExceptionResponse> createExceptionInfoResponse(ExceptionType exceptionType) {
        return new ResponseEntity<>(ExceptionResponse.builder()
                .exceptionCode(exceptionType.getCode())
                .customMessage(exceptionType.getMessage())
                .httpStatus(exceptionType.getHttpStatus().value())
                .build(), exceptionType.getHttpStatus());
    }
}
