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
                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">药品类型管理</strong> / <small>MedicalType Management</small></div>
            </div>
            <hr>

            <div class="am-tabs am-margin" data-am-tabs>
                <ul class="am-tabs-nav am-nav am-nav-tabs">
                    <li class="am-active"><a href="#tab1">基本信息</a></li>
                </ul>

                <div class="am-tabs-bd">
                    <div class="am-fade am-in am-active" id="tab1">
                        <form class="am-form" id="add-user" action="/medical/MedicalMnt_edit.do">
                            <div class="am-g am-margin-top">
                                <div class="am-u-sm-4 am-u-md-2 am-text-right">
                                    药品名
                                </div>
                                <div class="am-u-sm-8 am-u-md-4">
                                    <input type="text" class="am-input-sm"  name="tbMedicine.name" value="${sessionScope.medical_edit.name}" readonly="readonly" required/>
                                </div>
                                <div class="am-hide-sm-only am-u-md-6">
                                </div>
                            </div>

                            <div class="am-g am-margin-top">
                                <div class="am-u-sm-4 am-u-md-2 am-text-right">
                                    药厂名
                                </div>
                                <div class="am-u-sm-8 am-u-md-4">
                                    <input type="text" class="am-input-sm"  name="tbMedicine.factoryAdd" value="${sessionScope.medical_edit.factoryAdd}" required/>
                                </div>
                                <div class="am-hide-sm-only am-u-md-6">
                                </div>
                            </div>

                            <div class="am-g am-margin-top">
                                <div class="am-u-sm-4 am-u-md-2 am-text-right">
                                    描述
                                </div>
                                <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
                                    <textarea name="tbMedicine.description" class="am-input-sm">${sessionScope.medical_edit.description}</textarea>
                                    <input name="tbMedicine.id" value="${sessionScope.medical_edit.id}" class="am-hide">
                                </div>
                            </div>



                            <div class="am-g am-margin-top">
                                <div class="am-u-sm-4 am-u-md-2 am-text-right">
                                    价格
                                </div>
                                <div class="am-u-sm-8 am-u-md-4">
                                    <input type="number" class="am-input-sm" name="tbMedicine.price" value="${sessionScope.medical_edit.price}" required/>
                                </div>
                                <div class="am-hide-sm-only am-u-md-6">
                                </div>
                            </div>

                            <div class="am-g am-margin-top">
                                <div class="am-u-sm-4 am-u-md-2 am-text-right">
                                    药品库存
                                </div>
                                <div class="am-u-sm-8 am-u-md-4">
                                    <input type="number" class="am-input-sm" name="tbMedicine.medCount" value="${sessionScope.medical_edit.medCount}" required/>
                                </div>
                                <div class="am-hide-sm-only am-u-md-6">
                                </div>
                            </div>

                            <div class="am-g am-margin-top">
                                <div class="am-u-sm-4 am-u-md-2 am-text-right">
                                    需求
                                </div>
                                <div class="am-u-sm-8 am-u-md-4">
                                    <input type="number" class="am-input-sm" name="tbMedicine.reqCount" value="${sessionScope.medical_edit.reqCount}" required/>
                                </div>
                                <div class="am-hide-sm-only am-u-md-6">
                                </div>
                            </div>

                            <div class="am-g am-margin-top">
                                <div class="am-u-sm-4 am-u-md-2 am-text-right">
                                    类型
                                </div>
                                <div class="am-u-sm-8 am-u-md-4">
                                    <select data-am-selected="{maxHeight: 100,dropUp: 1 }" name="tbMedicine.categoryId">
                                        <c:set var="select_type" value="${sessionScope.medical_edit.categoryId}" scope="session"/>
                                        <c:forEach items="${category_list}" var="list">
                                            <c:choose>
                                                <c:when test="${select_type == list.id}">
                                                    <option value="<c:out value="${list.id}"/>" selected="selected"><c:out value="${list.name}"/></option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="<c:out value="${list.id}"/>"><c:out value="${list.name}"/></option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>

                                    </select>
                                </div>
                                <div class="am-hide-sm-only am-u-md-6">
                                </div>
                            </div>

                            <div class="am-g am-margin-top">
                                <div class="am-u-sm-4 am-u-md-2 am-text-right">
                                </div>
                                <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
                                    <input type="submit" class="am-btn am-btn-primary am-btn-xs"/>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

            </div>




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
</body>
</html>
