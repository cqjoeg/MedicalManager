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
                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">首页</strong> / <small>一些常用模块</small></div>
            </div>
            <hr>
            <div class="am-g">
                <div class="am-u-sm-12">
                    <table class="am-table am-table-bd am-table-striped admin-content-table">
                        <thead>
                        <tr>
                            <th>ID</th><th>用户账号</th><th>成交订单</th>
                        </tr>
                        </thead>
                        <tbody>
                        <s:iterator id="index_list" value="#session.index_list">
                            <tr>
                                <td><s:property value="#index_list.id"/></td>
                                <td><s:property value="#index_list.username"/></td>
                                <td><span class="am-badge am-badge-success"><s:property value="#index_list.countsell"/></span></td>
                            </tr>
                        </s:iterator>
                        </tbody>
                    </table>
                    ${index_pagingBar}
                </div>
            </div>

            <div class="am-g">
                <div class="am-u-md-6">
                    <div class="am-panel am-panel-default">
                        <div class="am-panel-hd am-cf" data-am-collapse="{target: '#collapse-panel-1'}">统计<span class="am-icon-chevron-down am-fr" ></span></div>
                        <div class="am-panel-bd am-collapse am-in" id="collapse-panel-1">
                            <ul class="am-list admin-content-file">
                                <li>
                                    <strong><span class="am-icon-upload"></span> 近一个月最好卖的药品</strong>
                                    <p>大力止咳糖浆</p>
                                </li>
                                <li>
                                    <strong><span class="am-icon-check"></span> 销售之星</strong>
                                    <p>大力哥</p>
                                </li>
                                <li>
                                    <strong><span class="am-icon-check"></span> 总销量最高的药品</strong>
                                    <p>大力止咳片</p>
                                </li>
                            </ul>
                        </div>
                    </div>

                </div>

                <div class="am-u-md-6">
                    <div class="am-panel am-panel-default">
                        <div class="am-panel-hd am-cf" data-am-collapse="{target: '#collapse-panel-2'}">销售排行榜（前5）<span class="am-icon-chevron-down am-fr" ></span></div>
                        <div id="collapse-panel-2" class="am-in">
                            <table class="am-table am-table-bd am-table-bdrs am-table-striped am-table-hover">
                                <tbody>
                                <tr>
                                    <th>账号</th>
                                    <th>交易产品笔数</th>
                                </tr>
                                <tr>

                                    <td>张1</td>
                                    <td>3,005</td>
                                </tr>
                                <tr>
                                    <td>张2</td>
                                    <td>2,505</td>
                                </tr>
                                <tr>
                                    <td>张3</td>
                                    <td>1,405</td>
                                </tr>
                                <tr>
                                    <td>张4</td>
                                    <td>4,005</td>
                                </tr>
                                <tr>
                                    <td>张5</td>
                                    <td>505</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
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
</body>
</html>
