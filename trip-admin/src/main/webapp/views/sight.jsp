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
            font-size: 0.5em;
        }
        .tag_part{
            margin-bottom: 1em;
            border-bottom: 1px #CFCFCF solid;
        }
        .leftWord{
            width: 70%;
            float: left;
            font-size: 1.5em;
        }
        .rightPic{
            width: 30%;
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
            /*font-size: 0.5em;*/
        }
        .place{
            font-size: 1.5em;
        }
        .group-purchase,.ticket{
            display: inline-block;
            font-size: 0.7em;
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
            font-size: 1em;
        }
        p{
            margin-bottom: 0.6em;
            font-size: 1.5em;
        }
        .price{
            color: #EE799F;
            /*font-size: 4em;*/
        }
        .description .first_des,.description .second_des{
            border: 1px #EEAD0E solid;
            font-size: 1.0em;
            color: #EEAD0E;
            line-height: 0.9em;
            padding: 0 0.3em;
        }
        .description .second_des{
            border: 1px #666666 solid;
            color: #666666;
        }
        .place_pic{
            width: 90px;
            height: 90px;
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
            width: 90px;
            height: 25%;
            background-color: rgba(0,0,0,0.8);
            position: absolute;
            bottom: 0;
        }
        .position{
            color: white;
            padding: 0.5em 0 0 0;
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
            width: 48%;
            margin: 0;
            display: inline-block;
            box-sizing: border-box;
            text-align: center;
            font-size: 3.0em;
            line-height: 3em;

        }
        .footerDir .iconfont:before{
            font-size: 1em;
        }
        .footerDir .icon-ic_pin_drop_px:before{
            font-size: 1.2em;
        }
        .btn-group { width: 100%; border-top: 1px solid #DDD; border-bottom: 2px solid #DDD;padding:0}
        button{width: 32.5%; text-align: center; border: 0; border-radius: 0; background-color: inherit; height: 44px; line-height: 44px; font-size: 3em;}
        .top_tiv_cover{
            position: fixed;
            top: 0;
            background-color: rgba(0,0,0,0.2);
            width: 100%;
            height: 7%;
            line-height: 100%;

        }
        .right a{
            width: 23%;
            display: inline-block;
        }
        .right span{
            width: 23%;
            display: inline-block;
        }
        .icon-icon{
            position: absolute;
            top: 50%;
            left: 2%;
            transform: translateY(-50%);
            background-color: rgba(0,0,0,0);
            font-size: 5em;
        }
    </style>
</head>
<body>
<div class="top_tiv_cover">

    <a href="${ctx}/trip-admin/main">
        <i class="iconfont icon-icon"></i>
    </a>
</div>

    <div class="container" style="margin: auto;max-width: 340px;" >
        <div style="margin-top: 20px;"></div>
        <jsp:include page="${ctx}/common/error.jsp"></jsp:include>

        <div style="margin-top: 10px;margin-left: 10px;margin-right: 10px;height: 15px;">
            <label style="float: left;font-size: 3em;">用户 ：
                <c:if test="${not empty sessionScope.userName}">
                    ${sessionScope.userName}
                </c:if>
                <c:if test="${empty userName}">
                    访客模式
                </c:if>
            </label>
            <c:if test="${not empty userName}">
                <a href="${ctx}/trip-admin/loginOut" style="float: right;font-size: 3em;">
                    注销登录
                </a>
            </c:if>
            <c:if test="${empty userName}">
                <a href="${ctx}/trip-admin/login" style="float: right;font-size: 16px;">
                    登录
                </a>
            </c:if>

        </div>

        <%--<hr width="100%">--%>

        <input type="text" value="${type}" id="type" style="display: none">
        <div class="btn-group">
            <a href="${ctx}/trip-admin/sight?type=recommend">
                <button id="recommend">综合排序</button>
            </a>
            <a href="${ctx}/trip-admin/sight?type=location">
                <button id="location">距离从近到远</button>
            </a>
            <a href="${ctx}/trip-admin/sight?type=rank">
                <button id="rank">携程排名</button>
            </a>
        </div>

        <div class="list">

            <c:forEach items="${list}" var="data" varStatus="status">

                    <div class="tag_part">
                        <div class="leftWord">
                            <p><span class="place">${status.index + 1}.${data.name}</span>
                            </p>
                            <p>
                            <c:if test="${data.userScore != ''}">
                                <span class="xingxing">
                                    <c:forEach var="i" begin="1" end="${data.userScore}" step="1">
                                        <i class="iconfont icon-xingxing"></i>
                                    </c:forEach>
                                </span>
                                <span class="price">${data.userScore}分/5分</span>
                            </c:if>
                            <c:if test="${data.userScore == ''}">
                                <span class="xingxing">
                                    <c:forEach var="i" begin="1" end="${data.score}" step="1">
                                        <i class="iconfont icon-xingxing"></i>
                                    </c:forEach>
                                </span>
                                <span class="price">携程：${data.score}分/5分</span>


                            </c:if>
                            </p>

                            <p><span>${data.address}</span></p>
                            <p class="description"><span class="first_des">深圳市景点第${data.rank}位</span><span class="second_des">${data.type}</span></p>
                        </div>
                        <div class="rightPic">
                            <div class="rightPic_div">
                                <img class="place_pic" src="${data.imgs}" onerror="javascript:this.src='${ctx}/trip-admin/resource/img/error.jpg'" alt="图片">
                                <div class="position"><i class="iconfont icon-ic_pin_drop_px"><span>${distanceMap[data.name]}公里</span></i></div>
                            </div>
                        </div>
                        <div class="footerDir">
                            <a href="${ctx}/trip-admin/sight/go?name=${data.name}">
                                <span>
                                    <i class="iconfont icon-daozheli-copy"></i>
                                    去这里
                                </span>
                            </a>

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
                <%--前面两个--%>
                <c:if test="${currentPage == 1}">
                    <span style="font-size: 25px;">${currentPage}</span>
                    <span style="font-size: 25px;"> ...  </span>
                </c:if>
                <c:if test="${currentPage != 1}">

                    <a href="${ctx}/trip-admin/sight?type=${type}&page=1" style="font-size: 25px;"><<1 </a>
                    <c:if test="${currentPage == 2}">
                        <span style="font-size: 25px;"> ...  </span>
                    </c:if>
                    <c:if test="${currentPage != 2}">
                        <a href="${ctx}/trip-admin/sight?type=${type}&page=${currentPage-1}" style="font-size: 25px;"><${currentPage-1}  </a>
                    </c:if>
                </c:if>

                    <%--后面两个--%>
                <c:if test="${currentPage == 1}">
                    <a href="${ctx}/trip-admin/sight?type=${type}&page=${currentPage+1}" style="font-size: 25px;"> ${currentPage+1}>  </a>
                    <a href="${ctx}/trip-admin/sight?type=${type}&page=${pageTimes}" style="font-size: 25px;float: right;">  ${pageTimes}>>  </a>
                </c:if>
                <c:if test="${currentPage != 1}">
                    <c:if test="${currentPage == pageTimes}">
                        <span style="font-size: 25px;"> ...  </span>
                        <span style="font-size: 25px;"> ${pageTimes}  </span>
                    </c:if>
                    <c:if test="${currentPage == pageTimes-1}">
                        <span style="font-size: 25px;"> ...  </span>
                        <a href="${ctx}/trip-admin/sight?type=${type}&page=${pageTimes}" style="font-size: 25px;float: right;">  ${pageTimes}  </a>
                    </c:if>

                    <c:if test="${currentPage < pageTimes - 1}">
                        <a href="${ctx}/trip-admin/sight?type=${type}&page=${currentPage+1}" style="font-size: 25px;"> ${currentPage+1}>  </a>
                        <a href="${ctx}/trip-admin/sight?type=${type}&page=${pageTimes}" style="font-size: 25px;float: right;">  ${pageTimes}>>  </a>
                    </c:if>
                </c:if>
            </div>
        </div>


    </div>
</body>
<script>
    $(function () {
        var type = $('#type').val();

        if(type == "recommend") {
            $('#recommend').addClass("btn-primary");
        } else if (type == "location") {
            $('#location').addClass("btn-primary");
        } else {
            $('#rank').addClass("btn-primary");
        }
    })


</script>
</html>