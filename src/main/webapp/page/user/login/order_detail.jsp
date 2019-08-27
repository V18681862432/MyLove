<%@ page import="com.xust.miaosha.entity.GoodsVo" %>
<%@ page import="com.xust.miaosha.entity.OrderInfo" %><%--
  Created by IntelliJ IDEA.
  User: Xiaoluo
  Date: 2019-08-17
  Time: 21:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单详情</title>
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
    OrderInfo orderInfo = (OrderInfo)request.getAttribute("orderInfo");
%>
<div class="panel panel-default">
    <div class="panel-heading">秒杀订单详情</div>
    <table class="table" id="goodslist">
        <tr>
            <td>商品名称</td>
            <td colspan="3"><%=goodsVo.getGoodsName()%></td>
        </tr>
        <tr>
            <td>商品图片</td>
            <td colspan="2"><img src=/<%=goodsVo.getGoodsImg()%> width="200" height="200" /></td>
        </tr>
        <tr>
            <td>订单价格</td>
            <td colspan="2"><%=goodsVo.getMiaoshaPrice()%></td>
        </tr>
        <tr>
            <td>下单时间</td>
            <td colspan="2"><%=orderInfo.getCreateDate()%></td>
        </tr>
        <tr>
            <td>订单状态</td>
            <td >
                <%
                  switch (orderInfo.getStatus()){
                      case 0:
                %>
                    <span>未支付</span>
                <%
                        break;
                        case 1:
                %>
                <span>待发货</span>
                <%
                        break;
                        case 2:
                %>
                <span>已发货</span>
                <%
                    break;
                    case 3:
                %>
                <span>已收货</span>
                <%
                    break;
                    case 4:
                %>
                <span>已退款</span>
                <%
                    break;
                    case 5:
                %>
                <span>已完成</span>
                <%
                    break;
                    }
                 %>
            </td>
            <td>
                <button class="btn btn-primary btn-block" type="submit" id="payButton">立即支付</button>
            </td>
        </tr>
        <tr>
            <td>收货人</td>
            <td colspan="2">XXX  18812341234</td>
        </tr>
        <tr>
            <td>收货地址</td>
            <td colspan="2">北京市昌平区回龙观龙博一区</td>
        </tr>
    </table>
</div>

</body>
</html>
