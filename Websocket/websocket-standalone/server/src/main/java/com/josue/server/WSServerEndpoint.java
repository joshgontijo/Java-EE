/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.server;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.CloseReason;
import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Josue
 */
@ServerEndpoint(value = "/chat")
public class WSServerEndpoint {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @OnOpen
    public void onOpen(Session session) {
        logger.log(Level.INFO, "Connected ... {0}", session.getId());
    }

    @OnMessage
    public String onMessage(String message, Session session) {
        switch (message) {
            case "quit":
                try {
                    session.close(new CloseReason(CloseCodes.NORMAL_CLOSURE, "Game ended"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
        return "Response from server: " + message;
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.info(String.format("Session %s closed because of %s", session.getId(), closeReason));
    }
}
