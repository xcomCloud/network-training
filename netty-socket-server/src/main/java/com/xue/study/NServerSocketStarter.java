package com.xue.study;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Author: mf015
 * Date: 2020/6/30 0030
 */
@SpringBootApplication
public class NServerSocketStarter {
    private static final Logger LOGGER = LoggerFactory.getLogger(NServerSocketStarter.class);

    public static void main(String[] args) {
        SpringApplication.run(NServerSocketStarter.class, args);
        LOGGER.info("### netty-socket-server ###");
    }
}
