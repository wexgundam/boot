<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>角色权限管理</title>
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
                <a href="${dynamicResourceServerUrl}/system/role/index.htm">角色管理</a>
                <i class="fa fa-circle"></i>
            </li>
            <li>
                <span>角色权限管理</span>
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
    <h1 class="page-title"> 角色权限管理
        <small>角色权限编辑</small>
    </h1>
    <!-- END PAGE TITLE-->

    <!-- END PAGE HEADER-->

    <!-- Begin 角色编辑面板 -->
    <div class="row">
        <div class="col-xs-12">
            <security:authorize access="hasRole('ROLE_ADMIN') and fullyAuthenticated">
                <form id="roleAuthorityForm" name="roleAuthorityForm" class="form-horizontal" role="form" action="${dynamicResourceServerUrl}/system/role/authority/update.htm" method="post">
                    <div class="form-body hidden">
                        <div class="form-group">
                            <label class="col-md-3 control-label">角色Id</label>
                            <div class="col-md-9">
                                <input name="roleId" type="text" class="form-control input-xlarge" placeholder="输入角色Id" value="${roleId}">
                                <label id="roleIdTip"></label>
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">权限Id数组</label>
                            <div class="col-md-9">
                                <input id="authorityIdArrayString" name="authorityIdArrayString" type="text" class="form-control input-xlarge" placeholder="输入角色权限数组">
                                <label id="authorityIdArrayStringTip"></label>
                                <span class="help-block"></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-actions">
                        <div class="row">
                            <div class="col-md-12">
                                <a class="btn btn-primary" href="">
                                    <i class="fa fa-refresh"></i> 刷新
                                </a>
                                <button type="submit" class="btn green" onclick="update()">
                                    <i class=" fa fa-plus"></i> 保存
                                </button>
                                <a class="btn default" onclick="history.back(-1);">
                                    <i class="fa fa-undo"></i> 返回
                                </a>
                            </div>
                        </div>
                    </div>
                </form>
            </security:authorize>
        </div>
    </div>
    <!-- End 角色编辑面板 -->

    <!-- Begin 分页表格 -->
    <div class="row" style="margin-top: 20px">
        <div class="col-xs-12">
            <table class="table table-striped table-bordered table-hover">
                <thead>
                    <tr>
                        <th width=50>#</th>
                        <th width=50>
                            <label class="mt-checkbox mt-checkbox-outline">
                                <input name="checkAll" type="checkbox" id="all" />
                                <span></span>
                            </label>
                        </th>
                        <th width=200>名称</th>
                        <th>描述</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${authorities}" var="role" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>
                                <label class="mt-checkbox mt-checkbox-outline">
                                    <input name="authority" type="checkbox" value="${role.id}" />
                                    <span></span>
                                </label>
                            </td>
                            <td>${role.name}</td>
                            <td>${role.description}</td>
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
            <c:if test="${! empty roleAuthorities}">
            $(function () {
                <c:forEach items="${roleAuthorities}" var="role">
                $("input[value=${role.id}]").attr("checked", true);
                </c:forEach>
            });
            </c:if>

            // 删除
            var update = function () {
                var authorityIdArrayString = "";
                $("input[name=authority]:checked").each(function (index) {
                    if (index > 0) {
                        authorityIdArrayString += "@";
                    }
                    authorityIdArrayString += $(this).val();
                });
                if (authorityIdArrayString.length > 0) {
                    $("#authorityIdArrayString").attr("value", authorityIdArrayString);
                }
                $("roleAuthorityForm").submit();
            };
        </script>
    </content-script>
    <!-- End Javascript -->
</body>
</html>
