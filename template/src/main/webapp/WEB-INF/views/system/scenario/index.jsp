<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>场景管理</title>
    <content-css>
        <link href="${staticResourceServerUrl}/assets/treetable/treeTable.min.css?version=${resourceVersion}" rel="stylesheet" type="text/css" />
        <style type="text/css">
            #scenarioTable {
                margin-top: 10px;
                margin-bottom: 10px;
            }
        </style>
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
                <span>系统配置</span>
                <i class="fa fa-circle"></i>
            </li>
            <li>
                <span>场景管理</span>
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
    <h1 class="page-title"> 场景管理
        <small>场景浏览与编辑</small>
    </h1>
    <!-- END PAGE TITLE-->

    <!-- END PAGE HEADER-->

    <!-- Begin 场景编辑面板 -->
    <div class="row">
        <div class="col-xs-12">
            <table id="scenarioTable">
                <tr>
                    <td>
                        <a class="btn btn-primary" id="btnRefresh" href="">
                            <i class="fa fa-refresh"></i> 刷新
                        </a>
                        <security:authorize access="hasRole('ROLE_ADMIN') and fullyAuthenticated">
                            <a class="btn btn-success" id="btnAdd" href="${dynamicServer }/system/scenario/add.htm">
                                <i class=" fa fa-plus"></i> 新建
                            </a>
                        </security:authorize>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <!-- End 场景编辑面板 -->

    <!-- Begin 场景树形表格 -->
    <div class="row">
        <div class="col-xs-12">
            <table id="treeTable" class="table table-striped table-bordered table-hover">
                <thead>
                    <tr>
                        <th width=180>场景名称</th>
                        <th width=120>场景代码</th>
                        <th>场景链接</th>
                        <th width=100>链接目标</th>
                        <th width=80>图标</th>
                        <th style="text-align: center;" width=80>排序</th>
                        <th width="241">操作</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${scenarios}" var="scenario" varStatus="st">
                        <tr id="${scenario.id}" pId="${scenario.parentId}">
                            <td>${scenario.name}</td>
                            <td></td>
                            <!-- style="word-break: break-all;" 在恰当的断字点进行换行 -->
                            <td style="word-break: break-all;">${scenario.url}</td>
                            <td>${scenario.urlTarget}</td>
                            <td>
                                <div>
                                    <i class="fa ${scenario.icon}"></i>
                                </div>
                            </td>
                            <td style="text-align: center;">${scenario.displayOrder}</td>
                            <td>
                                <security:authorize access="hasRole('ADMIN') and fullyAuthenticated">
                                    <a href="toUpdate.htm?id=${resource.id}&backUrl=${backUrl}">
                                        修改</i>
                                    </a>
                                    <a href="javascript:deleteScenario(${scenario.id });"> 删除 </a>
                                    <a href="${dynamicServer }/sys/resource/functionIndex.htm?parentId=${resource.id }">功能设置 </a>
                                </security:authorize>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <!-- End 场景树形表格 -->

    <!-- Begin 分页表格 -->
    <div></div>
    <div class="row">
        <div class="col-xs-12">
            <div class="alert alert-info">分页表格</div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <table class="table table-striped table-bordered table-hover">
                <thead>
                    <tr>
                        <th width=180>场景名称</th>
                        <th width=120>场景代码</th>
                        <th>场景链接</th>
                        <th width=100>链接目标</th>
                        <th width=80>图标</th>
                        <th style="text-align: center;" width=80>排序</th>
                        <th width="241">操作</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${scenarios}" var="scenario" varStatus="st">
                        <tr id="${scenario.id}" pId="${scenario.parentId}">
                            <td>${scenario.name}</td>
                            <td></td>
                            <td style="word-break: break-all;"></td>
                            <td></td>
                            <td>
                                <div>
                                    <i class="fa ${scenario.icon}"></i>
                                </div>
                            </td>
                            <td style="text-align: center;">${scenario.displayOrder}</td>
                            <td>
                                <security:authorize access="hasRole('ADMIN') and fullyAuthenticated">
                                    <a href="toUpdate.htm?id=${resource.id}&backUrl=${backUrl}">
                                        修改</i>
                                    </a>
                                    <a href="javascript:deleteScenario(${resource.id });"> 删除 </a>
                                    <a href="${dynamicServer }/sys/resource/functionIndex.htm?parentId=${resource.id }">功能设置 </a>
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
        <script src="${staticResourceServerUrl}/assets/treetable/jquery.treeTable.min.js" type="text/javascript"></script>

        <script type="text/javascript">
            $(function () {
//                $("#btnSearch").bind('click', searchModule);
//                $("#btnAdd").bind('click', addUser);
//                $("#btnClear").bind('click', clearCache);

                $("#treeTable").treeTable({
                    expandLevel: 3
                });
            })

            <%--// 查询方法--%>
            <%--var searchModule = function () {--%>
            <%--var url = "index.htm?";--%>
            <%--window.location = encodeURI(url);--%>
            <%--}--%>
            // 删除
            var deleteScenario = function (id) {
                bootbox.confirm("你确定要删除该场景吗？", function (result) {
                    if (result) {
                        window.location = "delete.htm?id=" + id;
                    }
                })
            }
            <%--//新增--%>
            <%--var addUser = function (id) {--%>
            <%--window.location = 'toAdd.htm?backUrl=${backUrl }';--%>
            <%--}--%>
            <%--//清空缓存--%>
            <%--var clearCache = function () {--%>
            <%--window.location = 'clearCache.htm?backUrl=${backUrl }';--%>
            <%--}--%>
        </script>
    </content-script>
    <!-- End Javascript -->
</body>
</html>
