package com.xue.study.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Author: mf015
 * Date: 2020/7/16 0016
 */
@Configuration
public class SocketConfig {

    @Value("${socket.tcp.port}")
    public int port;
    public static int SOCKET_PORT;

    @PostConstruct
    public void setSocketPort() {
        SOCKET_PORT = port;
    }
}
