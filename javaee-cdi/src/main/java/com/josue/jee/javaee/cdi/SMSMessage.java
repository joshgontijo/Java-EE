/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.josue.jee.javaee.cdi;

import com.josue.jee.javaee.cdi.qualifier.Message;
import com.josue.jee.javaee.cdi.qualifier.MessageType;

@Message(MessageType.SMS)
public class SMSMessage implements IMessage {

    @Override
    public String message() {
        return "SMS message";
    }

}
