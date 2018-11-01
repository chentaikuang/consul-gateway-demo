package com.xiaochen.ec.router;

import com.xiaochen.ec.handler.TimeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

//由于出现次数通常比较多，这里静态引入方法。
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * 参考文章：https://blog.csdn.net/get_set/article/details/79480233
 */
@Configuration
public class RouterConfig {

    @Autowired
    private TimeHandler timeHandler;

    /**
     * http://localhost:8888/timeHandler/curTime
     * http://localhost:8888/timeHandler/date
     * http://localhost:8888/timeHandler/intervalTime
     * http://localhost:8888/timeHandler/randomStr
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> timerRouter() {
        return route(GET("/timeHandler/curTime"), req -> timeHandler.curTime(req))
                .andRoute(GET("/timeHandler/intervalTime"), timeHandler::sendTimePerSec)
                .andRoute(GET("/timeHandler/randomStr"), timeHandler::sendStrPerSec);
    }

}