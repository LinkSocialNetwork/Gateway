package com;

import com.filters.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SpringCloudGateway {

    @Autowired
    AuthenticationFilter filter;

    //TODO create service routes
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(myRoute -> myRoute
                        .path("/api/postservice/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://postService"))
                .route(myRoute -> myRoute
                        .path("/api/userservice/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://userService"))
                .route(myRoute -> myRoute
                        .path("/api/notificationservice/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://notificationservice"))
                .route(myRoute-> myRoute
                        .path("/api/chatservice/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://chatService")
                )
                .build();
    }

}
