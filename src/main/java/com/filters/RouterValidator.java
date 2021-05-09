package com.filters;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
public class RouterValidator {

    /*
        Any routes that have "protected" in their uri must have a JWT token or
        will be denied access to their respective business service.
     */
    public static final List<String> closedApiEndpoints= Arrays.asList(
            "/protected/"
    );

    /*
       Here we use anyMatch() to make sure that any routes that match closedApiEndpoints
       in their uri will be checked by the AuthenticationFilter.
     */

    public Predicate<ServerHttpRequest> isSecured =
            request -> closedApiEndpoints
                    .stream()
                    .anyMatch(uri -> request.getURI().getPath().contains(uri));

}
