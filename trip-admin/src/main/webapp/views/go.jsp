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

	<style type="text/css">
		html{height:100%}
		body{height:100%;margin:0px;padding:0px}
		#container{height:300px;width:100%;}
		#r-result,#r-result table{width:100%;font-size:12px;pointer-events:none; }
		.btn-group { width: 100%; border-top: 1px solid #DDD; border-bottom: 2px solid #DDD;}
		button {width: 32.5%; text-align: center; border: 0; border-radius: 0; background-color: inherit; height: 44px; line-height: 44px; font-size: 15px;}
		 .top_tiv_cover{
			 position: fixed;
			 top: 0;
			 background-color: rgba(0,0,0,0.2);
			 /*opacity:0.5;*/
			 width: 100%;
			 height: 7%;
			 line-height: 100%;

		 }
		.icon-icon{
			position: absolute;
			top: 50%;
			left: 2%;
			transform: translateY(-50%);
			/*opacity:0.5;*/
			background-color: rgba(0,0,0,0);
			font-size: 2em;
		}

	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=caR16D6gFwi9voPC3H32RlXrNShv4Fvn">
		//v2.0版本的引用方式：src="http://api.map.baidu.com/api?v=2.0&ak=caR16D6gFwi9voPC3H32RlXrNShv4Fvn"
		//v1.4版本及以前版本的引用方式：src="http://api.map.baidu.com/api?v=1.4&key=您的密钥&callback=initialize"
	</script>
</head>

<body>
<div class="top_tiv_cover">
	<c:if test="${not empty userName}">
		<a href="${ctx}/trip-admin/sight?type=recommend">
			<i class="iconfont icon-icon"></i>
		</a>
	</c:if>
	<c:if test="${empty userName}">
		<a href="${ctx}/trip-admin/sight?type=rank">
			<i class="iconfont icon-icon"></i>
		</a>
	</c:if>
</div>
<input type="text" style="display: none" value="${sight.longitude}" id="longitude">
<input type="text" style="display: none" value="${sight.latitude}" id="latitude">


<div id="container" style="margin-top: 40px;"></div>
<div class="btn-group">
	<button id="bus" class="btn-primary">公交与地铁</button>
	<button id="car">驾车</button>
	<button id="walk">步行路线</button>
</div>
<div id="r-result"></div>
<script type="text/javascript">

	<%--var map = new BMap.Map("container");          // 创建地图实例--%>
	var lon1 ="<%=session.getAttribute("longitude")%>";
	var lat1 = "<%=session.getAttribute("latitude")%>";

	var lon2 = $('#longitude').val();
	var lat2 = $('#latitude').val();

	<%--console.info(lon + "," + lat);--%>

	//	var point = new BMap.Point(lon, lat);  // 创建点坐标
	//	map.centerAndZoom(point, 14);                 // 初始化地图，设置中心点坐标和地图级别
	//	var marker = new BMap.Marker(point);        // 创建标注
	//	map.addOverlay(marker);                     // 将标注添加到地图中

	// 百度地图API功能
	var p1 = new BMap.Point(lon1, lat1);
	var p2 = new BMap.Point(lon2, lat2);

	var map = new BMap.Map("container");

	map.centerAndZoom(p1, 12);



	var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_BOTTOM_LEFT });// 左下角，添加比例尺
	var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件

	map.addControl(top_left_control);
	map.addControl(top_left_navigation);

	var transit = new BMap.TransitRoute(map, {
		renderOptions: {map: map, panel: "r-result"}
	});
	transit.search(p1, p2);


	$('#bus').click(function () {

		$('.btn-group').children().removeClass('btn-primary');
		$('#bus').addClass('btn-primary');

		map.removeControl(top_left_control);
		map.removeControl(top_left_navigation);

		map = new BMap.Map("container");
		map.centerAndZoom(p1, 12);
		map.addControl(top_left_control);
		map.addControl(top_left_navigation);
		var transit = new BMap.TransitRoute(map, {
			renderOptions: {map: map, panel: "r-result"}
		});
		transit.search(p1, p2);
	});

	$('#car').click(function () {

		$('.btn-group').children().removeClass('btn-primary');
		$('#car').addClass('btn-primary');

		map.removeControl(top_left_control);
		map.removeControl(top_left_navigation);

		map = new BMap.Map("container");
		map.centerAndZoom(p1, 12);
		map.addControl(top_left_control);
		map.addControl(top_left_navigation);

		var driving = new BMap.DrivingRoute(map, {renderOptions: {map: map, panel: "r-result", autoViewport: true}});

		driving.search(p1,p2);


	});

	$('#walk').click(function () {

		$('.btn-group').children().removeClass('btn-primary');
		$('#walk').addClass('btn-primary');

		map.removeControl(top_left_control);
		map.removeControl(top_left_navigation);

		map = new BMap.Map("container");
		map.centerAndZoom(p1, 12);
		map.addControl(top_left_control);
		map.addControl(top_left_navigation);
		var walking = new BMap.WalkingRoute(map, {renderOptions: {map: map, panel: "r-result", autoViewport: true}});
		walking.search(p1, p2);
	});






</script>
</body>
</html>