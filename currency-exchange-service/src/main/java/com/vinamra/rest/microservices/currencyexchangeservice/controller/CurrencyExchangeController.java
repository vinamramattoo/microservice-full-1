package com.vinamra.rest.microservices.currencyexchangeservice.controller;

import com.vinamra.rest.microservices.currencyexchangeservice.bean.CurrencyExchange;
import com.vinamra.rest.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {


    private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
    @Autowired
    CurrencyExchangeRepository currencyExchangeRepository;
    @Autowired
    private Environment environment;

    @GetMapping(path = "/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to){

        logger.info("retrieveExchangeValue called with {} to {}",from,to);
        CurrencyExchange currencyExchange
                = currencyExchangeRepository.findByFromAndTo(from, to);

        if(currencyExchange == null) {
            throw new RuntimeException
                    ("Unable to Find data for " + from + " to " + to);
        }

        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);

        return currencyExchange;

    }
}
