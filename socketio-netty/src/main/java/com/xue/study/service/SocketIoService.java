package com.xue.study.service;

import com.xue.study.model.Message;

/**
 * Author: mf015
 * Date: 2020/6/30 0030
 */
public interface SocketIoService {

    public static final String EVENT_PUSH = "event_push";

    //
    void start() throws Exception;

    //
    void stop();

    //推送信息
    void pushMessage(Message message);
}
