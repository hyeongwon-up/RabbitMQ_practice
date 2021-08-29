package com.hw.rabbitmq;

import ch.qos.logback.classic.pattern.MessageConverter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;


public class PublisherConfig {
    private static final String EXCHANGE_NAME = "sample.exchange";
    private static final String Queue_NAME = "sample.queue";
    public static final String ROUTING_KEY = "sample.key";

    @Bean
    TopicExchange exchange(){
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    Queue queue() {
        return new Queue(Queue_NAME);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory factory, MessageConverter converter) {
        RabbitTemplate template = new RabbitTemplate(factory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }
}
