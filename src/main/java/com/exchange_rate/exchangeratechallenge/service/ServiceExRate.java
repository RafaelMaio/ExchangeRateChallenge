package com.exchange_rate.exchangeratechallenge.service;

import com.exchange_rate.exchangeratechallenge.exceptions.ApiRequestException;
import com.exchange_rate.exchangeratechallenge.model.ExchangeRateLatest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class ServiceExRate {

    private final ServiceFetchData servicefetchData;

    @Autowired
    public ServiceExRate(ServiceFetchData serviceExRate_fetchData){
        this.servicefetchData = serviceExRate_fetchData;
    }

    public Map<String, Double> getExchangeData(){
        return servicefetchData.getExchangeData().getRates();
    }

    public Double getExchangeRateCA_CB(String currencyA, String currencyB){
        ExchangeRateLatest exchangeRateLatest = servicefetchData.getExchangeData();
        Double currencyA_Value = exchangeRateLatest.getRates().get(currencyA);
        Double currencyB_Value = exchangeRateLatest.getRates().get(currencyB);
        if(currencyA_Value == null){
            throw new ApiRequestException("Currency A does not exist");
        }
        else if(currencyB_Value == null){
            throw new ApiRequestException("Currency B does not exist");
        }
        else{
            return currencyB_Value/currencyA_Value;
        }
    }

    public Map<String, Double> getExchangeRateCA_All(String currencyA){
        ExchangeRateLatest exchangeRateLatest = servicefetchData.getExchangeData();
        Double currencyA_Value = exchangeRateLatest.getRates().get(currencyA);
        if(currencyA_Value == null){
            throw new ApiRequestException("Currency A does not exist");
        }
        else{
            Map<String, Double> mapRateCA_All = new HashMap<>();
            exchangeRateLatest.getRates().forEach( (currency, currencyValue) ->
                    mapRateCA_All.put(currency, currencyValue/currencyA_Value));
            return mapRateCA_All;
        }
    }

    public Double getValueConversionCA_CB(String currencyA, String currencyB, Double value) {
        return getExchangeRateCA_CB(currencyA, currencyB) * value;
    }

    public Map<String, Double> getValueConversionCA_All(String currencyA, Double value){
        if(value == null){
            throw new ApiRequestException("Provide a real value.");
        }
        Map<String, Double> mapConvCA_All = new HashMap<>();
        getExchangeRateCA_All(currencyA).forEach((currency, rate) ->
                mapConvCA_All.put(currency, rate * value));
        return mapConvCA_All;
    }
}
