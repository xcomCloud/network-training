package com.xue.study.service.impl;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.xue.study.model.Message;
import com.xue.study.service.SocketIoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Author: mf015
 * Date: 2020/6/30 0030
 */
@Service("SocketIoService")
public class SocketIoServiceImpl implements SocketIoService {

    @Autowired
    private SocketIOServer socketIOServer;

    public Map<String, SocketIOClient> clientMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void invokeStart() throws Exception{
        start();
    }

    @PreDestroy
    public void invokeStop() {
        stop();
    }

    @Override
    public void start() throws Exception{
        socketIOServer.addConnectListener(client -> {
            String clientId = getClientParams(client);
            if (StringUtils.isNotBlank(clientId)) {
                System.out.println("conn client = "+clientId);
                clientMap.put(clientId, client);
            }
        });

        socketIOServer.addDisconnectListener(client -> {
            String clientId = getClientParams(client);
            if (StringUtils.isNotBlank(clientId)) {
                System.out.println("dis client = "+clientId);
                clientMap.remove(clientId);
                client.disconnect();
            }
        });

        socketIOServer.start();
    }

    @Override
    public void stop() {
        if (null != socketIOServer) {
            socketIOServer.stop();
            socketIOServer = null;
        }
    }

    @Override
    public void pushMessage(Message message) {
        String clientId = message.getClientId();
        if (StringUtils.isNotBlank(clientId)) {
            SocketIOClient socketIOClient = clientMap.get(clientId);
            if (null != socketIOClient) {
                socketIOClient.sendEvent(EVENT_PUSH, message);
            }
        }
    }

    private String getClientParams(SocketIOClient client) {
        Map<String, List<String>> urlParams = client.getHandshakeData().getUrlParams();
        System.out.println("urlParams***" + urlParams.toString());// TODO: 2020/6/30 0030  
        List<String> list = urlParams.get("clientId");
        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }
}
