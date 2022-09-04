package com.example.routerfunctions.router;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.routerfunctions.handler.AuthorHandler;

@Configuration(proxyBeanMethods = false)
public class AuthorRouter {

    @Bean
    public RouterFunction<ServerResponse> route(final AuthorHandler authorHandler) {

        return RouterFunctions.route()
                .path("/author", b1 -> b1
                        .nest(accept(APPLICATION_JSON), b2 -> b2
                                .GET("/{id}", authorHandler::fetchAuthor)
                                .GET("/{id}/book", authorHandler::fetchAuthorBooks)))
                .build();
    }
}
