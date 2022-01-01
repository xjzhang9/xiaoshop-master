package com.xjzhang.pro.service.impl;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.xjzhang.pro.model.entity.Brand;
import com.xjzhang.pro.service.RabbitClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * @author xjzhang
 * @version 1.0
 * @date 2021/12/26 13:29
 */
@Slf4j
@RabbitListener(queues = {"xiaoshop-directQueue"})
public class RabbitClientServiceImpl implements RabbitClientService {
    @Autowired
    private RabbitTemplate rabbitTemplate;



    public void publicMessage() {
        Brand brand = new Brand();
        brand.setBrandId(123L);
        brand.setName("123");
        brand.setDescript("1234567890");

        rabbitTemplate.convertAndSend("xiaoshop-directExchange", "xiaoshop-directQueue", brand, new CorrelationData(UUID.randomUUID().toString()));
    }

   @RabbitHandler
    public void consumerMessage(Message message, Brand brand, Channel channel) {
        log.info("<=== message={},brand= {}, channel = {} ===>", message, brand, channel);
    }
}
