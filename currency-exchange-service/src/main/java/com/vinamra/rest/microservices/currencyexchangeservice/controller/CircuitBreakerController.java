package com.vinamra.rest.microservices.currencyexchangeservice.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class CircuitBreakerController {
    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    //sample retry
    //    @Retry(name = "sample-api",fallbackMethod = "hardcodedResponse")
    //sample circuit breaker  if failing return default without actually sending req to server
    //    @CircuitBreaker(name = "default",fallbackMethod = "hardcodedResponse")
    //RateLimiting   10s => 10000 calls to sample api
    @RateLimiter(name = "default")
    @Bulkhead(name = "default")
    @GetMapping(path = "/sample-api")
    public String sampleApi(){
        logger.info("Sample Api call received -------------------------------------------==================");
//        new RestTemplate().getForEntity("http://localhost:8000/failure",String.class);

        return "sample api";
    }

    //fallback method
    public String hardcodedResponse(Exception ex){
        return "fallback-response";
    }
}
