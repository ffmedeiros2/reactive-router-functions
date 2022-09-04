package com.example.routerfunctions.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.routerfunctions.model.Author;
import com.example.routerfunctions.model.Book;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class AuthorRepository {
    private final WebClient client;

    public AuthorRepository(
            final WebClient.Builder builder,
            @Value("${api.bookstore.url}") String url) {
        this.client = builder.baseUrl(url).build();
    }

    public Mono<Author> fetch(final String id) {
        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("author")
                        .pathSegment("{id}")
                        .build(id))
                .retrieve()
                .bodyToMono(Author.class);
    }

    public Flux<Book> fetchAllBooks(final String id) {
        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("author")
                        .pathSegment("{id}")
                        .pathSegment("book")
                        .build(id))
                .retrieve()
                .bodyToFlux(Book.class);
    }
}
