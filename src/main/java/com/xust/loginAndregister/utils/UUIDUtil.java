package com.xust.loginAndregister.utils;

import java.util.UUID;

/**
 * @author: Victor
 * @create: 2019-08-15 22:52
 **/

public class UUIDUtil {
    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
