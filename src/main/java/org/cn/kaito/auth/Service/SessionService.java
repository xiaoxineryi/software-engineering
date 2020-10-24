package org.cn.kaito.auth.Service;

import javax.websocket.Session;

public interface SessionService {
    public void addByID(String userID, Session session);
    void addByToken(String token,Session session);
    void sendMessage(String userID,String message);
}
