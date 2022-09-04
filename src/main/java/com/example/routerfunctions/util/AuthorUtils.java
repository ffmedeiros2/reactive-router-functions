package com.example.routerfunctions.util;

import java.util.List;
import java.util.function.BiFunction;

import com.example.routerfunctions.model.Author;
import com.example.routerfunctions.model.Book;

public class AuthorUtils {
    private AuthorUtils() {
    }

    public static BiFunction<Author, List<Book>, Author> combineWithBooks() {
        return (author, books) -> Author.builder()
                .id(author.getId())
                .name(author.getName())
                .books(books)
                .build();
    }
}
