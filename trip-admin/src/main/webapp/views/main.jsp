

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
    <div class="container" style="margin: auto;max-width: 340px;min-height:450px;background: url('${ctx}/trip-admin/resource/img/background.png') no-repeat" >
        <h1>Welcome!</h1>
    </div>

</body>

</html>
