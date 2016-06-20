<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>医药管理系统</title>
  <meta name="description" content="这是一个 table 页面">
  <meta name="keywords" content="table">
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

<div class="am-cf admin-main">
  <!-- sidebar start -->
  <jsp:include page="/main/left.jsp"></jsp:include>
  <!-- sidebar end -->
  <!-- content start -->
  <div class="admin-content">
    <div class="admin-content-body">
      <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">表格</strong> / <small>Table</small></div>
      </div>

      <hr>

      <div class="am-g">
        <div class="am-u-sm-12 am-u-md-6">
          <div class="am-btn-toolbar">
            <div class="am-btn-group am-btn-group-xs">
              <a href="/medical/admin-med-add.jsp"><button type="button" class="am-btn am-btn-default"><span class="am-icon-plus"></span> 新增</button></a>
              <a href="#"><button type="button" class="am-btn am-btn-default"><span class="am-icon-trash-o"></span> 删除</button></a>
            </div>
          </div>
        </div>
        <c:set var="select_type" value="${select_type}" scope="session"/>
        <div class="am-u-sm-12 am-u-md-3">
          <select data-am-selected="{btnSize:'sm'}" onchange="mbar(this)" id="select">
            <c:set var="select_type" value="${select_type}" scope="session"/>
            <c:choose>
              <c:when test="${select_type==0}">
                <option value="0" selected="selected">所有类别</option>
              </c:when>
              <c:otherwise>
                <option value="0">所有类别</option>
              </c:otherwise>
            </c:choose>

            <%--<c:set var="category_list" value="${category_list}" scope="session"/>--%>

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

        <div class="am-u-sm-12 am-u-md-3">
          <div class="am-input-group am-input-group-sm">
            <input type="text" class="am-form-field" id="input-search">
          <span class="am-input-group-btn">
            <button class="am-btn am-btn-default btn-search" type="button" onclick="search()">搜索</button>
          </span>
          </div>
        </div>

      </div>

      <div class="am-g am-padding">
        <div class="am-u-sm-12">
          <table class="am-table am-table-striped am-table-hover table-main">
            <thead>
            <tr>
              <th class="table-check"><input type="checkbox"/></th>
              <th class="table-id">ID</th>
              <th class="table-title">药品名</th>
              <th class="table-medical-factory am-hide-sm-only">生产药厂</th>
              <th class="table-price am-hide-sm-only">价格</th>
              <th class="table-set">操作</th>
            </tr>
            </thead>
            <tbody>
            <s:iterator id="medical_list" value="#session.medical_list">
              <tr>
                <td><input type="checkbox" /></td>
                <td><s:property value="#medical_list.id"/> </td>
                <td><s:property value="#medical_list.name"/> </td>
                <td class="am-hide-sm-only"><s:property value="#medical_list.factoryAdd"/></td>
                <td class="am-hide-sm-only"><s:property value="#medical_list.price"/></td>
                <td>
                  <div class="am-btn-toolbar">
                    <div class="am-btn-group am-btn-group-xs">
                      <a href="/medical/MedicalMnt_toedit.do?id=<s:property value="#medical_list.id"/>"><button class="am-btn am-btn-default am-btn-xs am-text-secondary">
                        <span class="am-icon-pencil-square-o"/> 编辑
                      </button></a>
                      <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"
                      onclick="isDel_m(<s:property value="#medical_list.id"/>)">
                        <span class="am-icon-trash-o"/> 删除
                      </button>
                    </div>
                  </div>
                </td>
              </tr>
            </s:iterator>
            </tbody>
          </table>
          ${medical_pagingBar}
          <%--<div class="am-cf">--%>
          <%--<div class="am-fl">--%>
          <%--&lt;%&ndash;分页&ndash;%&gt;--%>
          <%----%>
          <%--</div>--%>
          <%--</div>--%>
          <hr />
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
