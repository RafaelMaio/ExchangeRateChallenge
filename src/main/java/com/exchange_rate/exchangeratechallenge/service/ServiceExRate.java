package com.exchange_rate.exchangeratechallenge.service;

import com.exchange_rate.exchangeratechallenge.model.ExchangeRateLatest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
//@AllArgsConstructor
public class ServiceExRate {
    private final RestTemplate restTemplate;

    @Autowired
    public ServiceExRate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ExchangeRateLatest getExchangeData(){
        return restTemplate.getForObject("https://api.exchangerate.host/latest",
                ExchangeRateLatest.class);
    }
}
