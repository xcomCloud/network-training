package com.xue.study;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Author: mf015
 * Date: 2020/6/30 0030
 * <p>
 * ----------------------
 * 本来是要搞serverSocket的netty实现的， 搞错了，就顺便了解下socketIO的netty实现。
 * TODO socketIO的netty实现并没有成功使用上.........有时间了再看
 */

@SpringBootApplication
public class SocketIoStarter {
    private static final Logger LOGGER = LoggerFactory.getLogger(SocketIoStarter.class);

    public static void main(String[] args) {
        SpringApplication.run(SocketIoStarter.class, args);
        LOGGER.info("### welcome to socketio-netty ###");
    }
}
