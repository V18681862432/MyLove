package com.xust.properties.redis;

/**
 * @author: Victor
 * @create: 2019-08-15 10:02
 * @comment: redis key's prefix
 **/

public interface KeyPrefix {
    public int expireSeconds();
    public String getPrefix();
}
