<%@ page import="com.yin.trip.admin.entity.Sight" %>

<%--
  Created by IntelliJ IDEA.
  User: yinfeng
  Date: 2016/11/3 0003
  Time: 23:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<%--<html>--%>
<%--<head>--%>
    <%--<title>轻松出行</title>--%>

    <%--<jsp:include page="${ctx}/common/head.jsp"></jsp:include>--%>


<%--</head>--%>
<%--<body style="background-color: #E4B89B">--%>

<%--<div class="container" style="margin: auto;max-width: 340px;">--%>
    <%--<jsp:include page="${ctx}/common/error.jsp"></jsp:include>--%>
    <%--<h1>景点列表</h1>--%>

    <%--<c:forEach items="${list}" var="data">--%>
        <%--<p>${data.name}</p>--%>

    <%--</c:forEach>--%>

    <%--<div class="pagging">--%>
        <%--<div class="left">共${userNum}条记录</div>--%>
        <%--<div class="right">--%>
            <%--<c:if test="${currentPage == 1}">--%>
                <%--<span class="disabled"><< 前一页</span>--%>
            <%--</c:if>--%>
            <%--<c:if test="${currentPage != 1}">--%>
                <%--<a href="${ctx}/trip-admin/sight?page=${currentPage-1}"><< 前一页</a>--%>
            <%--</c:if>--%>
            <%--<c:if test="${currentPage == 1}">--%>
                <%--<span class="current">1</span>--%>
            <%--</c:if>--%>
            <%--<c:if test="${currentPage != 1}">--%>
                <%--<a href="${ctx}/trip-admin/sight?page=1">1</a>--%>
            <%--</c:if>--%>
            <%--<%--%>
                <%--int pageTimes = (Integer)request.getAttribute("pageTimes");--%>
                <%--for(int i = 1;i < pageTimes; i++)--%>
                <%--{--%>
                    <%--request.setAttribute("page", i + 1);--%>
            <%--%>--%>
            <%--<c:if test="${currentPage == page}">--%>
                <%--<span class="current"><%=i+1%></span>--%>
            <%--</c:if>--%>
            <%--<c:if test="${currentPage != page}">--%>
                <%--<a href="${ctx}/trip-admin/sight?page=<%=i+1%>"><%=i+1%></a>--%>
            <%--</c:if>--%>
            <%--<%} %>--%>

            <%--<c:if test="${currentPage == pageTimes}">--%>
                <%--<span class="disabled">后一页 >></span>--%>
            <%--</c:if>--%>
            <%--<c:if test="${currentPage != pageTimes}">--%>
                <%--<a href="${ctx}/trip-admin/sight?page=${currentPage+1}">后一页 >></a>--%>
            <%--</c:if>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

<%--</body>--%>
<%--<script>--%>

<%--</script>--%>
<%--</html>--%>
<html>
<head>
    <title>轻松出行</title>

    <jsp:include page="${ctx}/common/head.jsp"></jsp:include>

    <style type="text/css">
        *{
            margin: 0;
            padding: 0;
        }
        body{
            background-color: #EBEBEB;
        }
        .tag_part{
            margin-bottom: 1em;
            border-bottom: 1px #CFCFCF solid;
        }
        .leftWord{
            width: 75%;
            float: left;

        }
        .rightPic{
            width: 25%;
            float: left;
        }
        .footerDir{
            width: 100%;
            clear: both;
            border-top: 1px #CFCFCF solid;
        }
        div{
            padding: 0.6em;
            padding-bottom: 0;
            box-sizing: border-box;
            background-color: white;
            font-size: 0.5em;
        }
        .place{
            font-size: 2em;
        }
        .group-purchase,.ticket{
            display: inline-block;
            font-size: 1em;
            background-color: red;
            color: white;
            margin-left:1em;
            padding:0.28em;
            border-radius: 0.3em;

        }
        .ticket{
            background-color: #CD96CD;
            margin-left:0.5em;
        }
        span{
            font-size: 1.1em;
            margin-left: 0.3em;
        }
        .icon-xingxing:before{
            color: #EEAD0E;
            font-size: 0.5em;
        }
        p{
            margin-bottom: 0.6em;
            font-size: 2em;
        }
        .price{
            color: #EE799F;
        }
        .description .first_des,.description .second_des{
            border: 1px #EEAD0E solid;
            font-size: 0.8em;
            color: #EEAD0E;
            line-height: 0.9em;
            padding: 0 0.3em;
        }
        .description .second_des{
            border: 1px #666666 solid;
            color: #666666;
        }
        .place_pic{
            width: 100%;
        }
        .rightPic{
            padding: 0.6em;
        }
        .rightPic_div{
            width: 100%;
            position: relative;
            padding: 0;
        }
        .position{
            width: 100%;
            height: 30%;
            background-color: rgba(0,0,0,0.8);
            position: absolute;
            bottom: 0;
        }
        .position{
            color: white;
            padding: 0;
            text-align:center;
            line-height: 2em;
        }
        .icon-ic_pin_drop_px:before{
            font-size: 1em;

        }
        .position span{
            font-size: 0.8em;
        }
        .footerDir{
            padding:0;
        }
        .footerDir span{
            width: 24%;
            margin: 0;
            display: inline-block;
            box-sizing: border-box;
            text-align: center;
            font-size: 2.5em;
            line-height: 3em;

        }
        .footerDir .iconfont:before{
            font-size: 1em;
        }
        .footerDir .icon-ic_pin_drop_px:before{
            font-size: 1.2em;
        }
    </style>
