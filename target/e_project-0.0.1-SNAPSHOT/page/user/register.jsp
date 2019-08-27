<%--
  Created by IntelliJ IDEA.
  User: Xiaoluo
  Date: 2019-08-15
  Time: 11:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
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
<form name="registForm" id="registForm" method="post"  style="width:50%; margin:0 auto" action="/loginAndregister/user/to_regist">

    <h2 style="text-align:center; margin-bottom: 20px">用户注册</h2>

    <div class="form-group">
        <div class="row">
            <label class="form-label col-md-4">请输入手机号</label>
            <div class="col-md-5">
                <input id="mobile" name="mobile" class="form-control" type="text"  placeholder="手机号" required="true"/>
            </div>
        </div>
    </div>


    <div class="form-group">
        <div class="row">
            <label class="form-label col-md-4">请输入昵称</label>
            <div class="col-md-5">
                <input id="nickname" name="nickname" class="form-control" type="text"  placeholder="昵称" required="true"/>
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
        <div class="col-md-5">
            <button class="btn btn-primary btn-block" type="submit" onclick="register()" style="margin-top: 20px;">注册</button>
        </div>
    </div>


</form>
</body>
</html>
