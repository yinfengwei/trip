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
		.wrapper{
			width:100%;
		}
		/*tab*/
		#star{overflow:hidden;}
		#star li{float:left; width:20px; height:20px; margin:2px; display:inline; color:#999; font:bold 18px arial; cursor:pointer}
		#star .act{color:#c00}
		#star .noact{color:#999}

		#name {
			font-size: 18px;
			font-family: "Microsoft YaHei UI";
		}

        *{
            margin: 0;
            padding: 0;
        }
        html,body{
            width: 100%;
            height: 100%;
            background-color:#F0F0F0;
        }
        div{
            background-color: white;
            position: relative;
        }
        #img_div{
            width: 100%;
            height: 30%;
            overflow: hidden;
        }
        #place_img{
            width: 100%;
        }
        #place_div{
            padding: 15px;
            background-color:#3F85FE;
            color: white;
        }
        .first_p{
            font-size: 1.3em;
            margin-bottom: 5px;

        }
        .sec_p{
            font-size: 0.85em;
            margin-bottom: 5px;

        }
        .third_p{
            font-size: 0.9em;

        }
        #way_div{
            padding: 15px 0;
        }
        #way_div span{
            display: inline-block;
            width: 32%;
            text-align: center;
        }
        .icon-chuzuche{
            font-size: 0.8em;
        }
        #otherMas_div{
            margin-top: 7px;
            padding: 17px;
        }
        #otherMas_div p{
            margin-bottom:30px;
        }
        #otherMas_div .climate{
            margin-bottom: 0;
        }
        .icon-dingwei,.icon-dianhua,.icon-shijian, .icon-wangluo
        {
            color:#368AF0;
        }
        .icon-dingwei,.icon-dianhua,.icon-shijian,.icon-wangluo{
            font-size: 1.5em;
            margin-right: 1em;
        }

        .place_add span{
            width: 60%;
        }
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
            font-size: 1.7em;
        }
        .icon-icon:before{
            color: white;

        }



	</style>


</head>
<body>
<div id="img_div">
    <img id="place_img"  src="${sight.imgs}" onerror="javascript:this.src='${ctx}/trip-admin/resource/img/error.jpg'" alt="图片">
</div>
<div id="place_div">
    <p class="first_p">${sight.name}</p>
    <p class="sec_p">
        <c:if test="${sight.userScore != ''}">
            <span class="stars">
                 <c:forEach var="i" begin="1" end="${sight.userScore}" step="1">
                     <span class="stars">★</span>
                 </c:forEach>
            </span>
            <span class="price">${sight.userScore}分/5分</span>
        </c:if>
        <c:if test="${sight.userScore == ''}">
            <span class="xingxing">
                <c:forEach var="i" begin="1" end="${sight.score}" step="1">
                    <span class="stars">★</span>
                </c:forEach>
            </span>
            <span class="price">携程：${sight.score}分/5分</span>

        </c:if>
    </p>
    <p class="third_p">
        <span>${sight.type}</span>
    </p>
	<p class="third_p">
		<c:if test="${sight.rank != 0}">
			<span>携程景点排行 :第${sight.rank}名</span>
		</c:if>
	</p>
</div>
<div id="way_div">
	<a href="${ctx}/trip-admin/sight/go?name=${sight.name}">
		<span class="taxi_time"><i class="iconfont icon-chuzuche"></i></span>
		<span class="bus_time"><i class="iconfont icon-gongjiaoche"></i></span>
		<span class="walk_time"><i class="iconfont icon-buxing"></i></span>
	</a>
</div>
<div id="otherMas_div">
    <p class="place_add">
        <i class="iconfont icon-dingwei"></i>
        <span>${sight.address}</span>
    </p>
	<%--<c:if test="${empty sight.phone} ">--%>
		<p class="place_add">
			<i class="iconfont icon-dianhua"></i>
			<span>${sight.phone}</span>
		</p>
	<%--</c:if>--%>


	<c:if test="${not empty sight.openTime}">
		<p class="dur_time">
			<i class="iconfont icon-shijian"></i>
			<span>${sight.openTime}</span>
		</p>
	</c:if>
	<c:if test="${not empty sight.playTime}">
		<p class="dur_time">
			<i class="iconfont icon-shijian"></i>
			<span>建议游玩时间 : ${sight.playTime}</span>
		</p>
	</c:if>

	<c:if test="${not empty sight.website}">
		<p class="climate">
			<i class="iconfont icon-wangluo"></i>
			<span>${sight.website}</span>
		</p>
	</c:if>
</div>
<div class="top_tiv_cover">
	<a href="${ctx}/trip-admin/sight?type=rank">
		<i class="iconfont icon-icon"></i>
	</a>
