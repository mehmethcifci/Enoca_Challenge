package com.enoca.challenge.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ExceptionType {


    COMPANY_NOT_FOUND(1001,"Company is not found",NOT_FOUND),

    EMPLOYEE_NOT_FOUND(1002,"Employee is not found",NOT_FOUND);

    private int code;
    private String message;
    private HttpStatus httpStatus;
}
