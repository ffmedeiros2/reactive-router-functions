package com.example.routerfunctions.repository;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.routerfunctions.enums.Currency;
import com.example.routerfunctions.model.CurrencyConverter;

import reactor.core.publisher.Mono;

@Repository
public class CurrencyRepository {
    private final WebClient client;
    private final String key;

    @Autowired
    public CurrencyRepository(
            final WebClient.Builder builder,
            @Value("${api.layer.key}") final String key,
            @Value("${api.layer.currency.url}") String url) {
        this.client = builder.baseUrl(url).build();
        this.key = key;
    }

    public Mono<BigDecimal> getQuote(final Currency from, final Currency to) {
        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/convert")
                        .queryParam("from", from)
                        .queryParam("to", to)
                        .queryParam("amount", 1)
                        .build())
                .header("apikey", key)
                .retrieve()
                .bodyToMono(CurrencyConverter.class)
                .map(convert -> convert.getInfo().getQuote())
                .map(BigDecimal::valueOf);
    }
}
