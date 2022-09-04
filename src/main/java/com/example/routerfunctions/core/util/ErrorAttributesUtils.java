package com.example.routerfunctions.core.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClientResponseException.NotFound;

import com.example.routerfunctions.core.exception.ExceptionRule;

public class ErrorAttributesUtils {
    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS");

    private ErrorAttributesUtils() {
    }

    public static void populateErrorAttributes(final Map<String, Object> map,
            final Throwable exception) {
        putDefaultErrorAttributes(map);
        putErrorAttributes(map, exception);
    }

    private static void putDefaultErrorAttributes(final Map<String, Object> map) {
        map.put("timestamp", LocalDateTime.now().format(DEFAULT_FORMATTER));
    }

    private static void putErrorAttributes(final Map<String, Object> map,
            final Throwable exception) {
        getExceptionRules().stream()
                .filter(rule -> rule.getClazz().equals(exception.getClass()))
                .findFirst()
                .ifPresent(rule -> {
                    map.put("status", rule.getHttpStatus().value());
                    map.put("error", rule.getError());
                });
    }

    private static List<ExceptionRule> getExceptionRules() {
        return Collections.singletonList(
                new ExceptionRule(NotFound.class, HttpStatus.NOT_FOUND, "Not Found"));
    }
}
