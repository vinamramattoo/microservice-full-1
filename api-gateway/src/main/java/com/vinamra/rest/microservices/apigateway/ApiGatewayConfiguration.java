package com.vinamra.rest.microservices.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {

        return builder.routes()
//              customized routes

                //1
                .route(p -> p.path("/get")
                        .filters(f -> f.addRequestHeader("MyHeader", "MyURI")
                                        .addRequestParameter("Param","MyVal"))
                        .uri("http://httpbin.org:80"))
                //2
                .route(p -> p.path("/currency-exchange/**")
                        //here lb::// stands for load balancer followed by app name (Lower Cased)
                        .uri("lb://currency-exchange"))

                //3
                .route(p -> p.path("/currency-conversion/**")
                        .uri("lb://currency-conversion"))

                //4
                .route(p -> p.path("/currency-conversion-feign/**")
                        .uri("lb://currency-conversion"))

                //5
                //renaming a route using regex
                .route(p -> p.path("/currency-conversion-new/**")
                        .filters(f -> f.rewritePath(
                                "/currency-conversion-new/(?<segment>.*)",
                                "/currency-conversion-feign/${segment}"))
                        .uri("lb://currency-conversion"))
                .build();
    }

}
