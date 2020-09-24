package com.xue.study.netty;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Author: mf015
 * Date: 2020/7/16 0016
 */
//@WebListener
public class ServerListener implements ServletContextListener {

    private NettyServer nettyServer;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        if (null == nettyServer) {
            new Thread(new NettyServer()).start();
        }
    }
}