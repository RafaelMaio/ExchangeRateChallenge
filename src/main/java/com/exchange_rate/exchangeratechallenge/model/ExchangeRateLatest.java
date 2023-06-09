package com.exchange_rate.exchangeratechallenge.model;


import lombok.*;

import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ExchangeRateLatest implements Serializable {
    private Motd motd;
    private Boolean success;
    private String base;
    private String date;
    Map<String, Double> rates;
}