package com.vinamra.rest.microservices.currencyconversionservice.proxy;

import com.vinamra.rest.microservices.currencyconversionservice.bean.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


//put app name url taken from eureka
//@FeignClient(name = "currency-exchange",url = "localhost:8001")
@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {

    @GetMapping(path = "/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
