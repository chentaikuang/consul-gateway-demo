package com.xiaochen.ec.router;

import com.xiaochen.ec.handler.RateLimiterHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RateLimiterRouterConfig {

    @Autowired
    private RateLimiterHandler rateLimiterHandler;

    /**
     * http://localhost:8888/rateLimiterHandler/bool
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> rateHandlerRouter() {
        return route(GET("/rateLimiterHandler/bool"), req -> rateLimiterHandler.bool(req));
    }
}
