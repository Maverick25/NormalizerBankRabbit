/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.normalizer.controller;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;
import dk.normalizer.dto.LoanResponseDTO;
import dk.normalizer.messaging.Receive;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author marekrigan
 */
public class NormalizeFromBankRabbit 
{
    private static Gson gson;
    
    public static void receiveMessages() throws IOException,InterruptedException
    {
        gson = new Gson();
        
        HashMap<String,Object> objects = Receive.setUpReceiver();
        
        QueueingConsumer consumer = (QueueingConsumer) objects.get("consumer");
        Channel channel = (Channel) objects.get("channel");
        
        LoanResponseDTO loanResponseDTO;
        
        while (true) 
        {
          QueueingConsumer.Delivery delivery = consumer.nextDelivery();
          String message = new String(delivery.getBody());
//          AMQP.BasicProperties props = delivery.getProperties();
//          AMQP.BasicProperties replyProps = new AMQP.BasicProperties.Builder().correlationId(props.getCorrelationId()).build();
//          System.out.println(props.getCorrelationId());
//          System.out.println(props.getReplyTo());
          
          
          
//          loanResponseDTO = gson.fromJson(message, LoanResponseDTO.class);
          
//          System.out.println(loanResponseDTO.toString());
          
//          sendMessage();

          channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
        
    }
    
    public static void sendMessage() throws IOException
    {
//        Send.sendMessage();
    }
}
