package com.xust.loginAndregister.entity;

import com.xust.miaosha.entity.Order;
import com.xust.properties.redis.BasePrefix;

/**
 * @author: Victor
 * @create: 2019-08-26 00:27
 **/

public class OrderKey extends BasePrefix {
    private OrderKey(int expireSeconds, String prefix){
        super(expireSeconds,prefix);
    }

    private OrderKey(String prefix){
        super(0,prefix);
    }

    public static OrderKey is_Miaosha = new OrderKey("succ");
    public static OrderKey Order = new OrderKey("orderinfo");
}
