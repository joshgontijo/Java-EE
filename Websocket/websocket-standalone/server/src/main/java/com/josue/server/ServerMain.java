/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.glassfish.tyrus.server.Server;

/**
 *
 * @author Josue
 */
public class ServerMain {

    public static void main(String[] args) {
        Server server = new Server("localhost", 9090, "/ws", WSServerEndpoint.class);
        try {
            server.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Please press a key to stop the server.");
            reader.readLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            server.stop();
        }
    }

}
