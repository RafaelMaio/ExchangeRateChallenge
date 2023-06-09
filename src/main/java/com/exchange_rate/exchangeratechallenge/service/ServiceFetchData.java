package com.exchange_rate.exchangeratechallenge.service;

import com.exchange_rate.exchangeratechallenge.exceptions.ApiRequestException;
import com.exchange_rate.exchangeratechallenge.model.ExchangeRateLatest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ServiceFetchData {
    private final RestTemplate restTemplate;

    @Autowired
    public ServiceFetchData(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable("exchange_rate")
    public ExchangeRateLatest getExchangeData() {
        try{
            System.out.println("Caching data...");
            return restTemplate.getForObject("https://api.exchangerate.host/latest",
                    ExchangeRateLatest.class);}
        catch(RuntimeException e){
            throw new ApiRequestException("External API down");
        }
    }

    //TODO: Add other data sources
}
