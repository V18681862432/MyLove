package com.xust.miaosha.mapper;

import com.xust.loginAndregister.entity.User;
import com.xust.miaosha.entity.GoodsVo;
import com.xust.miaosha.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xust.miaosha.entity.OrderInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
public interface OrderMapper {
    @Select("select * from miaosha_order where user_id=#{id} and goods_id=#{goods_id}")
    public Order getMiaoshaOrderByUserId(@Param("id") long id, @Param("goods_id") long goods_id);

    @Insert("insert into miaosha_order (user_id, goods_id, order_id)values(#{userId}, #{goodsId}, #{orderId})")
    void insertMiaoshaOrder(Order miaoshaOrder);

    @Insert("insert into order_info(user_id, goods_id, goods_name, goods_count, goods_price, order_channel, status, create_date)values("
            + "#{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel},#{status},#{createDate} )")
    long insert(OrderInfo orderInfo);
}
