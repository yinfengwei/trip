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
		span{

		}
	</style>

</head>
<body>
	<div class="container">

		<img width="100%" src="${sight.imgs}" onerror="javascript:this.src='${ctx}/trip-admin/resource/img/error.jpg'" alt="图片">
		<span>地址 : ${sight.address}</span>
		<br>
		<span>景点类型 : ${sight.type}</span>
		<br>
		<span>景点名字 : ${sight.name}</span>
		<br>
		<c:if test="${not empty sight.playTime}">
			<span>建议游玩时间 : ${sight.playTime}</span>
			<br>
		</c:if>
		<c:if test="${not empty sight.phone} ">
			<span> 电话号码: ${sight.phone}</span>
			<br>
		</c:if>
		<c:if test="${not empty sight.website}">
			<span>官网 : ${sight.website}</span>
			<br>
		</c:if>
		<br>
		<c:if test="${not empty sight.sum}">
			<span>评论人数 :${sight.sum}</span>
			<br>
		</c:if>

		<c:if test="${not empty sight.score}">
			<span>携程得分 :${sight.score}</span>
			<br>
		</c:if>

		<c:if test="${sight.rank != 0}">
			<span>景点排行 :${sight.rank}</span>
			<br>
		</c:if>

		<c:if test="${not empty sight.openTime}">
			<span>开放时间 :${sight.openTime}</span>
			<br>
		</c:if>

		<c:if test="${not empty sight.ticket}">
			<span>门票 :${sight.ticket}</span>
			<br>
		</c:if>

		<c:if test="${not empty sight.introduce}">
			<span>简介:${sight.introduce}</span>
			<br>
		</c:if>

		<c:if test="${not empty sight.tips}">
			<span>tips :${sight.tips}</span>
			<br>
		</c:if>



	</div>

</div>

</body>
</html>