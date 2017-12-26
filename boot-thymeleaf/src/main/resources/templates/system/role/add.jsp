<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>新建角色</title>
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
                <a href="${dynamicResourceServerUrl}/system/role/index.htm">角色管理</a>
                <i class="fa fa-circle"></i>
            </li>
            <li>
                <span>新建角色</span>
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
    <h1 class="page-title"> 新建角色
        <small>新建角色，设置角色属性、密码等基本信息</small>
    </h1>
    <!-- END PAGE TITLE-->

    <!-- END PAGE HEADER-->

    <div class="row">
        <div class="col-xs-12">
            <form id="scenarioForm" name="scenarioForm" class="form-horizontal" role="form" action="${dynamicResourceServerUrl}/system/role/add.htm" method="post">
                <div class="form-body">
                    <div class="form-group">
                        <label class="col-md-3 control-label">角色名称</label>
                        <div class="col-md-9">
                            <input name="name" type="text" class="form-control input-xlarge" placeholder="输入角色名称">
                            <label id="nameTip"></label>
                            <span class="help-block"></span>
                        </div>
                    </div>
                </div>
                <div class="form-body">
                    <div class="form-group">
                        <label class="col-md-3 control-label">角色描述</label>
                        <div class="col-md-9">
                            <textarea name="description" class="form-control input-inline  input-xlarge " rows="3 " maxlength="50" placeholder="只能输入50个字符"></textarea>
                            <label id="descriptionTip"></label>
                            <span class="help-block"></span>
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
        <script language="JavaScript">
        </script>
    </content-script>
</body>
</html>
