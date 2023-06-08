package com.exchange_rate.exchangeratechallenge.contoller;

import com.exchange_rate.exchangeratechallenge.exceptions.ApiRequestException;
import com.exchange_rate.exchangeratechallenge.model.ExchangeRateLatest;
import com.exchange_rate.exchangeratechallenge.service.ServiceExRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
public class ControllerExRate {
    private final ServiceExRate serviceExRate;

    @Autowired
    public ControllerExRate(ServiceExRate serviceExRate){
        this.serviceExRate = serviceExRate;
    }

    @GetMapping("/")
    public ExchangeRateLatest getRaw(){
        return serviceExRate.getExchangeData();
    }

    @GetMapping("/exchange-rate")
    public Map<String, Double> getExchangeRate(
        @RequestParam String currencyA,
        @RequestParam String currencyB) {
        return Collections.singletonMap("Rate", serviceExRate.getExchangeRateCA_CB(currencyA, currencyB));
    }


}
