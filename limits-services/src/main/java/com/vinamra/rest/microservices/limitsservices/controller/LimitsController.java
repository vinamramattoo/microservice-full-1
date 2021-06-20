package com.vinamra.rest.microservices.limitsservices.controller;

import com.vinamra.rest.microservices.limitsservices.bean.Limits;
import com.vinamra.rest.microservices.limitsservices.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    @Autowired
    private Configuration configuration;


    @GetMapping(path = "/limits")
    public Limits retrieveLimits() {
        return new Limits(configuration.getMinimum(), configuration.getMaximum());
    }
}
