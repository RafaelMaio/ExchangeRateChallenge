package com.exchange_rate.exchangeratechallenge.model;


import lombok.*;

import java.util.Dictionary;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ExchangeRateLatest {
    private Motd motd;
    private Boolean success;
    private String base;
    private String date;
    Map<String, Double> rates;


}