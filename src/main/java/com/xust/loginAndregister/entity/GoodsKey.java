package com.xust.loginAndregister.entity;

import com.xust.properties.redis.BasePrefix;

/**
 * @author: Victor
 * @create: 2019-08-15 10:26
 * @comment: 前缀
 **/

public class GoodsKey extends BasePrefix {
    private GoodsKey(int expireSeconds, String prefix){
        super(expireSeconds,prefix);
    }

    private GoodsKey(String prefix){
        super(0,prefix);
    }

    public static GoodsKey prefix = new GoodsKey(60,"id");
    public static GoodsKey Good_Stock = new GoodsKey(0,"goods_stock");
}
