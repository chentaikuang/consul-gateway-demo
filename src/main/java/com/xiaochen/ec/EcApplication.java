package com.xiaochen.ec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import reactor.core.publisher.Mono;

@SpringBootApplication
@ComponentScan(basePackages = {"com.xiaochen.ec.controller", "com.xiaochen.ec.handler", "com.xiaochen.ec.router"})
public class EcApplication {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        SpringApplication.run(EcApplication.class, args);
    }


    /**
     * 转发规则实现方式一
     * /hi/**  --> http://localhost:8888/test/hi?tips=requet_route
     *
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // basic proxy
                .route(
                        "test_route",
                        p -> p
                                .path("/hi/**")
                                .uri("http://localhost:8888/test/hi?tips=request_route")
                )
                .build();
    }

    /**
     * IP限流
     *
     * @return
     */
    @Bean
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostString());
    }

    /**
     * 用户限流
     *
     * @return
     */
    @Bean
    KeyResolver userKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("userId"));
    }

    /**
     * 接口限流
     *
     * @return
     */
    @Bean
    KeyResolver apiKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getPath().value());
    }

}
