package com.example.routerfunctions.util;

import com.example.routerfunctions.enums.Currency;
import com.example.routerfunctions.model.Book;
import com.example.routerfunctions.model.Money;

import reactor.core.publisher.Mono;

public class BookUtils {
    private BookUtils() {
    }

    public static Book convertPrice(final Book book, final Money baseRate) {
        return Book.builder()
                .id(book.getId())
                .name(book.getName())
                .summary(book.getSummary())
                .published(book.getPublished())
                .price(MoneyUtils.convert(book.getPrice(), baseRate))
                .pages(book.getPages())
                .build();
    }

    public static Mono<Currency> getCurrency(Book book) {
        final Money price = book.getPrice();

        return price == null
                ? Mono.empty()
                : Mono.just(price.getCurrency());
    }
}
