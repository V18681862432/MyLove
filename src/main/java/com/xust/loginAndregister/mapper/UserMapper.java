package com.xust.loginAndregister.mapper;

import com.xust.loginAndregister.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 雒凯
 * @since 2019-08-14
 */

@Component
public interface UserMapper extends BaseMapper<User> {
    @Mapper
    @Select("select * from user where mobile=#{mobile}")
    public User findByMobile(@Param("mobile") String mobile);

    @Insert("insert into user(mobile,nickname,password,salt,register_date,last_login_date,login_count)" +
       " values(#{mobile},#{nickname},#{password},#{salt},#{register_date},#{last_login_date},#{login_count})")
    public void addUser(@Param("mobile")String mobile, @Param("nickname")
            String nickname, @Param("password") String password, @Param("salt")
            String salt, @Param("register_date")String register_date,@Param("last_login_date")
            String last_login_date,@Param("login_count")  int login_count
                        );

    @Update("update user set login_count=#{login_count},last_login_date=#{last_login_date} where mobile=#{mobile}")
    public void updateLogin_count(@Param("mobile")String mobile,@Param("login_count") int login_count,@Param("last_login_date")String last_login_date);

    @Update("update user set password=#{password} where mobile=#{mobile}")
    public void updatePassword(User user);
}
