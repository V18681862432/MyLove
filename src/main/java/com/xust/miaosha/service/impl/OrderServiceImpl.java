package com.xust.miaosha.service.impl;

import com.xust.loginAndregister.entity.OrderKey;
import com.xust.loginAndregister.entity.User;
import com.xust.miaosha.entity.GoodsVo;
import com.xust.miaosha.entity.MiaoshaGoods;
import com.xust.miaosha.entity.Order;
import com.xust.miaosha.entity.OrderInfo;
import com.xust.miaosha.mapper.OrderMapper;
import com.xust.miaosha.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xust.properties.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 雒凯
 * @since 2019-08-16
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RedisService redisService;
    @Override
    public Order getMiaoshaOrderByUserId(long id, long goodsid) {
        return orderMapper.getMiaoshaOrderByUserId(id,goodsid);
    }


    @Transactional
    public OrderInfo createOrder(User user,GoodsVo goodsVo)
    {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goodsVo.getId().longValue());
        orderInfo.setGoodsName(goodsVo.getGoodsName());
        orderInfo.setGoodsPrice(goodsVo.getMiaoshaPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId(user.getId());
        long orderId = orderMapper.insert(orderInfo);
        Order miaoshaOrder = new Order();
        miaoshaOrder.setGoodsId(goodsVo.getId().longValue());
        miaoshaOrder.setOrderId(orderId);
        miaoshaOrder.setUserId(user.getId());
        redisService.set(OrderKey.is_Miaosha,user.getId()+"",miaoshaOrder);
        orderMapper.insertMiaoshaOrder(miaoshaOrder);
        return orderInfo;
    }

}
