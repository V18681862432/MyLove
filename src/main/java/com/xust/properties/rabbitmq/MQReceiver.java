package com.xust.properties.rabbitmq;

import com.xust.loginAndregister.entity.MiaoshaUserKey;
import com.xust.loginAndregister.entity.OrderKey;
import com.xust.loginAndregister.entity.User;
import com.xust.miaosha.entity.GoodsVo;
import com.xust.miaosha.entity.MiaoshaGoods;
import com.xust.miaosha.entity.Order;
import com.xust.miaosha.entity.OrderInfo;
import com.xust.miaosha.service.impl.GoodsServiceImpl;
import com.xust.miaosha.service.impl.OrderInfoServiceImpl;
import com.xust.miaosha.service.impl.OrderServiceImpl;
import com.xust.properties.redis.RedisService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Victor
 * @create: 2019-08-22 17:01
 **/
@Service
public class MQReceiver {

    @Autowired
    GoodsServiceImpl goodsService;
    @Autowired
    OrderServiceImpl orderService;
    @Autowired
    OrderInfoServiceImpl orderInfoService;
    @Autowired
    RedisService redisService;
    private static Logger logger = LoggerFactory.getLogger(MQReceiver.class);


    @RabbitListener(queues = MQConfig.MIAOSHA_QUEUE)
    public void receive(String message) {
        MiaoShaMessage mm = RedisService.string2Bean(message, MiaoShaMessage.class);
        User user = mm.getUser();
        Integer goodsid = mm.getGoodsid();
        GoodsVo goodsVo = goodsService.getGoodsVoById(goodsid);
        int stock = goodsVo.getMiaoshaStock();
        if (stock <= 0) {
        }
        //判断是否秒杀到了
        Order order = orderService.getMiaoshaOrderByUserId(user.getId(), goodsid);
        if (order == null) {
            //减库存 写入秒杀订单 写入订单
            boolean success = goodsService.reduceStock(goodsVo);
            if (success) {
                OrderInfo orderInfo = orderInfoService.miaoshaAdd(user, goodsVo);
                redisService.set(OrderKey.Order,user.getId()+"",orderInfo);
            }
        }
    }


    /**
     * Direct  交换机模式：Exchange
     */
//    @RabbitListener(queues = MQConfig.QUEUE)
//    public void receive(String message){
//        logger.info("receive:"+message);
//    }
//
//    @RabbitListener(queues = MQConfig.TOPIC_QUEUE1)
//    public void receiveTopic1(String message){
//        logger.info("receive topic queue1:"+message);
//    }
//
//    @RabbitListener(queues = MQConfig.TOPIC_QUEUE2)
//    public void receiveTopic2(String message){
//        logger.info("receive topic queue2:"+message);
//    }
}