</head>
<body>
    <div class="container" style="margin: auto;max-width: 340px;" >
        <div style="margin-top: 10px;margin-left: 10px;margin-right: 10px;height: 15px;">
            <label style="float: left">用户 ：
                <c:if test="${not empty sessionScope.userName}">
                    ${sessionScope.userName}
                </c:if>
                <c:if test="${empty userName}">
                    访客模式
                </c:if>
            </label>

            <a href="${ctx}/trip-admin/login" style="float: right">
                <c:if test="${not empty userName}">
                    注销登录
                </c:if>
            </a>

        </div>
        <hr width="100%">
        <div class="list">

            <c:forEach items="${list}" var="data" varStatus="status">

                    <div class="tag_part">
                        <div class="leftWord">
                            <p><span class="place">${status.index + 1}.${data.name}</span>
                            <span>${data.sum}条评论</span>
                            </p>
                            <p>
                            <span class="xingxing">
                                <c:forEach var="i" begin="1" end="${data.score}" step="1">
                                    <i class="iconfont icon-xingxing"></i>
                                </c:forEach>
                            </span>
                                <c:if test="${not empty data.userScore}">
                                    <span class="price">${data.userScore}分/5分</span>
                                </c:if>
                                <c:if test="${empty data.userScore}">
                                    <span class="price">携程评分：${data.score}分/5分</span>
                                </c:if>
                            </p>

                            <p><span>${data.address}</span></p>
                            <p class="description"><span class="first_des">深圳市景点第${data.rank}位</span><span class="second_des">${data.type}</span></p>
                        </div>
                        <div class="rightPic">
                            <div class="rightPic_div">
                                <img class="place_pic" src="${data.imgs}" onerror="javascript:this.src='${ctx}/trip-admin/resource/img/error.jpg'" alt="图片">
                                <div class="position"><i class="iconfont icon-ic_pin_drop_px"><span>4.4公里</span></i></div>
                            </div>
                        </div>
                        <div class="footerDir">
                            <a href="${ctx}/trip-admin/sight/go?name=${data.name}">
                                <span>
                                    <i class="iconfont icon-daozheli-copy"></i>
                                    去这里
                                </span>
                            </a>
                            <span>
                                <i class="iconfont icon-quzheli"></i>
                                导航
                            </span>
                            <span>
                                <i class="iconfont icon-ic_pin_drop_px"></i>
                                搜周边
                            </span>
                            <a href="${ctx}/trip-admin/sight/view?name=${data.name}">
                                <span>
                                    <i class="iconfont icon-xiangqing"></i>
                                        详情
                                </span>
                            </a>

                        </div>
                    </div>
                </c:forEach>

        </div>


        <%--<c:if test="${currentPage != pageTimes}">--%>
            <%--<div style="text-align:center">--%>
                <%--<a style="font-size: 18px;margin: auto" href="javascript:;" onclick="addSight(${currentPage + 1})"> -- 加载更多 -- </a>--%>
            <%--</div>--%>
        <%--</c:if>--%>
        <%--<c:if test="${currentPage == pageTimes}">--%>
            <%--<div style="text-align:center">--%>
                <%--<span style="font-size: 18px;margin: auto"> -- 没有更多了 -- </span>--%>
            <%--</div>--%>
        <%--</c:if>--%>


        <div class="pagging">
            <%--<div class="left">共${userNum}条记录</div>--%>
            <div class="right">
                <c:if test="${currentPage == 1}">
                    <span class="disabled" style="font-size: 18px;float: left;"><< 前一页</span>
                </c:if>
                <c:if test="${currentPage != 1}">
                    <a href="${ctx}/trip-admin/sight?page=${currentPage-1}" style="font-size: 18px;float: left;"><< 前一页</a>
                </c:if>

                <c:if test="${currentPage == pageTimes}">
                    <span class="disabled" style="font-size: 18px;float: right;">后一页 >></span>
                </c:if>
                <c:if test="${currentPage != pageTimes}">
                    <a href="${ctx}/trip-admin/sight?page=${currentPage+1}" style="font-size: 18px;float: right;">后一页 >></a>
                </c:if>
            </div>
        </div>


    </div>
