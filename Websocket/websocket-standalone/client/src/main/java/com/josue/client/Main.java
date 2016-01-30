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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Josue
 */
public class Main {
    
    public static void main(String[] args) throws URISyntaxException {
        final ChatClientEndpoint clientEndPoint = new ChatClientEndpoint(new URI("ws://localhost:9090/ws/chat"));
        clientEndPoint.addMessageHandler(new ChatClientEndpoint.MessageHandler() {
            @Override
            public void handleMessage(String message) {
                System.out.println(message);
            }
        });
        
        //async input
        new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                while(true){
                    try {
                        System.out.println("Message: ");
                        String readLine = br.readLine();
                        clientEndPoint.sendMessage(readLine);
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
    }
}
