package org.cn.kaito.auth.Service.ServiceImpl;

import org.cn.kaito.auth.Exception.CustomerException;
import org.cn.kaito.auth.Service.SessionService;
import org.cn.kaito.auth.Service.UserService;
import org.cn.kaito.auth.Utils.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.util.HashMap;

@Service
public class SessionServiceImpl implements SessionService {
    private final HashMap<String, Session> workers = new HashMap<>();

    @Autowired
    UserService userService;

    public void addByID(String userID,Session session){
        workers.put(userID,session);
    }

    public void addByToken(String token,Session session) throws CustomerException {
        String userID = userService.getUserIDByToken(token);
        addByID(userID,session);
        System.out.println(token);
        System.out.println(userID);
        sendMessage(userID,"succeed");
    }

    @Override
    public void sendMessage(String userID, String message)  {
        if (workers.containsKey(userID)){
            workers.get(userID).getAsyncRemote().sendObject(message);
        }
    }

    @Override
    public void close(String token) throws CustomerException {
        String userID = userService.getUserIDByToken(token);
        workers.remove(userID);
    }


}
