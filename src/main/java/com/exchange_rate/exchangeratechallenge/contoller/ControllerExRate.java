package com.exchange_rate.exchangeratechallenge.contoller;

import com.exchange_rate.exchangeratechallenge.service.ServiceExRate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.Map;

@RestController
@Tag(name="ExchangeRate", description = "Exchange rate API operations")
public class ControllerExRate {
    private final ServiceExRate serviceExRate;

    @Autowired
    public ControllerExRate(ServiceExRate serviceExRate){
        this.serviceExRate = serviceExRate;
    }

    @GetMapping("/")
    @Operation(summary = "Get all currency exchange rates")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "External API successful data fetch",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(ref = "#/components/schemas/Exchange Rate All")) })
    })
    public Map<String, Double> getRaw(){
        return serviceExRate.getExchangeData();
    }

    @GetMapping("/exchange-rate")
    @Operation(summary = "Get exchange rate from Currency A to Currency B")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(ref = "#/components/schemas/Exchange Rate CA-CB"))})

    })
    public Map<String, Double> getExchangeRate(
        @Parameter(description = "Exchange rate from currency A") @RequestParam String currencyA,
        @Parameter(description = "Exchange rate to currency B") @RequestParam String currencyB) {
        return Collections.singletonMap("Rate", serviceExRate.getExchangeRateCA_CB(currencyA, currencyB));
    }

    @GetMapping("/exchange-rate-all")
    @Operation(summary = "Get exchange rate from Currency A")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(ref = "#/components/schemas/Exchange Rate All"))})

    })
    public Map<String, Double> getExchangeRateAll(
            @Parameter(description = "Exchange rate from currency A") @RequestParam String currencyA){
        return serviceExRate.getExchangeRateCA_All(currencyA);
    }

    @GetMapping("/value-conversion")
    @Operation(summary = "Get value conversion from Currency A to Currency B")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(ref = "#/components/schemas/Value Conversion CA-CB"))})

    })
    public Map<String, Double> getValueConversion(
            @Parameter(description = "Value conversion from currency A") @RequestParam String currencyA,
            @Parameter(description = "Value conversion to currency B")@RequestParam String currencyB,
            @Parameter(description = "Value to convert") @RequestParam Double value){
        return Collections.singletonMap("Conversion", serviceExRate.getValueConversionCA_CB(currencyA, currencyB, value));
    }

    @GetMapping("/value-conversion-all")
    @Operation(summary = "Get value conversion from Currency A")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(ref = "#/components/schemas/Exchange Rate All"))})

    })
    public Map<String, Double> getValueConversionAll(
            @Parameter(description = "Value conversion from currency A") @RequestParam String currencyA,
            @Parameter(description = "Value to convert") @RequestParam Double value){
        return serviceExRate.getValueConversionCA_All(currencyA, value);
    }

}
