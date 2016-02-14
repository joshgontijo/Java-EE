/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Josue
 */
public class Main {
    
    private static final Map<String, ChatClientEndpoint> clients = new HashMap<>();
    
    public static void main(String[] args) throws URISyntaxException {
        
        
        //async input
        new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                while(true){
                    try {
                        System.out.println("Client id: ");
                        String clientd = br.readLine();
                        ChatClientEndpoint client = clients.get(clientd);
                        if(client == null){
                            System.err.println("CLIENT DOESNT EXIST, CREATING...");
                            client = createClient(clientd);
                        }
                        System.out.println("Message: ");
                        String readLine = br.readLine();
                        client.sendMessage(readLine);
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
    }
    
    private static ChatClientEndpoint createClient(String id){
        try {
            final ChatClientEndpoint clientEndPoint = new ChatClientEndpoint(new URI("ws://localhost:8080/ws-web-server/ws/" + id));
            clientEndPoint.addMessageHandler(new ChatClientEndpoint.MessageHandler() {
                @Override
                public void handleMessage(String message) {
                    System.out.println(message);
                }
            });
            
            clients.put(id, clientEndPoint);
            
            return clientEndPoint;
        } catch (URISyntaxException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
