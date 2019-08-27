package com.xust.properties.rabbitmq;

import com.xust.loginAndregister.entity.User;

/**
 * @author: Victor
 * @create: 2019-08-25 23:27
 **/

public class MiaoShaMessage {
    private User user;
    private Integer goodsid;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Integer goodsid) {
        this.goodsid = goodsid;
    }
}
