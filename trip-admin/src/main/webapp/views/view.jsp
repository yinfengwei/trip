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
		.wrapper{width:300px;}
		/*tab*/
		#star{overflow:hidden;}
		#star li{float:left; width:20px; height:20px; margin:2px; display:inline; color:#999; font:bold 18px arial; cursor:pointer}
		#star .act{color:#c00}
		#star .noact{color:#999}

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
		<hr>
		<div class="wrapper">
			<p>打分环节 : </p>
			<p style="font-size: small">tips:等级为“很差”、“差”、“一般”、“好”、“很好”</p>
			<p>您的评分为 : <span id="result"></span>
				<ul id="star">
					<li>★</li>
					<li>★</li>
					<li>★</li>
					<li>★</li>
					<li>★</li>
				</ul>


		</div>
		<hr>
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
<script>
	$(function(){
		var star = document.getElementById("star");
		var star_li = star.getElementsByTagName("li");
		var result = document.getElementById("result");
		var i = 0;
		var j = 0;
		var len = star_li.length;


		for(i = 0; i < len; i++){
			star_li[i].index = i;

			star_li[i].onclick = function(){

				var score = this.index + 1;


				$.ajax({
					type: "POST",
					url: "${ctx}/trip-admin/sight/score",
					data:  {
						"score" : score.toString()
					},
					dataType: "json",
					success: function(data) {
						if (data == '1') {
							alert("评分成功");
							result.innerHTML = score + "分";
							for(j = 0; j <= len; j++){
								if (j <= score - 1) {
									$('#star li').eq(j).addClass("act");
									$('#star li').eq(j).removeClass("noact");
								} else {
									$('#star li').eq(j).removeClass("act");
									$('#star li').eq(j).addClass("noact");

								}
							}
						} else {
							alert("评分失败，请重新评分");
						}

					}
				});

			}


		}
	});
</script>
</html>