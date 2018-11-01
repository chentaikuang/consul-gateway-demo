package com.xiaochen.ec.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 服务权重测试
 * http://xujin.org/sc/gw/gw08/
 */
@RestController
@RequestMapping("/service")
public class ServiceController {

    private static final String VER_1 = "ver1";
    private static final String VER_2 = "ver2";
    static AtomicInteger v1Num = new AtomicInteger(0);
    static AtomicInteger v2Num = new AtomicInteger(0);
    private static Map vers;

    static {
        vers = new HashMap();
        vers.put(VER_1, v1Num.get());
        vers.put(VER_2, v2Num.get());
    }

    @RequestMapping(value = "/v1", produces = "text/plain;charset=UTF-8")
    public Mono<String> v1() {
        verNmInc(VER_1);
        return Mono.just(returnVers());
    }

    @RequestMapping(value = "/v2", produces = "text/plain;charset=UTF-8")
    public Mono<String> v2() {
        verNmInc(VER_2);
        return Mono.just(returnVers());
    }

    private String returnVers() {
        return vers.toString();
    }

    private void verNmInc(String  ver) {
        if (ver.equalsIgnoreCase(VER_1)) {
            vers.put(VER_1, v1Num.incrementAndGet());
        } else {
            vers.put(VER_2, v2Num.incrementAndGet());
        }
    }
}