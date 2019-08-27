package com.xust.loginAndregister.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: Victor
 * @create: 2019-08-17 23:34
 **/

public class GetTookenUtil {
    public static String getCookieValue(HttpServletRequest request, String cookiName) {
        Cookie[]  cookies = request.getCookies();
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals(cookiName)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    public static void main(String[] args) {

    }
}
