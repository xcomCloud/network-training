package com.xue.study;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Author: mf015
 * Date: 2020/6/24 0024
 */
@SpringBootApplication
public class MqttNettyStarter {
    private static final Logger LOGGER = LoggerFactory.getLogger(MqttNettyStarter.class);

    public static void main(String[] args) {
        SpringApplication.run(MqttNettyStarter.class, args);
        LOGGER.info("#### welcome to mqtt-netty-using ####");
    }
}