</div>

		<div class="panel panel-info wrapper">
			<div class="panel-heading">
				<h3 class="panel-title">景点评分</h3>
			</div>
			<div class="panel-body">
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
		</div>
		<%--<div class="wrapper">--%>
			<%--<p>景点评分 : </p>--%>
			<%--<p style="font-size: small">tips:等级为“很差”、“差”、“一般”、“好”、“很好”</p>--%>
			<%--<p>您的评分为 : <span id="result"></span>--%>
				<%--<ul id="star">--%>
					<%--<li>★</li>--%>
					<%--<li>★</li>--%>
					<%--<li>★</li>--%>
					<%--<li>★</li>--%>
					<%--<li>★</li>--%>
				<%--</ul>--%>
		<%--</div>--%>
		<%--<hr>--%>
		<%--<br>--%>
		<div class="panel panel-success">
			<div class="panel-heading">
				<h3 class="panel-title">景点相关信息</h3>
			</div>
			<div class="panel-body">


				<c:if test="${sight.userSum != 0}">
					<span id="userSum">评论人数 :${sight.userSum}人</span>
					<br>
				</c:if>

				<c:if test="${sight.userScore != 0}">
					<span id="userScore">平均得分 :${sight.userScore}分</span>
					<br>
				</c:if>

				<c:if test="${sight.userSum == 0 and not empty sight.sum} ">
					<span id="sum">携程评论人数 :${sight.sum}人</span>
					<br>
				</c:if>

				<c:if test="${sight.userSum == 0 and not empty sight.score}">
					<span id="score">携程得分 :${sight.score}分</span>
					<br>
				</c:if>

				<c:if test="${sight.rank != 0}">
					<span id="rank">携程景点排行 :第${sight.rank}名</span>
					<br>
				</c:if>

				<c:if test="${not empty sight.openTime}">
					<span id="openTime">开放时间 :${sight.openTime}</span>
					<br>
				</c:if>

				<c:if test="${not empty sight.ticket}">
					<span id="ticket">门票 :${sight.ticket}</span>
					<br>
				</c:if>
			</div>
		</div>

		<c:if test="${not empty sight.introduce}">
			<div class="panel panel-success">
				<div class="panel-heading">
					<h3 class="panel-title">简介</h3>
				</div>
				<div class="panel-body">
						${sight.introduce}
				</div>
			</div>
			<%--<span id="introduce">简介:${sight.introduce}</span>--%>
			<%--<br>--%>
		</c:if>

		<c:if test="${not empty sight.tips}">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">tips</h3>
				</div>
				<div class="panel-body">
						${sight.tips}
				</div>
			</div>
			<%--<span id="tips">tips :${sight.tips}</span>--%>
			<%--<br>--%>
		</c:if>

	</div>
	<input type="text" id="longitude" style="display: none" value="${sight.longitude}">
	<input type="text" id="latitude" style="display: none" value="${sight.latitude}">
</div>

</body>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=caR16D6gFwi9voPC3H32RlXrNShv4Fvn">
	//v2.0版本的引用方式：src="http://api.map.baidu.com/api?v=2.0&ak=caR16D6gFwi9voPC3H32RlXrNShv4Fvn"
	//v1.4版本及以前版本的引用方式：src="http://api.map.baidu.com/api?v=1.4&key=您的密钥&callback=initialize"
</script>
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
//							alert("评分成功");
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

		var latitude = $('#latitude').val(),
				longitude = $('#longitude').val(),
				localLatitude = "<%=session.getAttribute("latitude")%>";
				localLongitude = "<%=session.getAttribute("longitude")%>";
		getTime(latitude,longitude,localLatitude,localLongitude);
	});

	function getTime(latitude,longitude,localLatitude,localLongitude) {

		//百度API经纬度保留6位小数
		latitude = Math.round(parseFloat(latitude) * 1000000)/1000000;
		longitude = Math.round(parseFloat(longitude) * 1000000)/1000000;
		localLatitude = Math.round(parseFloat(localLatitude) * 1000000)/1000000;
		localLongitude = Math.round(parseFloat(localLongitude) * 1000000)/1000000;

		console.info(latitude + "," + longitude);
		console.info(localLatitude + "," + localLongitude);

		$.ajax({
			type: "GET",
			url: "${ctx}/trip-admin/sight/getTime",
			data: {
				"latitude" : latitude,
				"longitude" : longitude,
				"localLatitude" : localLatitude,
				"localLongitude" : localLongitude
			},
			dataType: "json",
			success: function(data) {
//				var json = eval('(' + data + ')');
				console.info(data);


				for(var temp=0; temp < 3; temp++) {
					//获取驾车数据
					var driving = data[temp];
					var json = eval('(' + driving + ')');
					console.info(json);

					if(temp == 0) {
						//如果获取成功
						if(json.status == 0) {
//							console.info(json.result.routes[0].duration/60);
							var time = json.result.routes[0].duration/ 60;

							//如果大于60分钟则按照小时计算
							if(time > 60) {
								time = Math.round(time / 60 * 10)/10;
								$('.taxi_time').html("<i class='iconfont icon-chuzuche'></i>" + time + "小时");
							}else {
								time = Math.round(time);
								$('.taxi_time').html("<i class='iconfont icon-chuzuche'></i>" + time + "分钟");
							}

						}
					}else if(temp == 1) {
						//如果获取成功
						if(json.status == 0) {
//							console.info(json.result.routes[0].duration/60);
							var time = json.result.routes[0].duration/60;

							//如果大于60分钟则按照小时计算
							if(time > 60) {
								time = Math.round(time / 60 * 10)/10;
								$('.walk_time').html("<i class='iconfont icon-buxing'></i>" + time + "小时");
							}else {
								time = Math.round(time);
								$('.walk_time').html("<i class='iconfont icon-buxing'></i>" + time + "分钟");
							}

//							$('.walk_time').html("<i class='iconfont icon-buxing'></i>" + time + "分钟");
						}
					}else{
						//如果获取成功
						if(json.status == 0) {
//							console.info(json.result.routes[0].duration/60);
							var scheme = json.result.routes[0].scheme;
							var time = 0 ;
							for(var j = 0;j < scheme.length; j++) {
								time += scheme[j].duration;
							}
							time = time/ 60;

							//如果大于60分钟则按照小时计算
							if(time > 60) {
								time = Math.round(time / 60 * 10)/10;
								$('.bus_time').html("<i class='iconfont icon-gongjiaoche'></i>" + time + "小时");
							}else {
								time = Math.round(time);
								$('.bus_time').html("<i class='iconfont icon-gongjiaoche'></i>" + time + "分钟");
							}
//							$('.bus_time').html("<i class='iconfont icon-gongjiaoche'></i>" + time + "分钟");
						}
					}

				}

			}
		});
	}
</script>
</html>