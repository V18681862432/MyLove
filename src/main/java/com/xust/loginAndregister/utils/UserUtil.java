package com.xust.loginAndregister.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xust.loginAndregister.entity.LoginVo;
import com.xust.loginAndregister.entity.User;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: Victor
 * @create: 2019-08-22 09:35
 **/

public class UserUtil {
    private static void createUser(int count) throws Exception {
        List<User> users = new ArrayList<User>(count);
        //生成用户
        for (int i = 0; i < count; i++) {
            User user = new User();
            long mobile = 1300000000L+i;
            user.setMobile(String.valueOf(mobile));
            user.setLoginCount(1);
            user.setNickname("user" + i);
            user.setRegisterDate(new Date());
            user.setSalt("vic1318494971tor");
            user.setPassword(Md5Util.InputPass2DbPass("123456", user.getSalt()));
            users.add(user);
        }
//        System.out.println("create user");
//		//插入数据库
//		Connection conn = DBUtil.getConn();
//		String sql = "insert into user(login_count, nickname, register_date, salt, password, mobile)values(?,?,?,?,?,?)";
//		PreparedStatement pstmt = conn.prepareStatement(sql);
//		for(int i=0; i < users.size();i++) {
//			User user = users.get(i);
//			pstmt.setInt(1, user.getLoginCount());
//			pstmt.setString(2, user.getNickname());
//			pstmt.setTimestamp(3, new Timestamp(user.getRegisterDate().getTime()));
//			pstmt.setString(4, user.getSalt());
//			pstmt.setString(5, user.getPassword());
//			pstmt.setString(6, user.getMobile());
//			pstmt.addBatch();
//		}
//		pstmt.executeBatch();
//		pstmt.close();
//		conn.close();
//		System.out.println("insert to db");
//        //登录，生成token

        System.out.println(users.size());
        for (int i = 0; i < 1995; i++) {
            User user = users.get(i);
        }
    }


    public static void main(String[] args) throws Exception {
        createUser(5000);
    }
}