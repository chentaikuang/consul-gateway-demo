package com.xiaochen.ec.starter;

import org.springframework.context.annotation.Configuration;

@Configuration
public class TestAutoConfigurationFactory {

    public void main(String[] args) {
        test();
    }

    public void test() {
        System.out.println("=======>> TestAutoConfigurationFactory!");
    }


}
