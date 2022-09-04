package com.example.routerfunctions.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Book {
    private Long id;
    private String name;
    private String summary;
    private Integer published;
    private Money price;
    private Integer pages;
}
