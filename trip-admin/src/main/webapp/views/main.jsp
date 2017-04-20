

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
<body style="background-color: #E4B89B;height: 100%;width: 100%">


    <jsp:include page="${ctx}/common/error.jsp"></jsp:include>
    <c:if test="${empty userName}">
        <div class="top_tiv_cover">

            <a href="${ctx}/trip-admin/login">
                <i class="iconfont icon-icon"></i>
            </a>
        </div>
    </c:if>
    <div style="margin-top: 40px;margin-left: 10px;margin-right: 10px;">

        <label style="float: left">用户 ：
            <c:if test="${not empty userName}">
                ${userName}
            </c:if>
            <c:if test="${empty userName}">
                访客模式
            </c:if>
        </label>
        <c:if test="${not empty userName}">
            <a href="${ctx}/trip-admin/loginOut" style="float: right">
                注销登录
            </a>
        </c:if>

    </div>
    <div class="container" style="max-width: 340px;min-height:550px;background: url('${ctx}/trip-admin/resource/img/background.png') no-repeat" >

        <div class="content" style="margin-top: 280px;text-align: center">
            <p style="font-size: 18px;color: steelblue;margin: auto"> 欢迎来到休闲出行推荐系统！</p>
            <p style="font-size: 16px;">请选择您所感兴趣的景点类型</p>
            <c:if test="${not empty userName}">
                <a href="${ctx}/trip-admin/sight?type=recommend&sightType=sportSight" style="font-size: 18px;text-align: center;">
                    <img src="${ctx}/trip-admin/resource/img/sportSight.bmp" style="width: 100%">
                </a>
                <hr>
                <a href="${ctx}/trip-admin/sight?type=recommend&sightType=relaxSight" style="font-size: 18px;text-align: center;">
                    <img src="${ctx}/trip-admin/resource/img/relaxSight.bmp" style="width: 100%">
                </a>
                <hr>
                <a href="${ctx}/trip-admin/sight?type=recommend&sightType=humanSight" style="font-size: 18px;text-align: center;">
                    <img src="${ctx}/trip-admin/resource/img/humanSight.bmp" style="width: 100%">
                </a>
                <hr>
                <a href="${ctx}/trip-admin/sight?type=recommend&sightType=natureSight" style="font-size: 18px;text-align: center;">
                    <img src="${ctx}/trip-admin/resource/img/natureSight.bmp" style="width: 100%">
                </a>
                <hr>
                <a href="${ctx}/trip-admin/sight?type=recommend&sightType=studySight" style="font-size: 18px;text-align: center;">
                    <img src="${ctx}/trip-admin/resource/img/studySight.bmp" style="width: 100%">
                </a>
            </c:if>
            <c:if test="${empty userName}">
                <a href="${ctx}/trip-admin/sight?type=location&sightType=sportSight" style="font-size: 18px;text-align: center;">
                    <img src="${ctx}/trip-admin/resource/img/sportSight.bmp" style="width: 100%">
                </a>
                <hr>
                <a href="${ctx}/trip-admin/sight?type=location&sightType=relaxSight" style="font-size: 18px;text-align: center;">
                    <img src="${ctx}/trip-admin/resource/img/relaxSight.bmp" style="width: 100%">
                </a>
                <hr>
                <a href="${ctx}/trip-admin/sight?type=location&sightType=humanSight" style="font-size: 18px;text-align: center;">
                    <img src="${ctx}/trip-admin/resource/img/humanSight.bmp" style="width: 100%">
                </a>
                <hr>
                <a href="${ctx}/trip-admin/sight?type=location&sightType=natureSight" style="font-size: 18px;text-align: center;">
                    <img src="${ctx}/trip-admin/resource/img/natureSight.bmp" style="width: 100%">
                </a>
                <hr>
                <a href="${ctx}/trip-admin/sight?type=location&sightType=studySight" style="font-size: 18px;text-align: center;">
                    <img src="${ctx}/trip-admin/resource/img/studySight.bmp" style="width: 100%">
                </a>
            </c:if>


        </div>

    </div>

</body>
<script>

</script>
</html>
