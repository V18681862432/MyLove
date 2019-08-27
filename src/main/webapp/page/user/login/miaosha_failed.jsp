<%@ page import="com.xust.properties.exception.CodeMsg" %><%--
  Created by IntelliJ IDEA.
  User: Xiaoluo
  Date: 2019-08-16
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>秒杀失败</title>
</head>
<body>
    <%
        CodeMsg codeMsg = (CodeMsg)request.getAttribute("errmsg");
    %>
    <script type="text/javascript">
        alert("<%=codeMsg.getMsg()%>");
        window.location.href="/miaosha/goods/to_list";
    </script>
</body>
</html>
