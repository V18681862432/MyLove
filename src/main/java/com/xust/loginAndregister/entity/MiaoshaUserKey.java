package com.xust.loginAndregister.entity;

import com.xust.properties.redis.BasePrefix;

/**
 * @author: Victor
 * @create: 2019-08-15 22:55
 **/

public class MiaoshaUserKey extends BasePrefix {

    public static int EXPRIE_SECOND = 2400*24*2;
    private MiaoshaUserKey(int EXPRIE_SECOND,String prefix){
        super(EXPRIE_SECOND,prefix);
    }
    public static MiaoshaUserKey token = new MiaoshaUserKey(EXPRIE_SECOND,"tk");

    public static MiaoshaUserKey id = new MiaoshaUserKey(EXPRIE_SECOND,"id");

}
