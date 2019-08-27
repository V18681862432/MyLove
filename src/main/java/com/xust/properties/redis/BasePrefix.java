package com.xust.properties.redis;

/**
 * @author: Victor
 * @create: 2019-08-15 10:04
 **/

public abstract class BasePrefix implements KeyPrefix{

    private int expireseconds;
    private String prefix;

    public BasePrefix(int expireseconds, String prefix) {
        this.expireseconds = expireseconds;
        this.prefix = prefix;
    }

    public BasePrefix() { }

    /**
     * 如果不指定过期时间，则默认为0
     * @param prefix
     */
    public BasePrefix(String prefix) {
        this(0,prefix);
    }

    /**
     * 默认0代表用不过期
     * @return
     */
    @Override
    public int expireSeconds() {
         return expireseconds;
    }

    @Override
    public String getPrefix() {
        String name = getClass().getSimpleName();
        return name+"-"+prefix;
    }
}
