package com.xust.miaosha.controller;

import com.xust.loginAndregister.entity.MiaoshaUserKey;
import com.xust.loginAndregister.entity.User;
import com.xust.properties.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author: Victor
 * @create: 2019-08-18 23:01
 **/
@Controller
@RequestMapping("miaosha/user")
public class JMeterUserController {

    @Autowired
    RedisService redisService;
    @RequestMapping("/info")
    @ResponseBody
    public User info(String token){
        User user = redisService.get(MiaoshaUserKey.token,token,User.class);
        return user;
    }
}
