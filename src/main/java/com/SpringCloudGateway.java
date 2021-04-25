package com;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SpringCloudGateway {

    //TODO create service routes
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
        return builder.routes()
<<<<<<< HEAD
                .route(myRoute -> myRoute.path("/**").uri("lb://postservice"))
                .route(myRoute -> myRoute.path("/api2/**").uri("lb://tempservice2"))
=======
                .route(myRoute -> myRoute
                        .path("/api/postservice/**")
                        .uri("lb://postService"))
                .route(myRoute -> myRoute
                        .path("/api/userservice/**")
                        .uri("lb://userService"))
>>>>>>> 3c03bb7fb11bcd39b21526d46a0056642efc4c92
                .build();
    }

}
