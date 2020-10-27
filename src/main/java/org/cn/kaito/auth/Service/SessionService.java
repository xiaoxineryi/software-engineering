package org.cn.kaito.auth.Service;

import org.cn.kaito.auth.Exception.CustomerException;

import javax.websocket.Session;

public interface SessionService {
    public void addByID(String userID, Session session);
    void addByToken(String token,Session session) throws CustomerException;
    void sendMessage(String userID,String message) throws CustomerException;

    void close(String token) throws CustomerException;
}
