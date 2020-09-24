package com.xue.study;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Author: mf015
 * Date: 2020/9/24 0024
 */
@SpringBootApplication
public class SocketDemoStarter {
    private static final Logger LOGGER = LoggerFactory.getLogger(SocketDemoStarter.class);

    public static void main(String[] args) {
        SpringApplication.run(SocketDemoStarter.class, args);
        LOGGER.info("#### socket-demo started ####");
    }
}
