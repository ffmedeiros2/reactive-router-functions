package com.example.routerfunctions.handler;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.routerfunctions.core.util.ServerResponseUtils;
import com.example.routerfunctions.enums.Currency;
import com.example.routerfunctions.model.Book;
import com.example.routerfunctions.model.Money;
import com.example.routerfunctions.repository.AuthorRepository;
import com.example.routerfunctions.repository.CurrencyRepository;
import com.example.routerfunctions.util.AuthorUtils;
import com.example.routerfunctions.util.BookUtils;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuthorHandler {
    private final AuthorRepository authorRepository;
    private final CurrencyRepository currencyRepository;

    public Mono<ServerResponse> fetchAuthor(final ServerRequest request) {

        return authorRepository.fetch(request.pathVariable("id"))
                .flatMap(ServerResponseUtils::ok);
    }

    public Mono<ServerResponse> fetchAuthorBooks(final ServerRequest request) {
        return Mono
                .zip(
                        authorRepository.fetch(request.pathVariable("id")),
                        fetchBooks(request),
                        AuthorUtils.combineWithBooks())
                .flatMap(ServerResponseUtils::ok);
    }

    private Mono<List<Book>> fetchBooks(final ServerRequest request) {
        final Currency to = extractCurrency(request);

        return authorRepository
                .fetchAllBooks(request.pathVariable("id"))
                .flatMap(book -> convertBookPrice(book, to))
                .collectList();
    }

    private Currency extractCurrency(final ServerRequest request) {
        return Currency
                .valueOf(request.queryParam("currency"));
    }

    private Mono<Book> convertBookPrice(final Book book, final Currency to) {
        return BookUtils
                .getCurrency(book)
                .filter(from -> from != to)
                .flatMap(from -> currencyRepository.getQuote(from, to))
                .map(quote -> BookUtils
                        .convertPrice(book,
                                Money.builder()
                                        .value(quote)
                                        .currency(to)
                                        .build()))
                .switchIfEmpty(Mono.just(book));
    }
}
