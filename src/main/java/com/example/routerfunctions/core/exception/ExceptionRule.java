package com.example.routerfunctions.core.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionRule {
    private Class<?> clazz;
    private HttpStatus httpStatus;
    private String error;
}
