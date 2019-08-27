package com.xust.properties.exception;

/**
 * @author: Victor
 * @create: 2019-08-15 19:22
 **/

public class CodeMsg {
    private int code;
    private String msg;

    //通用的错误码
    public static CodeMsg SUCCESS = new CodeMsg(500000,"0");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100,"服务端异常");
    public static CodeMsg BIND_ERROR = new CodeMsg(500101,"参数校验异常");
    public static CodeMsg MIAOSHA_LIMIT = new CodeMsg(500102,"访问次数过多");

    //登录模块
    public static CodeMsg SESSION_ERROR = new CodeMsg(500210,"session不存在或者已失效");
    public static CodeMsg PASSWORD_EMPTY = new CodeMsg(500211,"密码不能为空");
    public static CodeMsg MOBILE_EMPTY = new CodeMsg(500212,"手机号不能为空");
    public static CodeMsg MOBILE_ERROR = new CodeMsg(500213,"手机号格式错误");
    public static CodeMsg MOBILE_NOT_EXISTS = new CodeMsg(500214,"手机号不存在");
    public static CodeMsg MOBILE_EXISTS = new CodeMsg(500215,"手机号已存在");
    public static CodeMsg PASSWORD_ERROR = new CodeMsg(500216,"密码错误");

    //秒杀模块
    public static CodeMsg USER_NULL = new CodeMsg(500300,"用户不存在");
    public static CodeMsg STOCK_EMPTY = new CodeMsg(500301,"没有库存");
    public static CodeMsg ISMIAOSHA = new CodeMsg(500302,"已经秒杀过，不能重复秒杀");
    public static CodeMsg MIAOSHA_FAILED = new CodeMsg(500303,"秒杀失败");



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "CodeMsg{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }

    public CodeMsg fillArgs(Object...args){
        int code = this.code;
        String message = String.format(this.msg,args);
        return new CodeMsg(code,message);
    }


}
