

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
<body style="background-color: #E4B89B">

    <jsp:include page="${ctx}/common/error.jsp"></jsp:include>
    <div class="container" style="margin-bottom: 150px;max-width: 340px;background: url('${ctx}/trip-admin/resource/img/background.png') no-repeat" >

        <form role="form" style="margin-top: 270px;" method="post" action="${ctx}/trip-admin/check">

            <input name="userName" class="form-control" type="text" style="font-size: 18px;height: 40px;"placeholder="用户名" required value="${userName}">
            <a href="${ctx}/trip-admin/register" style="font-size: 18px;float: right">注册账号</a>
            <br>
            <input name="password" class="form-control" type="password" style="font-size: 18px;height: 40px;"placeholder="密码" required>
            <a href="${ctx}/trip-admin/forget" style="font-size: 18px;float: right">忘记密码</a>
            <br>
            <button type="submit" value="提交" class="btn btn-primary btn-block btn-lg"style="font-size: 22px;text-align: center;margin-top: 20px;">立即登录</button>
        </form>
        <br>
        <div>
            <p style="font-size: 18px;float: right">不想登录
                <a href="${ctx}/trip-admin/main" style="font-size: 18px;text-align: center;">随便瞧瞧</a>
            </p>
        </div>
        <div>
            <p style="font-size: 17px;float: right;color: white">Tips:
                <span style="font-size: 17px;text-align: center;">点击左下方图标退出登录重新定位</span>
            </p>
        </div>

    </div>

</body>

</html>
