package com.xue.study.config;

import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author: mf015
 * Date: 2020/6/30 0030
 */
@Configuration
public class SocketIoConfig {
    @Value("${io.netty.host}")
    private String host;
    @Value("${io.netty.port}")
    private Integer port;
    @Value("${io.netty.bossThreads}")
    private int bossThreads;
    @Value("${io.netty.workerThreads}")
    private int workerThreads;
    @Value("${io.netty.allowCustomRequests}")
    private boolean allowCustomRequests;
    @Value("${io.netty.upgradeTimeout}")
    private int upgradeTimeout;
    @Value("${io.netty.pingTimeout}")
    private int pingTimeout;
    @Value("${io.netty.pingInterval}")
    private int pingInterval;


    @Bean
    public SocketIOServer socketIOServer(){
        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setTcpNoDelay(true);
        socketConfig.setSoLinger(0);

        com.corundumstudio.socketio.Configuration configuration = new com.corundumstudio.socketio.Configuration();
        configuration.setSocketConfig(socketConfig);
        configuration.setHostname(host);
        configuration.setPort(port);
        configuration.setBossThreads(bossThreads);
        configuration.setWorkerThreads(workerThreads);
        configuration.setAllowCustomRequests(allowCustomRequests);
        configuration.setUpgradeTimeout(upgradeTimeout);
        configuration.setPingTimeout(pingTimeout);
        configuration.setPingInterval(pingInterval);
        return new SocketIOServer(configuration);
    }
}
