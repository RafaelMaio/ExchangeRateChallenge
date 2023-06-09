package com.exchange_rate.exchangeratechallenge.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Motd implements Serializable {
    private String msg;

    private String url;
}
