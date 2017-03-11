

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

<div class="container" style="margin: auto;max-width: 340px;">
    <jsp:include page="${ctx}/common/error.jsp"></jsp:include>
    <form role="form" style="margin-top: 60px;" method="post" action="${ctx}/trip-admin/update">

        <input name="userName" id="userName" class="form-control" type="text" style="font-size: 18px;height: 40px;"placeholder="用户名" required>
        <br>
        <input name="phone" class="form-control" type="text" style="font-size: 18px;height: 40px;"placeholder="手机号码" required>
        <br>
        <button type="button" class="btn btn-primary" onclick="getQuestion()" >获取密保问题</button>
        <label id="question" class="form-control" style="font-size: 18px;"hidden></label>
        <br>
        <input name="answer" class="form-control" type="text" style="font-size: 18px;height: 40px;"placeholder="密保答案" required>
        <br>
        <input name="password" class="form-control" type="password" style="font-size: 18px;height: 40px;"placeholder="新密码" required>
        <br>
        <button type="submit" value="提交" class="btn btn-primary btn-block btn-lg"style="font-size: 22px;text-align: center;">更新信息</button>
    </form>
</div>

</body>
<script>

    function getQuestion() {
        var userName = $('#userName').val();

        $.ajax({
            type : "POST",  //提交方式
            url : "${ctx}/trip-admin/getQuestion",//路径
            data : {
                "userName" : userName
            },//数据，这里使用的是Json格式进行传输
            success : function(data) {//返回数据根据结果进行相应的处理

                if ( data != null && data != "") {
                    alert("获取成功");
                    $('#question').remove("hidden");
                    $('#question').text(data);

                } else {
                    alert("获取失败，请检查用户名");
                }
            }
        });
    }
</script>
</html>
