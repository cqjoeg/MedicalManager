<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2016/5/30
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<jsp:include page="/main/top.jsp"></jsp:include>
<div class="am-cf admin-main" >
    <!-- sidebar start -->
    <jsp:include page="/main/left.jsp"></jsp:include>
    <!-- sidebar end -->
    <%--content start--%>
    <div class="admin-content">
        <div class="admin-content-body">
            <div class="am-cf am-padding">
                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">公告管理</strong> / <small>MedicalType Management</small></div>
            </div>
            <hr>
            <div class="am-g">
                <div class="am-u-sm-12 am-u-md-6">
                    <div class="am-btn-toolbar">
                        <div class="am-btn-group am-btn-group-xs">
                            <a href="/announce/admin-announce-add.jsp"><button type="button" class="am-btn am-btn-default"><span class="am-icon-plus"></span>
                                新增</button></a>
                        </div>
                    </div>
                </div>

                <div class="am-g am-padding">
                    <div class="am-u-sm-12">
                        <table class="am-table am-table-bd am-table-striped admin-content-table">
                            <thead>
                            <tr>
                                <th>ID</th><th>标题</th><th>创建时间</th><th>是否置顶</th><th>管理</th>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator id="announce_list" value="#session.announce_list">
                                <tr><td><s:property value="#announce_list.id"/></td>
                                    <td><a href="/announce/AnnounceMnt_show.do?id=<s:property value="#announce_list.id"/>"><s:property value="#announce_list.announTitle"/></a></td>
                                    <td><s:property value="#announce_list.createTime"/></td>
                                    <td><s:property value="#announce_list.hot"/></td>
                                    <td>
                                        <div class="am-btn-toolbar">
                                            <div class="am-btn-group am-btn-group-xs">
                                                <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only" onclick="isDel_announce(<s:property value="#announce_list.id"/>)"><span class="am-icon-trash-o"></span> 删除</button>
                                                <a href="/announce/AnnounceMnt_hot.do?id=<s:property value="#announce_list.id"/>"><button class="am-btn am-btn-default am-btn-xs am-text-secondary"><span class="am-icon-trash-o"></span> 置顶</button></a>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </s:iterator>
                            </tbody>

                        </table>
                        ${announce_pagingBar}
                    </div>
                </div>


            </div>
            <!-- content end -->

        </div>
    </div>

    <%--content end--%>
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
<script src="/assets/js/myjs.js"></script>
</body>
</html>
