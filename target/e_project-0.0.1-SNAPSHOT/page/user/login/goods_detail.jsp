<%@ page import="com.xust.miaosha.entity.GoodsVo" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%><%--
  Created by IntelliJ IDEA.
  User: Xiaoluo
  Date: 2019-08-16
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品详情</title>
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
<%
    GoodsVo goodsVo = (GoodsVo)request.getAttribute("goodsVo");
%>
<div class="panel panel-default">
    <div class="panel-heading">秒杀商品详情</div>
    <div class="panel-body">
        <%
            if(request.getAttribute("user")==null)
            {
        %>
        <span> 您还没有登录，请登陆后再操作<br/></span>
        <%
            }
        %>
        <span>没有收货地址的提示。。。</span>
    </div>
    <table class="table" id="goodslist">
        <tr>
            <td>商品名称</td>
            <td colspan="3"><%=goodsVo.getGoodsName()%></td>
        </tr>
        <tr>
            <td>商品图片</td>
            <td colspan="3"><img src=/<%=goodsVo.getGoodsImg()%> width="200" height="200" /></td>
        </tr>
        <tr>

            <td id="miaoshaTip">
                <%
                if(request.getAttribute("miaoshaStatus").equals(0))
                {
                     %>
                <span>秒杀未开始，秒杀开始时间:<%=request.getAttribute("starttime")%></span>
                <%
                }
                %>
                <input type="hidden" id="remainSeconds" value=<%=request.getAttribute("remainSecond")%> />
                <%
                    if(request.getAttribute("miaoshaStatus").equals(1))
                    {
                %>
                <span>秒杀倒计时：<span id="countDown"><%=request.getAttribute("remainSecond")%></span>秒</span>
                <%
                    }
                %>
                <%
                    if(request.getAttribute("miaoshaStatus").equals(2))
                    {
                %>
                <span>秒杀已结束</span>
                <%
                    }
                %>

            </td>
            <td>
                <form id="miaoshaForm" method="post" action="/miaosha/goods/do_miaosha/<%=goodsVo.getId()%>">
                    <button class="btn btn-primary btn-block" type="submit" id="buyButton">立即秒杀</button>
                    <input type="hidden" name="goodsId" id=<%=goodsVo.getId()%> />
                </form>
            </td>
        </tr>
        <tr>
            <td>商品原价</td>
            <td colspan="3"><%=goodsVo.getGoodsPrice()%></td>
        </tr>
        <tr>
            <td>秒杀价</td>
            <td colspan="3"><%=goodsVo.getMiaoshaPrice()%></td>
        </tr>
        <tr>
            <td>库存数量</td>
            <td colspan="3"><%=goodsVo.getMiaoshaStock()%></td>
        </tr>
    </table>
</div>
<script>
    $(function(){
        countDown();
    });

    function countDown(){
        var remainSeconds = $("#remainSeconds").val();
        var timeout;
        if(remainSeconds > 0){//秒杀进行中，倒计时
            $("#buyButton").attr("disabled", false);
            $("#miaoshaTip").html("秒杀进行中");

        }else if(remainSeconds < 0){//秒杀未开始
            $("#buyButton").attr("disabled", true);
            timeout = setTimeout(function(){
                $("#countDown").text(remainSeconds - 1);
                $("#remainSeconds").val(remainSeconds - 1);
                countDown();
            },1000);
        }else{//秒杀已经结束
            $("#buyButton").attr("disabled", true);
            $("#miaoshaTip").html("秒杀已经结束");
        }
    }

</script>
</body>
</html>
