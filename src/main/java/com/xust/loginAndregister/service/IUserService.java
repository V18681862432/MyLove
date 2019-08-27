package com.xust.loginAndregister.service;

import com.xust.loginAndregister.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 雒凯
 * @since 2019-08-14
 */
public interface IUserService {
    public User findByMobile(String mobile);

    public void addUser(String mobile, String nickname, String password, String salt,String register_date, String last_login_date,int login_count);

    public void updateLogin_count(String mobile,int login_count,String lastLoginDate);

    public void updatePassWord(String mobile,String passwordNew,String token) throws Exception;
}
