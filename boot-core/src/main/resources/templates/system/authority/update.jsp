<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>修改权限</title>
    <content-css>
    </content-css>
</head>
<body>


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
                <a href="${dynamicResourceServerUrl}/system/authority/index.htm">权限管理</a>
                <i class="fa fa-circle"></i>
            </li>
            <li>
                <span>权限场景</span>
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
    <h1 class="page-title"> 修改权限
        <small>修改权限，设置权限属性、密码等基本信息</small>
    </h1>
    <!-- END PAGE TITLE-->

    <!-- END PAGE HEADER-->

    <div class="row">
        <div class="col-xs-12">
            <form id="scenarioForm" name="scenarioForm" class="form-horizontal" role="form" action="${dynamicResourceServerUrl}/system/authority/update.htm" method="post">
                <div class="form-body">
                    <div class="form-group hidden">
                        <label class="col-md-3 control-label">权限ID</label>
                        <div class="col-md-9">
                            <input name="id" type="password" class="form-control input-xlarge" value="${authority.id}">
                            <label id="idTip"></label>
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">权限名称</label>
                        <div class="col-md-9">
                            <input name="name" type="text" class="form-control input-xlarge" placeholder="输入权限名称" value="${authority.name}" readonly style="border: hidden;background: none">
                            <label id="nameTip"></label>
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label">权限描述</label>
                            <div class="col-md-9">
                                <textarea name="description" class="form-control input-inline  input-xlarge " rows="3 " maxlength="50" placeholder="只能输入50个字符">${authority.description }</textarea>
                                <label id="descriptionTip"></label>
                                <span class="help-block"></span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-actions">
                    <div class="row">
                        <div class="col-md-offset-3 col-md-9">
                            <button type="submit" class="btn green">保存</button>
                            <button type="button" class="btn default" onclick="history.back(-1)">取消</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <content-script>
        <script type="text/javascript">
        </script>
    </content-script>
</body>
</html>
