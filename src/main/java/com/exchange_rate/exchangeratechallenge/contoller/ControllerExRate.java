package com.exchange_rate.exchangeratechallenge.contoller;

import com.exchange_rate.exchangeratechallenge.model.ExchangeRateLatest;
import com.exchange_rate.exchangeratechallenge.service.ServiceExRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
