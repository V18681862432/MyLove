package com.xust.properties.rabbitmq;

import com.xust.properties.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Victor
 * @create: 2019-08-22 17:01
 **/
@Service
public class MQSender {

    private static Logger logger = LoggerFactory.getLogger(MQReceiver.class);

    @Autowired
    AmqpTemplate amqpTemplate;

    public void sendMiaoshaMessage(MiaoShaMessage miaoShaMessage){
        amqpTemplate.convertAndSend(MQConfig.MIAOSHA_QUEUE,RedisService.bean2String(miaoShaMessage));
    }

//    public void send(Object message){
//        String str = RedisService.bean2String(message);
//        logger.info("send:"+str);
//        //发送的时候要指定一下发送到哪个queue
//        amqpTemplate.convertAndSend(MQConfig.QUEUE,str);
//    }
//
//    public void sendTopic(Object message){
//        String str = RedisService.bean2String(message);
//        logger.info("send:"+str);
//        //发送的时候要指定一下发送到哪个queue
//        amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE,"topic.key1",message+"1");
//        amqpTemplate.convertAndSend(MQConfig.TOPIC_EXCHANGE,"topic.key2",message+"2");
//
//    }
}