</body>
<%--<script id="template" type="text/template">--%>

    <%--&lt;%&ndash;<c:forEach items="${item}" var="data" varStatus="status" >&ndash;%&gt;--%>

        <%--<div class="tag_part">--%>
            <%--<div class="leftWord">--%>
                <%--<p><span class="place">${status.index + 1}.${data.name}</span><a class="group-purchase" href="">团</a><a class="ticket" href="">票</a></p>--%>
                <%--<p>--%>
                    <%--<span class="xingxing">--%>
                        <%--<c:forEach var="i" begin="1" end="${data.score}" step="1">--%>
                            <%--<i class="iconfont icon-xingxing"></i>--%>
                        <%--</c:forEach>--%>
                    <%--</span>--%>
                    <%--<span>${data.sum}条评论</span>--%>
                    <%--<span class="price">${data.score}分/5分</span>--%>
                    <%--<span>南山区</span>--%>
                <%--</p>--%>
                <%--<p class="description"><span class="first_des">深圳市景点第${data.rank}位</span><span class="second_des">${data.type}</span></p>--%>
            <%--</div>--%>
            <%--<div class="rightPic">--%>
                <%--<div class="rightPic_div">--%>
                    <%--<img class="place_pic" src="${data.imgs}" onerror="javascript:this.src='${ctx}/trip-admin/resource/img/error.jpg'" alt="图片">--%>
                    <%--<div class="position"><i class="iconfont icon-ic_pin_drop_px"><span>4.4公里</span></i></div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="footerDir">--%>
                <%--<span>--%>
                    <%--<i class="iconfont icon-daozheli-copy"></i>--%>
                    <%--去这里--%>
                <%--</span>--%>
                <%--<span>--%>
                    <%--<i class="iconfont icon-quzheli"></i>--%>
                    <%--导航--%>
                <%--</span>--%>
                <%--<span>--%>
                    <%--<i class="iconfont icon-ic_pin_drop_px"></i>--%>
                    <%--搜周边--%>
                <%--</span>--%>
                <%--<a href="${ctx}/trip-admin/sight/view?name=${data.name}">--%>
                   <%--<span>--%>
                    <%--<i class="iconfont icon-xiangqing"></i>--%>
                    <%--详情--%>
                <%--</span>--%>
                <%--</a>--%>

            <%--</div>--%>
        <%--</div>--%>
    <%--&lt;%&ndash;</c:forEach>&ndash;%&gt;--%>
