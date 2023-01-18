package com.barteksmalec.sfgjms.listner;

import com.barteksmalec.sfgjms.config.JmsConfig;
import com.barteksmalec.sfgjms.model.HelloWorldMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Message;

@Component
public class HelloMessageListner {

    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void listen(@Payload HelloWorldMessage helloWorldMessage, Message message) {
        System.out.println("I got a message !!");
        System.out.println(helloWorldMessage);

    }
}
