<%--
  Created by IntelliJ IDEA.
  User: Xiaoluo
  Date: 2019-08-14
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>登录</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

    <!-- jquery -->
    <script type="text/javascript" src="/resource/js/jquery.min.js"></script>
    <!-- bootstrap -->
    <link rel="stylesheet" type="text/css" href="/resource/bootstrap/css/bootstrap.min.css" />
    <script type="text/javascript" src="/resource/bootstrap/js/bootstrap.min.js"></script>
    <!-- jquery-validator -->
    <script type="text/javascript" src="/resource/jquery-validation/jquery.validate.min.js"></script>
    <script type="text/javascript" src="/resource/jquery-validation/localization/messages_zh.min.js"></script>
    <!-- layer -->
    <script type="text/javascript" src="/resource/layer/layer.js"></script>
    <!-- md5.js -->
    <script type="text/javascript" src="/resource/js/md5.min.js"></script>
    <!-- common.js -->
    <script type="text/javascript" src="/resource/js/common.js"></script>

</head>
<body>
<form name="loginForm" id="loginForm" method="post"  style="width:50%; margin:0 auto">

    <h2 style="text-align:center; margin-bottom: 20px">用户登录</h2>

    <div class="form-group">
        <div class="row">
            <label class="form-label col-md-4">请输入手机号码</label>
            <div class="col-md-5">
                <input id="mobile" name = "mobile" class="form-control" type="text" placeholder="手机号码" required="true"  minlength="11" maxlength="11" />
            </div>
            <div class="col-md-1">
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="row">
            <label class="form-label col-md-4">请输入密码</label>
            <div class="col-md-5">
                <input id="password" name="password" class="form-control" type="password"  placeholder="密码" required="true"/>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-3">
            <button class="btn btn-primary btn-block" type="reset" onclick="reset()" style="margin-top: 20px;">重置</button>
        </div>
        <div class="col-md-3">
            <button class="btn btn-primary btn-block" type="submit" onclick="login()" style="margin-top: 20px;">登录</button>
        </div>
        <div class="col-md-3">
            <button class="btn btn-primary btn-block" type="submit" onclick="regis()" style="margin-top: 20px;">注册</button>
        </div>
    </div>

</form>
</body>
<script>
    function login(){
        $("#loginForm").validate({
            submitHandler:function(form){
                doLogin();
            }
        });
    }
    function regis() {
        window.location.href="/page/user/register.jsp";
    };
    function doLogin(){
        g_showLoading();

        var inputPass = $("#password").val();
        var salt = g_passsword_salt;
        var str = ""+salt.charAt(0)+salt.charAt(2)+inputPass+salt.charAt(5)+salt.charAt(8)+salt.charAt(4)+salt.charAt(14);

        $.ajax({
            url: "/loginAndregister/user/do_login",
            type : 'POST',
            xhrFields: {
                withCredentials: true
            },
            crossDomain: true,
            data:{
                mobile:$("#mobile").val(),
                password: inputPass
            },
            success:function(data){
                layer.closeAll();

                if(data == 0){
                    layer.msg("成功");
                    window.location.href="/miaosha/goods/to_list";
                }else{
                    alert(data);
                    window.location.href="/page/user/login.jsp";
                }
            },
            error:function(){
                layer.closeAll();
            }
        });
    }
</script>
</body>
</html>
