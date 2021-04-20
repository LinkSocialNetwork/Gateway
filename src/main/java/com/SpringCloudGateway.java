package com;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

public class SpringCloudGateway {

    //TODO create service routes
//    @Bean
//    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
//        return builder.routes()
//                .route(myRoute -> myRoute.path("/api1/**").uri("lb://bookservice"))
//                .route(myRoute -> myRoute.path("/api2/**").uri("lb://userservice"))
//                .build();
//    }

}
