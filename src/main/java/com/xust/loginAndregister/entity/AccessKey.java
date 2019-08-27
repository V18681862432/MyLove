package com.xust.loginAndregister.entity;

import com.xust.properties.redis.BasePrefix;

/**
 * @author: Victor
 * @create: 2019-08-26 10:32
 **/

public class AccessKey extends BasePrefix {
    private AccessKey(int expireSeconds, String prefix){
        super(expireSeconds,prefix);
    }

    private AccessKey(String prefix){
        super(0,prefix);
    }

    public static AccessKey ACCESS = new AccessKey(10,"access");
    public static AccessKey HIDDEN = new AccessKey(3,"hidden");

}
