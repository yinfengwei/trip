

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
        .type_div{
            position: relative;
            width: 100%;
            margin-bottom: 4px;
        }
        .left_type_div{
            position: relative;
            width: 50%;
            float: left;
            margin-bottom: 4px;
            padding-right: 2px;
        }
        .right_type_div{
            position: relative;
            width: 50%;
            float: right;
            margin-bottom: 4px;
            padding-left: 2px;
        }
        .word_div{
            position: absolute;
            font-size: 30px;
            height:20%;
            width: 100%;
            background:#fff;
            display:block;
            filter:alpha(opacity=40);
            opacity:0.4;
            left:0;
            top:80%;

        }
        .word_div p{
            color: #000;
        }
        .top_tiv_cover{
            position: fixed;
            top: 0;
            background-color: rgba(0,0,0,0.2);
            width: 100%;
            height: 6%;
            line-height: 100%;

        }
        .icon-icon{
            position: absolute;
            top: 50%;
            left: 2%;
            transform: translateY(-50%);
            background-color: rgba(0,0,0,0);
            font-size: 2em;
        }

    </style>
</head>
<body style="background-color: rgb(240,239,244);height: 100%;width: 100%">

    <jsp:include page="${ctx}/common/error.jsp"></jsp:include>
    <div style="margin-top: 20px;margin-left: 10px;margin-right: 10px;">

        <label style="float: left;font-size: 16px;">用户 ：
            <c:if test="${not empty userName}">
                ${userName}
            </c:if>
            <c:if test="${empty userName}">
                访客模式
            </c:if>
        </label>
        <c:if test="${not empty userName}">
            <a href="${ctx}/trip-admin/loginOut" style="float: right;font-size: 16px;">
                注销登录
            </a>
        </c:if>
        <c:if test="${empty userName}">
            <a href="${ctx}/trip-admin/login" style="float: right;font-size: 16px;">
                登录
            </a>
        </c:if>

    </div>


    <div class="container" style="padding-top:30px;max-width: 340px;">

        <div class="content" style="text-align: center">
            <p style="font-size: 18px;color: steelblue;margin: auto"> 欢迎来到休闲出行推荐系统！</p>
            <p style="font-size: 16px;">请选择您所感兴趣的景点类型</p>
        </div>
    </div>
    <div style="margin-left: 10px;margin-right: 10px;">
        <c:if test="${not empty userName}">
            <div class="type_div">
                <a href="${ctx}/trip-admin/sight?type=recommend&sightType=relaxSight" style="font-size: 18px;text-align: center;">
                     <img src="${ctx}/trip-admin/resource/img/relaxSight.jpg" style="width: 100%">
                    <div class="word_div">
                        <p>休闲娱乐</p>
                    </div>
                </a>
            </div>
            <div class="left_type_div">
                <a href="${ctx}/trip-admin/sight?type=recommend&sightType=sportSight" style="font-size: 18px;text-align: center;">
                    <img src="${ctx}/trip-admin/resource/img/sportSight.jpg" style="width: 100%">
                    <div class="word_div">
                        <p>运动健身</p>
                    </div>
                </a>
            </div>
            <div class="right_type_div">
                <a href="${ctx}/trip-admin/sight?type=recommend&sightType=humanSight" style="font-size: 18px;text-align: center;">
                    <img src="${ctx}/trip-admin/resource/img/humanSight.jpg" style="width: 100%">
                    <div class="word_div">
                        <p>人文景观</p>
                    </div>
                </a>
            </div>
            <div class="left_type_div">
                <a href="${ctx}/trip-admin/sight?type=recommend&sightType=natureSight" style="font-size: 18px;text-align: center;">
                <img src="${ctx}/trip-admin/resource/img/natureSight.jpg" style="width: 100%">
                    <div class="word_div">
                        <p>自然风光</p>
                    </div>
                </a>
            </div>
            <div class="right_type_div">
                <a href="${ctx}/trip-admin/sight?type=recommend&sightType=studySight" style="font-size: 18px;text-align: center;">
                <img src="${ctx}/trip-admin/resource/img/studySight.jpg" style="width: 100%">
                    <div class="word_div">
                        <p>创意学府</p>
                    </div>
                </a>
            </div>
        </c:if>
        <c:if test="${empty userName}">
            <div class="type_div">
                <a href="${ctx}/trip-admin/sight?type=location&sightType=relaxSight" style="font-size: 18px;text-align: center;">
                    <img src="${ctx}/trip-admin/resource/img/relaxSight.jpg" style="width: 100%">
                    <div class="word_div">
                        <p>休闲娱乐</p>
                    </div>
                </a>
            </div>
            <div class="left_type_div">
                <a href="${ctx}/trip-admin/sight?type=location&sightType=sportSight" style="font-size: 18px;text-align: center;">
                    <img src="${ctx}/trip-admin/resource/img/sportSight.jpg" style="width: 100%">
                    <div class="word_div">
                      <p>运动健身</p>
                    </div>
                </a>
            </div>
            <div class="right_type_div">
                <a href="${ctx}/trip-admin/sight?type=location&sightType=humanSight" style="font-size: 18px;text-align: center;">
                    <img src="${ctx}/trip-admin/resource/img/humanSight.jpg" style="width: 100%">
                    <div class="word_div">
                        <p>人文景观</p>
                    </div>
                </a>
            </div>
            <div class="left_type_div">
                <a href="${ctx}/trip-admin/sight?type=location&sightType=natureSight" style="font-size: 18px;text-align: center;">
                    <img src="${ctx}/trip-admin/resource/img/natureSight.jpg" style="width: 100%">
                    <div class="word_div">
                        <p>自然风光</p>
                    </div>
                </a>
            </div>
            <div class="right_type_div">
                <a href="${ctx}/trip-admin/sight?type=location&sightType=studySight" style="font-size: 18px;text-align: center;">
                    <img src="${ctx}/trip-admin/resource/img/studySight.jpg" style="width: 100%">
                    <div class="word_div">
                        <p>创意学府</p>
                    </div>
                </a>
            </div>
        </c:if>

    </div>
</body>
<script>

</script>
</html>
