package com.xust.loginAndregister.utils;


import org.apache.commons.codec.digest.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: Victor
 * @create: 2019-08-14 15:14
 **/

public class Md5Util {
    /**
     * 转换
     * @param src
     * @return
     */
    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }

    private static final String salt = "vic1318494971tor";


    public static String inputPassForm(String inputPass){
        String str = ""+salt.charAt(0)+salt.charAt(2)+inputPass+salt.charAt(5)+salt.charAt(8)+salt.charAt(4)+salt.charAt(14);
        return md5(str);
    }

    public static String FormPass2DbPass(String formPass,String salt){
        String str = ""+salt.charAt(0)+salt.charAt(2)+formPass+salt.charAt(5)+salt.charAt(8)+salt.charAt(4)+salt.charAt(14);
        return md5(str);
    }

    public static String InputPass2DbPass(String inputPass,String salt){
        String formPass = inputPassForm(inputPass);
        String dbPass = FormPass2DbPass(formPass,salt);
        return dbPass;
    }

    public static void main(String[] args) {
        System.out.println(inputPassForm("123456"));
        //vc123456193o(反查彩虹表得到这个值，而不是123456)
        // 2ce1c46715a245825cdc048292304fe7
        System.out.println(FormPass2DbPass(inputPassForm("123456"),"vic1318494971tor"));
//        System.out.println(InputPass2DbPass("123456","vic1318494971tor"));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(new Date()));

    }
}

