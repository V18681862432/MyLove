package com.xust.miaosha.service.impl;

import com.xust.loginAndregister.entity.User;
import com.xust.miaosha.entity.GoodsVo;
import com.xust.miaosha.entity.Order;
import com.xust.miaosha.entity.OrderInfo;
import com.xust.miaosha.mapper.OrderInfoMapper;
import com.xust.miaosha.mapper.OrderMapper;
import com.xust.miaosha.service.IOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 雒凯
 * @since 2019-08-16
 */
@Service
public class OrderInfoServiceImpl implements IOrderInfoService {

    @Autowired
    private OrderServiceImpl orderService;
    @Override
    public OrderInfo miaoshaAdd(User user, GoodsVo goodsVo) {
        return orderService.createOrder(user,goodsVo);
    }
}
