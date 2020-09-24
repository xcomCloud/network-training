package com.xue.study;

import com.xue.study.netty.ServerListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * Author: mf015
 * Date: 2020/6/30 0030
 */
//@ServletComponentScan("com.xue.study.netty") //webListener 注册方式二
@SpringBootApplication
public class NServerSocketStarter implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(NServerSocketStarter.class);

    //注册方式一
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean() {
        ServletListenerRegistrationBean bean = new ServletListenerRegistrationBean();
        bean.setListener(new ServerListener());
        return bean;
    }

    public static void main(String[] args) {
        SpringApplication.run(NServerSocketStarter.class, args);
        LOGGER.info("### netty-socket-server ###");
    }

    @Override
    public void run(String... args) throws Exception {
//        servletListenerRegistrationBean();
    }
}
