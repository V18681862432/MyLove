package com.xust.loginAndregister.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xust.loginAndregister.entity.MiaoshaUserKey;
import com.xust.loginAndregister.entity.User;
import com.xust.loginAndregister.entity.UserKey;
import com.xust.loginAndregister.mapper.UserMapper;
import com.xust.loginAndregister.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xust.loginAndregister.utils.Md5Util;
import com.xust.properties.exception.CodeMsg;
import com.xust.properties.exception.GlobalExceptionHandler;
import com.xust.properties.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 雒凯
 * @since 2019-08-14
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private  RedisService service;
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByMobile(String mobile) {
        /*
        return userMapper.findByMobile(mobile);
         */

        User user = service.get(UserKey.getById,mobile,User.class);
        if(user!=null){
            return user;
        }else{
            user = userMapper.findByMobile(mobile);
            service.set(UserKey.getById,mobile,user);
            return user;
        }

    }

    public void updatePassWord(String mobile,String passwordNew,String token) throws Exception {
        User user = findByMobile(mobile);
        if (user == null){
            throw new Exception(CodeMsg.USER_NULL.getMsg());
        }
        else{
            //更新数据库
            User newUser = new User();
            newUser.setMobile(user.getMobile());
            newUser.setPassword(Md5Util.InputPass2DbPass(passwordNew,user.getSalt()));
            userMapper.updatePassword(newUser);
            //处理缓存
            service.del(MiaoshaUserKey.id,mobile);
            service.set(MiaoshaUserKey.token,token,user);
        }
    }

    @Override
    public void addUser(String mobile, String nickname, String password, String salt, String register_date, String last_login_date, int login_count) {
        userMapper.addUser(mobile,nickname,password,salt,register_date,last_login_date,login_count);

    }

    @Override
    public void updateLogin_count(String mobile, int login_count,String lastLoginDate) {
        userMapper.updateLogin_count(mobile,login_count,lastLoginDate);
    }

}
