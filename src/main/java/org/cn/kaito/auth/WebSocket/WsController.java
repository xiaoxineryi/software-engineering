package org.cn.kaito.auth.WebSocket;


import lombok.extern.slf4j.Slf4j;
import org.cn.kaito.auth.Config.MyEndpointConfig;
import org.cn.kaito.auth.Security.SecurityTokenUtil;
import org.cn.kaito.auth.Service.SessionService;
import org.cn.kaito.auth.Utils.WsDecoder;
import org.cn.kaito.auth.Utils.WsEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value = "/room/{token}",configurator = MyEndpointConfig.class
        ,encoders = {WsEncoder.class}, decoders = {WsDecoder.class})
@Slf4j
@Component
public class WsController {

    @Autowired
    SessionService sessionService;

    @OnOpen
    public void OnOpen(Session session,
                       @PathParam("token") String token) throws IOException {
        sessionService.addByToken(token,session);
    }


    @OnMessage
    public String OnMessage(String  o){
        return o;
    }
    @OnClose
    public void OnClose(Session session, @PathParam("roomID") int roomID,
                        @PathParam("token") String token){

    }

}
