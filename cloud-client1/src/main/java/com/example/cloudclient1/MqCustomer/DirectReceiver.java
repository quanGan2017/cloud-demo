package com.example.cloudclient1.MqCustomer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DirectReceiver {

    @RabbitHandler
    @RabbitListener(queues = "TestDirectQueue")//监听的队列名称 TestDirectQueue
    public void process1(Map testMessage) {
        System.out.println("DirectReceiver消费者收到消息  : " + testMessage.toString());
    }

    @RabbitHandler
    @RabbitListener(queues = "topic.man")//监听的队列名称 TestDirectQueue
    public void process2(Map testMessage) {
        System.out.println("TopicManReceiver消费者收到消息  : " + testMessage.toString());
    }
    @RabbitHandler
    @RabbitListener(queues = "topic.woman")//监听的队列名称 TestDirectQueue
    public void process3(Map testMessage) {
        System.out.println("TopicTotalReceiver消费者收到消息  : " + testMessage.toString());
    }

    @RabbitHandler
    @RabbitListener(queues = "fanout.A")
    public void processA(Map testMessage) {
        System.out.println("FanoutReceiverA消费者收到消息  : " +testMessage.toString());
    }

    @RabbitHandler
    @RabbitListener(queues = "fanout.B")
    public void processB(Map testMessage) {
        System.out.println("FanoutReceiverB消费者收到消息  : " +testMessage.toString());
    }


    @RabbitHandler
    @RabbitListener(queues = "fanout.C")
    public void processC(Map testMessage) {
        System.out.println("FanoutReceiverC消费者收到消息  : " +testMessage.toString());
    }


}