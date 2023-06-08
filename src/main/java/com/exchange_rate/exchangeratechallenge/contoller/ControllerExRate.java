package com.exchange_rate.exchangeratechallenge.contoller;

import com.exchange_rate.exchangeratechallenge.service.ServiceExRate;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Map<String, Double> getRaw(){
        return serviceExRate.getExchangeData();
    }

    //TODO: Change API response body
    @GetMapping("/exchange-rate")
    public Map<String, Double> getExchangeRate(
        @RequestParam String currencyA,
        @RequestParam String currencyB) {
        return Collections.singletonMap("Rate", serviceExRate.getExchangeRateCA_CB(currencyA, currencyB));
    }

    //TODO: Exponential notation? Not critical
    @GetMapping("/exchange-rate-all")
    public Map<String, Double> getExchangeRateAll(
            @RequestParam String currencyA){
        return serviceExRate.getExchangeRateCA_All(currencyA);
    }

    @GetMapping("/value-conversion")
    public Map<String, Double> getValueConversion(
            @RequestParam String currencyA,
            @RequestParam String currencyB,
            @RequestParam Double value){
        return Collections.singletonMap("Conversion", serviceExRate.getValueConversionCA_CB(currencyA, currencyB, value));
    }

    @GetMapping("/value-conversion-all")
    public Map<String, Double> getValueConversionAll(
            @RequestParam String currencyA,
            @RequestParam Double value){
        return serviceExRate.getValueConversionCA_All(currencyA, value);
    }

}
