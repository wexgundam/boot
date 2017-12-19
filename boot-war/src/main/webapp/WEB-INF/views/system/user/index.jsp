<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>用户管理</title>
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
                <span>用户管理</span>
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
    <h1 class="page-title"> 用户管理
        <small>用户浏览与编辑</small>
    </h1>
    <!-- END PAGE TITLE-->

    <!-- END PAGE HEADER-->

    <!-- Begin 用户编辑面板 -->
    <div class="row">
        <div class="col-xs-12">
            <a class="btn btn-primary" id="btnRefresh" href="">
                <i class="fa fa-refresh"></i> 刷新
            </a>
            <security:authorize access="hasRole('SystemUserCreate') and fullyAuthenticated">
                <a class="btn btn-success" id="btnAdd" href="${dynamicServer }/system/user/add.htm">
                    <i class=" fa fa-plus"></i> 新建
                </a>
            </security:authorize>
        </div>
    </div>
    <!-- End 用户编辑面板 -->

    <!-- Begin 分页表格 -->
    <div class="row" style="margin-top: 20px">
        <div class="col-xs-12">
            <table class="table table-striped table-bordered table-hover">
                <thead>
                    <tr>
                        <th width=50>#</th>
                        <th width=200>用户名称</th>
                        <th width=100>账户未过期</th>
                        <th width=100>账户位锁定锁</th>
                        <th width=100>认证未过期</th>
                        <th width=100>可用</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${users}" var="user" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${user.username}</td>
                            <td>${user.accountNonExpired}</td>
                            <td>${user.accountNonLocked}</td>
                            <td>${user.credentialsNonExpired}</td>
                            <td>${user.enabled}</td>
                            <td>
                                <security:authorize access="hasRole('SystemUserUpdate') and fullyAuthenticated">
                                    <a href="${dynamicResourceServerUrl}/system/user/update.htm?id=${user.id}"> 修改 </a>
                                </security:authorize>
                                <security:authorize access="hasRole('SystemUserDelete') and fullyAuthenticated">
                                    <a href="javascript:deleteUser(${user.id})"> 删除 </a>
                                </security:authorize>
                                <security:authorize access="hasRole('SystemUserAuthorize') and fullyAuthenticated">
                                    <a href="${dynamicResourceServerUrl}/system/user/role/index.htm?userId=${user.id}">
                                        角色
                                    </a>
                                </security:authorize>
                                <a href="${dynamicResourceServerUrl}/system/user/authority/index.htm?userId=${user.id}">
                                    权限
                                </a>
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
            var deleteUser = function (id) {
                bootbox.confirm("你确定要删除该场景吗？", function (result) {
                    if (result) {
                        window.location = "${dynamicResourceServerUrl}/system/user/delete.htm?id=" + id;
                    }
                })
            };
        </script>
    </content-script>
    <!-- End Javascript -->
</body>
</html>
