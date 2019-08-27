<%@ page import="com.xust.miaosha.entity.GoodsVo" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Xiaoluo
  Date: 2019-08-14
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>商品列表</title>
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
<div class="panel panel-default">
    <div class="panel-heading">秒杀商品列表</div>
    <table class="table" id="goodslist">
        <tr><td>商品名称</td><td>商品图片</td><td>商品原价</td><td>秒杀价</td><td>库存数量</td><td>详情</td></tr>

            <%
            List<GoodsVo> goodsVoList = ( List<GoodsVo>)request.getAttribute("goodslist");
            for ( GoodsVo list :goodsVoList ) {
            %>
        <tr>
            <th><%=list.getGoodsName()%></th>
            <th><img src=/<%=list.getGoodsImg()%>/></th>
            <th><%=list.getGoodsPrice()%></th>
            <th><%=list.getMiaoshaPrice()%></th>
            <th><%=list.getMiaoshaStock()%></th>
            <th><a href="/miaosha/goods/to_detail/<%=list.getId()%>">详情</a></th>
        </tr>
            <%
            }
            %>

    </table>
</div>
</body>
</html>
