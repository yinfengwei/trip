

<%--
  Created by IntelliJ IDEA.
  User: yinfeng
  Date: 2016/11/3 0003
  Time: 23:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>轻松出行</title>

    <jsp:include page="${ctx}/common/head.jsp"></jsp:include>

</head>
<body style="background-color: #E4B89B;height: 100%;width: 100%">
    <jsp:include page="${ctx}/common/error.jsp"></jsp:include>
    <div style="margin-top: 10px;margin-left: 10px;margin-right: 10px;">
        <label style="float: left">用户 ：${userName}</label>

        <a href="${ctx}/trip-admin/login" style="float: right">
            注销登录
        </a>

    </div>
    <div class="container" style="margin: auto;max-width: 340px;min-height:550px;background: url('${ctx}/trip-admin/resource/img/background.png') no-repeat" >

        <div class="content" style="margin-top: 280px;">
            <p style="font-size: medium"> trip 休闲推荐会根据您的位置以及用户信息等进行景点推荐。</p>

            <a href="${ctx}/trip-admin/sight" style="font-size: 18px;text-align: center;">
                <img src="${ctx}/trip-admin/resource/img/timg.gif" style="width: 100%">
            </a>
            <p>戳上方大白的肚子查看出行推荐吧！</p>
        </div>

    </div>

</body>
<script>

</script>
</html>
