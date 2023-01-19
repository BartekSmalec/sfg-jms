package com.barteksmalec.sfgjms.listner;

import com.barteksmalec.sfgjms.config.JmsConfig;
import com.barteksmalec.sfgjms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class HelloMessageListner {

    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void listen(@Payload HelloWorldMessage helloWorldMessage, @Headers MessageHeaders messageHeaders, Message message) {
        System.out.println("I got a message !!" + JmsConfig.MY_QUEUE);
        System.out.println(helloWorldMessage);

    }

    @JmsListener(destination = JmsConfig.MY_SEND_RCV_QUEUE)
    public void listenForHello(@Payload HelloWorldMessage helloWorldMessage, @Headers MessageHeaders messageHeaders, Message message) throws JMSException {
        System.out.println("I got a message !!" + JmsConfig.MY_SEND_RCV_QUEUE);
        System.out.println(helloWorldMessage);

        HelloWorldMessage world = HelloWorldMessage.builder()
                .id(UUID.randomUUID())
                .message("World")
                .build();

        jmsTemplate.convertAndSend(message.getJMSReplyTo(), world);
    }
}
