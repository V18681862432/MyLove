package com.xust.properties.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Victor
 * @create: 2019-08-22 17:02
 **/

@Configuration
public class MQConfig {

    public static final String MIAOSHA_QUEUE = "miaoshaqueue";
    public static final String QUEUE = "queue";
    public static final String TOPIC_QUEUE1 = "topic.queue1";
    public static final String TOPIC_QUEUE2 = "topic.queue2";
    public static final String TOPIC_EXCHANGE = "topic.exchange";

    @Bean
    public Queue queue(){
        return new Queue(MIAOSHA_QUEUE,true);
    }
    @Bean
    public Queue Topicqueue1(){
        return new Queue(TOPIC_QUEUE1,true);
    }
    @Bean
    public Queue Topicqueue2(){
        return new Queue(TOPIC_QUEUE2,true);
    }

    /**
     * 交换机
     * @return
     */
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE);
    }
    @Bean
    public Binding topicBinding1() {
        return BindingBuilder.bind(Topicqueue1()).to(topicExchange()).with("topic.key1");
    }
    @Bean
    public Binding topicBinding2() {
        return BindingBuilder.bind(Topicqueue2()).to(topicExchange()).with("topic.#");
    }
}
