package com.xust.miaosha.mapper;

import com.xust.loginAndregister.entity.User;
import com.xust.miaosha.entity.GoodsVo;
import com.xust.miaosha.entity.Order;
import com.xust.miaosha.entity.OrderInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 雒凯
 * @since 2019-08-16
 */
@Component
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {
    public OrderInfo miaoshaAdd(User user, GoodsVo goodsVo);
}
