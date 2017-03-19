

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
    <div class="container" style="margin: auto;max-width: 340px;min-height:550px;background: url('${ctx}/trip-admin/resource/img/background.png') no-repeat" >
        <div class="container" style="margin-top: 270px">
            <%--<p class="text-center">您的经纬度信息为 : ${lon},${lat}</p>--%>
            <p style="margin: auto">您所处位置为 : ${addr}</p>

            <br>

            <div>
                <input type="text" style="height: 36px" placeholder="更正位置信息">

                <button type="button" class="btn btn-warning">点击更正</button>
            </div>

            <br>

            <div style="margin: auto">
                 <div>
                     <p style="font-size: 18px;">登录可以进行景点推荐呦
                         <a href="${ctx}/trip-admin/login" style="font-size: 18px;text-align: center;">我要登录</a>
                     </p>
                 </div>
                 <div>
                     <p style="font-size: 18px;">不想登录
                         <a href="${ctx}/trip-admin/sight" style="font-size: 18px;text-align: center;">随便瞧瞧</a>
                     </p>
                 </div>
            </div>
        </div>

    </div>

</body>

</html>
