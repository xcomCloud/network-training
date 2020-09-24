package com.xue.study.service;

import com.xue.study.netty.NettyServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Author: mf015
 * Date: 2020/7/16 0016
 */
@Service
public class TestService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestService.class);

    public String test(String msg){
        LOGGER.info("msg=========="+msg);
        String resp = "23232323";
        return resp;
    }
}
