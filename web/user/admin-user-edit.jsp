<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2016/5/30
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html class="no-js fixed-layout">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>医药管理系统</title>
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
<%--<c:set var="error" value="${account_exit}" scope="session"/>--%>
<jsp:include page="/main/top.jsp"></jsp:include>
<div class="am-cf admin-main" >
    <!-- sidebar start -->
    <jsp:include page="/main/left.jsp"></jsp:include>
    <!-- sidebar end -->
    <%--content start--%>
    <div class="admin-content">
        <div class="admin-content-body">

            <div class="am-cf am-padding">
                <div class="am-fl am-cf">
                    <strong class="am-text-primary am-text-lg">用户管理</strong> / <small>User Management</small>
                    <strong class="am-text-primary am-text-lg">添加用户</strong> / <small>Add Account</small>
                </div>
            </div>
            <hr>

            <div class="am-tabs am-margin" data-am-tabs>
                <ul class="am-tabs-nav am-nav am-nav-tabs">
                    <li class="am-active"><a href="#tab1">基本信息</a></li>
                </ul>
                <div class="am-tabs-bd">
                    <div class="am-tab-panel am-fade am-in am-active" id="tab1">
                        <form class="am-form" id="add-user">
                            <div class="am-g am-margin-top">
                                <div class="am-u-sm-4 am-u-md-2 am-text-right">
                                    邮箱
                                </div>
                                <div class="am-u-sm-8 am-u-md-4">
                                    <input type="email" class="am-input-sm" id="add-username" name="tbUser.username" required/>
                                </div>
                                <div class="am-hide-sm-only am-u-md-6">
                                    <%--<c:if test="${error != null}" scope="session">--%>
                                        <%--${error}--%>
                                        <%--<c:remove var="account_exit" scope="session"/>--%>
                                    <%--</c:if>--%>
                                </div>
                            </div>

                            <div class="am-g am-margin-top">
                                <div class="am-u-sm-4 am-u-md-2 am-text-right">
                                    密码
                                </div>
                                <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
                                    <input type="password" class="am-input-sm" id="add-password" name="tbUser.password" required/>
                                </div>
                            </div>

                            <div class="am-g am-margin-top">
                                <div class="am-u-sm-4 am-u-md-2 am-text-right">
                                    重新输入密码
                                </div>
                                <div class="am-u-sm-8 am-u-md-4">
                                    <input type="password" class="am-input-sm" id="add-re-password" required/>
                                </div>
                                <div class="am-hide-sm-only am-u-md-6"></div>
                            </div>

                            <div class="am-g am-margin-top">
                                <div class="am-u-sm-4 am-u-md-2 am-text-right">账号状态</div>
                                <div class="am-u-sm-8 am-u-md-10">
                                    <div class="am-btn-group" data-am-button id="wrap">
                                        <label class="am-btn am-btn-default am-btn-xs">
                                            <input type="radio" name="tbUser.state" id="option1" value="0"> 正常
                                        </label>
                                        <label class="am-btn am-btn-default am-btn-xs">
                                            <input type="radio" name="tbUser.state" id="option2" value="1"> 待审核
                                        </label>
                                    </div>

                                </div>
                            </div>

                        </form>

                    </div>
                </div>
            </div>

            <div class="am-margin">
                <button type="button" class="am-btn am-btn-primary am-btn-xs" id="send">创建账号</button>
            </div>

            </div>

        <footer class="admin-content-footer">
            <hr>
            <p class="am-padding-left">© 2014 AllMobilize, Inc. Licensed under MIT license.</p>
        </footer>
        </div>
    <!-- content end -->


    </div>
<a href="#" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}"></a>

<footer>
    <hr>
    <p class="am-padding-left">© 2014 AllMobilize, Inc. Licensed under MIT license.</p>
</footer>

    <!--[if lt IE 9]>
    <script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
    <script src="/assets/js/amazeui.ie8polyfill.min.js"></script>
    <![endif]-->

    <!--[if (gte IE 9)|!(IE)]><!-->
    <script src="/assets/js/jquery.min.js"></script>
    <!--<![endif]-->
    <script src="/assets/js/amazeui.min.js"></script>
    <script src="/assets/js/app.js"></script>
    <script src="/assets/js/scripts.js"></script>

</body>
</html>