<%--&lt;%&ndash;</script>&ndash;%&gt;--%>
<%--<script>--%>

    <%--&lt;%&ndash;function addSight(currentPage) {&ndash;%&gt;--%>
        <%--&lt;%&ndash;$.ajax({&ndash;%&gt;--%>
            <%--&lt;%&ndash;type: "POST",&ndash;%&gt;--%>
            <%--&lt;%&ndash;url: "${ctx}/trip-admin/sight/appendSight",&ndash;%&gt;--%>
            <%--&lt;%&ndash;data:  {&ndash;%&gt;--%>
                <%--&lt;%&ndash;"currentPage" : currentPage.toString()&ndash;%&gt;--%>
            <%--&lt;%&ndash;},&ndash;%&gt;--%>
            <%--&lt;%&ndash;dataType: "json",&ndash;%&gt;--%>
            <%--&lt;%&ndash;success: function(data){&ndash;%&gt;--%>
                <%--&lt;%&ndash;alert(data[0].name);&ndash;%&gt;--%>
                <%--&lt;%&ndash;&lt;%&ndash;var source = $('#template').html();&ndash;%&gt;&ndash;%&gt;--%>
          <%--&lt;%&ndash;&lt;%&ndash;//      alert(source);&ndash;%&gt;&ndash;%&gt;--%>

                <%--&lt;%&ndash;&lt;%&ndash;var result = source.replace('${data}',data);&ndash;%&gt;&ndash;%&gt;--%>
                <%--&lt;%&ndash;&lt;%&ndash;alert(result);&ndash;%&gt;&ndash;%&gt;--%>
                <%--&lt;%&ndash;&lt;%&ndash;<c:forEach items=${data} var="data" varStatus="status" >&ndash;%&gt;&ndash;%&gt;--%>
                <%--&lt;%&ndash;var context = {"user": {"name": "tom"}};&ndash;%&gt;--%>
                <%--&lt;%&ndash;&lt;%&ndash;var source = "Hello, ${this.user.name}";&ndash;%&gt;&ndash;%&gt;--%>
<%--&lt;%&ndash;////                var scriptlet = jsp.runtime.JspRuntime.compile(source);&ndash;%&gt;--%>
<%--&lt;%&ndash;////                var result = scriptlet.execute(context);&ndash;%&gt;--%>
<%--&lt;%&ndash;//                logger.debug(result);&ndash;%&gt;--%>
            <%--&lt;%&ndash;}&ndash;%&gt;--%>
        <%--&lt;%&ndash;});&ndash;%&gt;--%>
    <%--&lt;%&ndash;}&ndash;%&gt;--%>

<%--//    var TemplateManager = {templates: {}};--%>
<%--//--%>
<%--//    /**--%>
<%--//     * @param name--%>
<%--//     * @return Template--%>
<%--//     */--%>
<%--//    TemplateManager.getTemplate = function(name){--%>
<%--//        return this.templates[name];--%>
<%--//    };--%>
<%--//--%>
<%--//    /**--%>
<%--//     * @param name--%>
<%--//     * @param template--%>
<%--//     * @return Template--%>
<%--//     */--%>
<%--//    TemplateManager.setTemplate = function(name, template){--%>
<%--//        this.templates[name] = template;--%>
<%--//    };--%>
<%--//--%>
<%--//    /**--%>
<%--//     * @param name--%>
<%--//     * @param context--%>
<%--//     * @return Template--%>
<%--//     */--%>
<%--//    TemplateManager.evaluate = function(name, context){--%>
<%--//        if(name != null)--%>
<%--//        {--%>
<%--//            if(typeof(jQuery) == "undefined")--%>
<%--//            {--%>
<%--//                throw {"name": "ClassNotFoundException", "message": "'jQuery' not found !"};--%>
<%--//            }--%>
<%--//--%>
<%--//            var e = jQuery("script[name=" + name + "]");--%>
<%--//            var template = this.getTemplate(name);--%>
<%--//--%>
<%--//            if(template == null)--%>
<%--//            {--%>
<%--//                template = template = jsp.runtime.JspRuntime.compile(e.get(0).text);--%>
<%--//                this.setTemplate(name, template);--%>
<%--//            }--%>
<%--//--%>
<%--//            if(template != null)--%>
<%--//            {--%>
<%--//                var target = e.attr("update");--%>
<%--//--%>
<%--//                if(target != null)--%>
<%--//                {--%>
<%--//                    var html = template.execute(context);--%>
<%--//                    jQuery(target).html(html);--%>
<%--//                }--%>
<%--//            }--%>
<%--//        }--%>
<%--//    };--%>

<%--</script>--%>
</html>