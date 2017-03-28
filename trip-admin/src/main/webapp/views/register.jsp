<%@ taglib prefix="script" uri="http://java.sun.com/jsp/jstl/core" %>


<%--
  Created by IntelliJ IDEA.
  User: yinfeng
  Date: 2016/11/3 0003
  Time: 23:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>轻松出行</title>

    <jsp:include page="${ctx}/common/head.jsp"></jsp:include>


</head>
<body style="background-color: #E4B89B">
    <jsp:include page="${ctx}/common/error.jsp"></jsp:include>
    <div class="container" style="margin: auto;max-width: 340px;background: url('${ctx}/trip-admin/resource/img/background.png') no-repeat">

        <form role="form" style="margin-top: 280px;" method="post" action="${ctx}/trip-admin/insertUser">

            <input name="userName" class="form-control" type="text" style="font-size: 18px;height: 40px;"placeholder="用户名"required>
            <br>
            <input name="password" class="form-control" type="password" style="font-size: 18px;height: 40px;"placeholder="密码"required>
            <br>
            <input name="phone" class="form-control" type="text" style="font-size: 18px;height: 40px;"placeholder="手机号码" required>
            <br>
            <div class="form-group">
                <label class="control-label">请选择您的身份：</label>
                <div class="form-group">
                    <select class="form-control selectpicker" name="type">
                        <option value="student" selected>学生</option>
                        <option value="officer">上班族</option>
                        <option value="cityNewer">城市新人</option>
                        <option value="other">其他</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label">请选择您的性别：</label>
                <div class="form-group">
                    <select class="form-control selectpicker" name="sex">
                        <option value="0" selected>男</option>
                        <option value="1">女</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label">请选择您的年龄：</label>
                <div class="form-group">
                    <select class="form-control selectpicker" name="age">
                        <option value="1">18岁及以下</option>
                        <option value="2" selected>19-30岁</option>
                        <option value="3">31-40岁</option>
                        <option value="4">41-50岁</option>
                        <option value="5">50岁及以上</option>
                    </select>
                </div>
            </div>

            <button type="submit" value="提交" class="btn btn-primary btn-block btn-lg"style="font-size: 22px;text-align: center;">注册</button>
        </form>
    </div>

</body>
<script>
    $(function () {
        $('.selectpicker').selectpicker();
    })
</script>
</html>
