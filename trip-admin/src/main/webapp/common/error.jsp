<%--
  Created by IntelliJ IDEA.
  User: yinfeng
  Date: 2016/11/3 0003
  Time: 0:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty error}">
    <div class="alert alert-warning">
        <button class="close" data-dismiss="alert">
            &times;
        </button>
        <strong>${error}</strong>
    </div>
</c:if>
<c:if test="${not empty info}">
    <div class="alert alert-info">
        <button class="close" data-dismiss="alert">
            &times;
        </button>
        <strong>${info}</strong>
    </div>
</c:if>







