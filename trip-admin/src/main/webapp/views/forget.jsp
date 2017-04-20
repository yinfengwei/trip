

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
    <style>
        .top_tiv_cover{
            position: fixed;
            top: 0;
            background-color: rgba(0,0,0,0.2);
            width: 100%;
            height: 7%;
            line-height: 100%;

        }
        .icon-icon{
            position: absolute;
            top: 50%;
            left: 2%;
            transform: translateY(-50%);
            background-color: rgba(0,0,0,0);
            font-size: 2.5em;
        }
    </style>

</head>
<body style="background-color: #E4B89B">
<jsp:include page="${ctx}/common/error.jsp"></jsp:include>
<div class="top_tiv_cover">

    <a href="${ctx}/trip-admin/login">
        <i class="iconfont icon-icon"></i>
    </a>
</div>
<div class="container" style="max-width: 340px;background: url('${ctx}/trip-admin/resource/img/background.png') no-repeat">

    <form role="form" style="margin-top: 280px;margin-bottom: 100px;" method="post" action="${ctx}/trip-admin/update">

        <input name="userName" id="userName" class="form-control" type="text" style="font-size: 18px;height: 40px;"placeholder="用户名" required>
        <br>
        <input name="phone" class="form-control" type="text" style="font-size: 18px;height: 40px;"placeholder="手机号码" required>
        <br>
        <input name="password" class="form-control" type="password" style="font-size: 18px;height: 40px;"placeholder="新密码" required>
        <br>
        <button type="submit" value="提交" class="btn btn-primary btn-block btn-lg"style="font-size: 22px;text-align: center;">更新信息</button>
    </form>
</div>

</body>

</html>
