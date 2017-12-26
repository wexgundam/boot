<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>用户权限</title>
    <content-css>
    </content-css>
</head>
<body>
    <!-- BEGIN PAGE HEADER-->

    <!-- BEGIN PAGE BAR -->
    <div class="page-bar">
        <ul class="page-breadcrumb">
            <li>
                <a href="${dynamicResourceServerUrl}/index.htm">首页</a>
                <i class="fa fa-circle"></i>
            </li>
            <li>
                <span>系统管理</span>
                <i class="fa fa-circle"></i>
            </li>
            <li>
                <a href="${dynamicResourceServerUrl}/system/user/index.htm">用户管理</a>
                <i class="fa fa-circle"></i>
            </li>
            <li>
                <span>用户权限</span>
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
    <h1 class="page-title"> 用户权限
        <small>用户权限浏览</small>
    </h1>
    <!-- END PAGE TITLE-->

    <!-- END PAGE HEADER-->

    <!-- Begin 用户角色编辑面板 -->
    <div class="row">
        <div class="col-xs-12">
            <a class="btn btn-primary" id="btnRefresh" href="">
                <i class="fa fa-refresh"></i> 刷新
            </a>
            <a class="btn btn default" id="btnAdd" href="${dynamicResourceServerUrl }/system/user/index.htm">
                <i class=" fa fa-undo"></i> 返回
            </a>
        </div>
    </div>
    <!-- End 用户角色编辑面板 -->

    <!-- Begin 表格 -->
    <div class="row" style="margin-top: 20px">
        <div class="col-xs-12">
            <table class="table table-striped table-bordered table-hover">
                <thead>
                    <tr>
                        <th width=50>#</th>
                        <th width=200>名称</th>
                        <th>描述</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${authorities}" var="role" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${role.name}</td>
                            <td>${role.description}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <!-- End 表格 -->

    <!-- Begin Javascript -->
    <content-script>
        <script type="text/javascript">
        </script>
    </content-script>
    <!-- End Javascript -->
</body>
</html>
