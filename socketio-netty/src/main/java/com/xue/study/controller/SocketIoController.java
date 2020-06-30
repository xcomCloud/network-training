package com.xue.study.controller;

import com.corundumstudio.socketio.SocketIOServer;
import com.xue.study.model.Message;
import com.xue.study.service.SocketIoService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: mf015
 * Date: 2020/6/30 0030
 */
@RestController
public class SocketIoController {

    @Autowired
    private SocketIoService socketIoService;

    @GetMapping("push-test")
    public String testPush() {
        Message message = new Message();
        message.setClientId("MF000001");
        socketIoService.pushMessage(message);
        return "ok";
    }
}
