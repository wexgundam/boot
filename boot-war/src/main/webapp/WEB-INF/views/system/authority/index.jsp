<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>权限管理</title>
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
                <span>权限管理</span>
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
    <h1 class="page-title"> 权限管理
        <small>权限浏览与编辑</small>
    </h1>
    <!-- END PAGE TITLE-->

    <!-- END PAGE HEADER-->

    <!-- Begin 权限编辑面板 -->
    <div class="row">
        <div class="col-xs-12">
            <a class="btn btn-primary" id="btnRefresh" href="">
                <i class="fa fa-refresh"></i> 刷新
            </a>
            <security:authorize access="hasRole('ROLE_ADMIN') and fullyAuthenticated">
                <a class="btn btn-success" id="btnAdd" href="${dynamicServer }/system/authority/add.htm">
                    <i class=" fa fa-plus"></i> 新建
                </a>
            </security:authorize>
        </div>
    </div>
    <!-- End 权限编辑面板 -->

    <!-- Begin 分页表格 -->
    <div class="row" style="margin-top: 20px">
        <div class="col-xs-12">
            <table class="table table-striped table-bordered table-hover">
                <thead>
                    <tr>
                        <th width=50>#</th>
                        <th width=200>名称</th>
                        <th>描述</th>
                        <th width=200>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${authorities}" var="authority" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${authority.name}</td>
                            <td>${authority.description}</td>
                            <td>
                                <security:authorize access="hasRole('ADMIN') and fullyAuthenticated">
                                    <a href="${dynamicResourceServerUrl}/system/authority/update.htm?id=${authority.id}">
                                        修改 </a>
                                    <a href="javascript:deleteAuthority(${authority.id})"> 删除 </a>
                                </security:authorize>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Begin 分页控件 -->
    ${pagination}
    <!-- End 分页控件 -->

    <!-- End 分页表格 -->

    <!-- Begin Javascript -->
    <content-script>
        <script type="text/javascript">
            // 删除
            var deleteAuthority = function (id) {
                bootbox.confirm("你确定要删除该角色吗？", function (result) {
                    if (result) {
                        window.location = "${dynamicResourceServerUrl}/system/authority/delete.htm?id=" + id;
                    }
                })
            };
        </script>
    </content-script>
    <!-- End Javascript -->
</body>
</html>
