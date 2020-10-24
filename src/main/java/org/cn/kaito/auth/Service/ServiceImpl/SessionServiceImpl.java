package org.cn.kaito.auth.Service.ServiceImpl;

import org.cn.kaito.auth.Service.SessionService;
import org.cn.kaito.auth.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.util.HashMap;

@Service
public class SessionServiceImpl implements SessionService {
    private HashMap<String, Session> workers = new HashMap<>();

    @Autowired
    UserService userService;

    public void addByID(String userID,Session session){
        workers.put(userID,session);
    }

    public void addByToken(String token,Session session){
        String userID = userService.getUserIDByToken(token);
        addByID(userID,session);
        sendMessage(userID,"succeed");
    }

    @Override
    public void sendMessage(String userID, String message) {
        workers.get(userID).getAsyncRemote().sendText(message);
    }


}
