package com.exchange_rate.exchangeratechallenge.service;

import com.exchange_rate.exchangeratechallenge.exceptions.ApiExceptionHandler;
import com.exchange_rate.exchangeratechallenge.exceptions.ApiRequestException;
import com.exchange_rate.exchangeratechallenge.model.ExchangeRateLatest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
//@AllArgsConstructor
public class ServiceExRate {
    private final RestTemplate restTemplate;

    @Autowired
    public ServiceExRate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ExchangeRateLatest getExchangeData() {
        try{
            return restTemplate.getForObject("https://api.exchangerate.host/latest",
                ExchangeRateLatest.class);}
        catch(RuntimeException e){
            throw new ApiRequestException("Server down");
        }
    }

    public Double getExchangeRateCA_CB(String currencyA, String currencyB){
        ExchangeRateLatest exchangeRateLatest = getExchangeData();
        Double currencyA_Value = exchangeRateLatest.getRates().get(currencyA);
        Double currencyB_Value = exchangeRateLatest.getRates().get(currencyB);
        if(currencyA_Value == null){
            throw new ApiRequestException("Cryptocurrency A does not exist");
        }
        else if(currencyB_Value == null){
            throw new ApiRequestException("Cryptocurrency B does not exist");
        }
        else{
            return currencyB_Value/currencyA_Value;
        }
    }
}
