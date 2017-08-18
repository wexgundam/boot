<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Test</title>
</head>
<body>
    <!-- BEGIN PAGE HEADER-->

    <!-- BEGIN PAGE BAR -->
    <div class="page-bar">
        <ul class="page-breadcrumb">
            <li>
                <a href="/index.htm">首页</a>
                <i class="fa fa-circle"></i>
            </li>
            <li>
                <span>系统配置</span>
                <i class="fa fa-circle"></i>
            </li>
            <li>
                <span>模块管理</span>
            </li>
        </ul>
        <div class="page-toolbar">
            <div class="btn-group pull-right">
                <button type="button" class="btn green btn-sm btn-outline dropdown-toggle" data-toggle="dropdown">
                    Actions <i class="fa fa-angle-down"></i>
                </button>
                <ul class="dropdown-menu pull-right" role="menu">
                    <li>
                        <a href="#"> <i class="icon-bell"></i> Action</a>
                    </li>
                    <li>
                        <a href="#"> <i class="icon-shield"></i> Another action</a>
                    </li>
                    <li>
                        <a href="#"> <i class="icon-user"></i> Something else here</a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#"> <i class="icon-bag"></i> Separated link</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!-- END PAGE BAR -->

    <!-- BEGIN PAGE TITLE-->
    <h1 class="page-title"> 模块管理
        <small>模块查询、浏览与更新</small>
    </h1>
    <!-- END PAGE TITLE-->

    <!-- END PAGE HEADER-->
    <div class="row">
        <div class="col-xs-12" id="insertDiv">
            <c:if test="${color == null}">
                <div class="color-view bg-white bg-font-white bold uppercase"> white</div>
            </c:if>
            <c:if test="${color != null}">
                <div class="color-view bg-${color} bg-font-${color} bold uppercase"> ${color}</div>
            </c:if>
        </div>
    </div>

    <!-- Begin 模块表格 -->
    <div class="row">
        <div class="col-xs-12">
            <table id="treeTable" class="table table-striped table-bordered table-hover">
                <thead>
                    <tr>
                        <th width=180>模块名称</th>
                        <th width=120>模块代码</th>
                        <th>模块链接</th>
                        <th width=100>链接目标</th>
                        <th width=80>图标</th>
                        <th style="text-align: center;" width=80>排序</th>
                        <th width="241">操作</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${list }" var="resource" varStatus="st">
                        <tr id="${resource.id}" pId="${resource.parentId}">
                            <td>${resource.name}</td>
                            <td>${resource.code}</td>
                            <td style="word-break: break-all;">${resource.url}</td>
                            <td>${resource.target}</td>
                            <td>
                                <div>
                                    <i class="fa ${resource.iconImg}"></i>
                                </div>
                            </td>
                            <td style="text-align: center;">${resource.displayOrder}</td>
                            <td>
                                <%--<c:if test="${critc:isP('SysResourceAdd')}">--%>
                                    <%--<a href="toUpdate.htm?id=${resource.id}&backUrl=${backUrl}"> 修改</i>--%>
                                    <%--</a>--%>
                                <%--</c:if> <c:if test="${critc:isP('SysResourceDelete')}">--%>
                                <%--<a href="javascript:delModule(${resource.id });"> 删除 </a>--%>
                            <%--</c:if>--%>
                                <%--<a href="${dynamicServer }/sys/resource/functionIndex.htm?parentId=${resource.id }">功能设置 </a>--%>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <!-- End 模块表格 -->

    <div class="row">
        <div class="col-xs-12">
            <!-- BEGIN PAGINATION PORTLET-->
            <div id="pagination">
                <ul class="pagination">
                    <li class="disabled">
                        <a href="javascript:;">
                            <i class="fa fa-angle-left"></i>
                        </a>
                    </li>
                    <li>
                        <a href="${dynamicResourceServerUrl}/test.htm?color=blue"> 1 </a>
                    </li>
                    <li>
                        <a href="${dynamicResourceServerUrl}/test.htm?color=red"> 2 </a>
                    </li>
                    <li class="active">
                        <a href="javascript:;"> 3 </a>
                    </li>
                    <li>
                        <a href="javascript:;"> 4 </a>
                    </li>
                    <li>
                        <a href="javascript:;"> 5 </a>
                    </li>
                    <li>
                        <a href="javascript:;"> 6 </a>
                    </li>
                    <li>
                        <a href="javascript:;">
                            <i class="fa fa-angle-right"></i>
                        </a>
                    </li>
                </ul>
            </div>
            <!-- END PAGINATION PORTLET-->
        </div>
    </div>
</body>
</html>
