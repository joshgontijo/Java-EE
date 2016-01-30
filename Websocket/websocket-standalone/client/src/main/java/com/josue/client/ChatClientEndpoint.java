/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.client;

import java.net.URI;
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@ClientEndpoint
public class ChatClientEndpoint {

    private Session userSession = null;
    private MessageHandler messageHandler;

    public ChatClientEndpoint(final URI endpointURI) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, endpointURI);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @OnOpen
    public void onOpen(final Session userSession) {
        this.userSession = userSession;
    }

    @OnClose
    public void onClose(final Session userSession, final CloseReason reason) {
        this.userSession = null;
    }

    @OnMessage
    public void onMessage(final String message) {
        if (messageHandler != null) {
            messageHandler.handleMessage(message);
        }
    }

    public void addMessageHandler(final MessageHandler msgHandler) {
        messageHandler = msgHandler;
    }

    public void sendMessage(final String message) {
        userSession.getAsyncRemote().sendText(message);
    }

    public static interface MessageHandler {
        public void handleMessage(String message);
    }
}
