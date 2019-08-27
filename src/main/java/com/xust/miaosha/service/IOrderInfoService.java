package com.xust.miaosha.service;

import com.xust.loginAndregister.entity.User;
import com.xust.miaosha.entity.GoodsVo;
import com.xust.miaosha.entity.Order;
import com.xust.miaosha.entity.OrderInfo;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 雒凯
 * @since 2019-08-16
 */
public interface IOrderInfoService {
    public OrderInfo miaoshaAdd(User user, GoodsVo goodsVo);

}
