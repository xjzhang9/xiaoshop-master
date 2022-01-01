package com.xjzhang.pro.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author xjzhang
 * @version 1.0
 * @date 2021/12/26 15:12
 */
@Slf4j
@Configuration
public class MyRabbitConifg {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Bean
    public Jackson2JsonMessageConverter myRabbitConverterFuture() {
        return new Jackson2JsonMessageConverter();
    }

    @PostConstruct
    public void initPublicMessage() {
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
              log.info("<=== correlationData= {}, ack = {}, cause= {} ===>", correlationData.getId(), ack, cause);
            }
        });

        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int i, String s, String s1, String s2) {
                log.info("<=====fail message= {}, i = {}, s= {} ====>", message.getBody(), i, s);
            }
        });
    }

    @Bean
    public void createExchange() {
        Exchange exchange = new DirectExchange("xiaoshop-directExchange", true, false);
        amqpAdmin.declareExchange(exchange);
    }

    @Bean
    public void createQueue() {
        Queue queue = new Queue("xiaoshop-directQueue", true, false, false);
        amqpAdmin.declareQueue(queue);
    }

    @Bean
    public void createBinding() {
        // String destination, Binding.DestinationType destinationType, String exchange, String routingKey,
        Binding binding = new Binding("xiaoshop-binding", Binding.DestinationType.QUEUE, "xiaoshop-directExchange", "xiaoshop-directQueue", null);
        amqpAdmin.declareBinding(binding);
    }
}
