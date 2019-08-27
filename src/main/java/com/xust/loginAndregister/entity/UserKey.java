package com.xust.loginAndregister.entity;

import com.xust.properties.redis.BasePrefix;

/**
 * @author: Victor
 * @create: 2019-08-15 10:26
 * @comment: 前缀
 **/

public class UserKey extends BasePrefix {
    private UserKey(int expireSeconds,String prefix){
        super(expireSeconds,prefix);
    }

    private UserKey(String prefix){
        super(0,prefix);
    }

    public static UserKey getById = new UserKey("id");
    public static UserKey getByNickName = new UserKey("nickname");
}
