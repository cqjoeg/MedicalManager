<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2016/5/30
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="bean" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Amaze UI Admin index Examples</title>
    <meta name="description" content="这是一个 index 页面">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="icon" type="image/png" href="/assets/i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="/assets/i/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI" />
    <link rel="stylesheet" href="/assets/css/amazeui.min.css"/>
    <link rel="stylesheet" href="/assets/css/admin.css">
</head>
<body>

<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
    以获得更好的体验！</p>
<![endif]-->

<!-- sidebar start -->
<div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
    <div class="am-offcanvas-bar admin-offcanvas-bar">
        <ul class="am-list admin-sidebar-list">
            <li><a href="/main/admin-index.jsp"><span class="am-icon-home"></span> 首页</a></li>

            <c:set var="users" value="${user}" scope="session"/>
            <c:choose>
                <c:when test="${users.power == 'super'}">
                    <li class="admin-parent">
                        <a class="am-cf" data-am-collapse="{target: '#collapse-nav'}"><span class="am-icon-file"></span> 管理 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
                        <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav">
                            <li><a href="/users/UserMnt.do?currPage=1"class="am-cf"><span class="am-icon-user"></span> 用户管理 <span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>
                            <li><a href="/category/LoadCategory.do?currPage=1"><span class="am-icon-puzzle-piece"></span> 药品类型管理</a></li>
                            <li><a href="/medical/LoadMedical.do?currPage=1"><span class="am-icon-th"></span> 药品管理<span class="am-badge am-badge-secondary am-margin-right am-fr">24</span></a></li>
                            <li><a href="/announce/AnnounceMnt.do?currPage=1"><span class="am-icon-calendar"></span> 公告管理</a></li>
                        </ul>
                    </li>
                </c:when>
                <c:otherwise>
                    <li><a href="#"><span class="am-icon-table"></span> 购买</a></li>
                    <li><a href="/announce/User_AnnounceMnt.do?currPage=1"><span class="am-icon-calendar"></span> 公告</a></li>
                </c:otherwise>
            </c:choose>
            <li><a href="#"><span class="am-icon-pencil-square-o"></span> 统计</a></li>
            <li><a href="/main/MedicalMain_quit.do"><span class="am-icon-sign-out"></span> 注销</a></li>

        </ul>

        <%-- 公告--%>
        <div class="am-panel am-panel-default admin-sidebar-panel">
            <div class="am-panel-bd">
                <p><span class="am-icon-bookmark"></span>公告</p>
                <p></p>
                <p></p>
            </div>
        </div>


    </div>
</div>
<!-- sidebar end -->


<%--<!--[if lt IE 9]>--%>
<%--<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>--%>
<%--<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>--%>
<%--<script src="/assets/js/amazeui.ie8polyfill.min.js"></script>--%>
<%--<![endif]-->--%>

<%--<!--[if (gte IE 9)|!(IE)]><!-->--%>
<%--<script src="/assets/js/jquery.min.js"></script>--%>
<%--<!--<![endif]-->--%>
<%--<script src="/assets/js/amazeui.min.js"></script>--%>
<%--<script src="/assets/js/app.js"></script>--%>
</body>
</html>
