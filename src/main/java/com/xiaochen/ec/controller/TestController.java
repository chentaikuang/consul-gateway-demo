package com.xiaochen.ec.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * http:localhost:8888/test/demo
     *
     * @return
     */
    @RequestMapping("/demo")
    public Mono<Map> getInfo() {
        Map map = new HashMap();
        map.put("tips", "demo");
        map.put("curTime", new SimpleDateFormat("yyyy-MM-dd HH:MM:ss").format(new Date()));
        return Mono.just(map);
    }

    /**
     * http:localhost:8888/test/hi
     *
     * @return
     */
    @RequestMapping("/hi")
    public Mono<Map> hi(String tips) {
        Map map = new HashMap();
        if (StringUtils.isEmpty(tips)) {
            tips = "defalut val";
        }
        map.put("tips", tips);
        map.put("curTime", new SimpleDateFormat("yyyy-MM-dd HH:MM:ss").format(new Date()));
        return Mono.just(map);
    }
}
