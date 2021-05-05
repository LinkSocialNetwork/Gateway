package com.filters;

import com.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;

@RefreshScope
@Component
public class AuthenticationFilter implements GatewayFilter {

    @Autowired
    private RouterValidator routerValidator;
    @Autowired
    private JwtUtil jwtUtil;


    /**Filters are applied to all matching routes in the RouterValidator class. JWT Tokens are checked for integrity, and
     * access is denied to the service if there is not a valid token.
     * @param exchange - Provides access to the HTTP request and response and also exposes additional server-side processing
     *                   related properties and features such as request attributes.
     * @param chain - Contract to allow a WebFilter to delegate to the next in the chain.
     * @return - Filter to next in the chain.
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        /*
        If path matches the closedApiEndpoints field in RouterValidator then methods are invoked.
         */

        if (routerValidator.isSecured.test(request)) {
            if (this.isAuthMissing(request)){
                return this.onError(exchange, "Authorization header is missing in request", HttpStatus.UNAUTHORIZED);

            }

            final String token = this.getAuthHeader(request);
            try {
                System.out.println("Token is Valid - True or False : " + jwtUtil.decrypt(token));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            try {
                if (!jwtUtil.decrypt(token))
                    return this.onError(exchange, "Authorization header is invalid", HttpStatus.UNAUTHORIZED);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            try {
                this.populateRequestWithHeaders(exchange, token);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return chain.filter(exchange);
    }


    /*PRIVATE*/

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    /**Checks to make sure the auth header currently set to "token" is in the headers.
     *
     * @param request - Represents a reactive server-side HTTP request.
     * @return - Returns the JWT token from the auth header.
     */

    private String getAuthHeader(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty("token").get(0);
    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("token");
    }

    /**Populates the request with the necessary headers to allow access to the service. It's possible to add claims to the token here
     * in case specific information is required by the service which is not provided in the request body.
     *
     * @param exchange - Provides access to the HTTP request and response and also exposes additional server-side processing related properties and features such as request attributes.
     * @param token - JWT token
     * @throws UnsupportedEncodingException
     */
    private void populateRequestWithHeaders(ServerWebExchange exchange, String token) throws UnsupportedEncodingException {
        exchange.getRequest().mutate()
                .build();
    }
}
