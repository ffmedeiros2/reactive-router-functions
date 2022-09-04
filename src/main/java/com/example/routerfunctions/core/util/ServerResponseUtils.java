package com.example.routerfunctions.core.util;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

public class ServerResponseUtils {

    private ServerResponseUtils() {
    }

    public static Mono<ServerResponse> ok(final Object value) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromValue(value));
    }
}
