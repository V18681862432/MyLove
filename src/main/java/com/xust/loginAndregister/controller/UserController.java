package com.xust.loginAndregister.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xust.loginAndregister.entity.*;
import com.xust.loginAndregister.utils.UUIDUtil;
import com.xust.loginAndregister.utils.ValidatorUtil;
import com.xust.properties.exception.CodeMsg;
import com.xust.properties.redis.RedisService;
import com.xust.loginAndregister.service.impl.UserServiceImpl;
import com.xust.loginAndregister.utils.Md5Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 雒凯
 * @since 2019-08-14
 */
@Controller
@RequestMapping("/loginAndregister/user")
public class UserController {

    private static String COOKIE_NAME = "tooken";
    @RequestMapping("/to_login")
    public String toLogin() {
        return "page/user/login";
    }

    @Autowired
    private UserServiceImpl service;
    @Autowired
    private RedisService redisService;

    @RequestMapping("/do_login")
    @ResponseBody
    public String login(@Valid LoginVo loginVo, HttpServletResponse response, Model model) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        if (StringUtils.isEmpty(password)){
            return CodeMsg.PASSWORD_EMPTY.getMsg();
        }
        User user = service.findByMobile(mobile);
        if (user == null) {
            return CodeMsg.MOBILE_NOT_EXISTS.getMsg();
        }
        else {
            String dbPass = user.getPassword();
            String dbSalt = user.getSalt();
            String calPass = Md5Util.InputPass2DbPass(password, dbSalt);
            if (dbPass.equals(calPass)) {
                int login_count = user.getLoginCount();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String last_login_date = simpleDateFormat.format(new Date());
                service.updateLogin_count(mobile,login_count+1,last_login_date);
                String token = UUIDUtil.uuid();
                redisService.set(MiaoshaUserKey.token,token,user);
                Cookie cookie = new Cookie(COOKIE_NAME,token);
                cookie.setMaxAge(MiaoshaUserKey.EXPRIE_SECOND);
                cookie.setPath("/");
                response.addCookie(cookie);
                model.addAttribute("user",user);
                return CodeMsg.SUCCESS.getMsg();
            }
        }
        return CodeMsg.PASSWORD_ERROR.getMsg();
    }


    @Transactional
    @RequestMapping("/to_regist")
    public String regist(@Valid RegisVo regisVo) {
        Random random = new Random();
        String nickname = regisVo.getNickname();
        String password = regisVo.getPassword();
        StringBuilder stringBuilder = new StringBuilder();
        String mobile = regisVo.getMobile();
        if(!ValidatorUtil.isMobile(mobile))
        {
            return "page/user/error";}
        User user = service.findByMobile(mobile);
        if (user != null) {
            return "page/user/error";
        } else {
            for (int i = 0; i < 15; i++) {
                char ch = (char) random.nextInt(128);
                stringBuilder.append(ch);
            }
            String salt = stringBuilder.toString();
            password = Md5Util.InputPass2DbPass(password, salt);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String register_date = simpleDateFormat.format(new Date());
            String last_login_date = simpleDateFormat.format(new Date());
            int login_count = 0;
            service.addUser(mobile, nickname, password, salt, register_date, last_login_date, login_count);
            return "page/user/login";
        }
    }


    /**
     * 优化前QPS=669.4336591243808
     * 优化后PQS=1086.7202782003912
     * 500线程并发循环十次
     * @param mobile
     * @return
     */
    @RequestMapping("/testfind")
    @ResponseBody
    public String test(String mobile){
        User user = service.findByMobile(mobile);
        return user.toString();
    }
}
