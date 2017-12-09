<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>修改用户</title>
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
                <a href="${dynamicResourceServerUrl}/system/user/index.htm">用户管理</a>
                <i class="fa fa-circle"></i>
            </li>
            <li>
                <span>用户场景</span>
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
    <h1 class="page-title"> 修改用户
        <small>修改用户，设置用户属性、密码等基本信息</small>
    </h1>
    <!-- END PAGE TITLE-->

    <!-- END PAGE HEADER-->

    <div class="row">
        <div class="col-xs-12">
            <form id="scenarioForm" name="scenarioForm" class="form-horizontal" role="form" action="${dynamicResourceServerUrl}/system/user/update.htm" method="post">
                <div class="form-body">
                    <div class="form-group hidden">
                        <label class="col-md-3 control-label">用户ID</label>
                        <div class="col-md-9">
                            <input name="id" type="password" class="form-control input-xlarge" value="${user.id}">
                            <label id="idTip"></label>
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">用户名称</label>
                        <div class="col-md-9">
                            <input name="username" type="text" class="form-control input-xlarge" placeholder="输入用户名称" value="${user.username}">
                            <label id="nameTip"></label>
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">账户未失效：</label>
                        <div class="col-md-9 ">
                            <div class="mt-radio-inline">
                                <label class="mt-radio">
                                    <input type="radio" class="input-inline" name="accountNonExpired" value="true" />未失效
                                    <span></span>
                                </label>
                                <label class="mt-radio">
                                    <input type="radio" class="input-inline text-danger" name="accountNonExpired" value="false" /><label class="font-red-thunderbird">已失效</label>
                                    <span></span>
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" style="padding-top: 0px">账户未锁定</label>
                        <div class="col-md-9 ">
                            <div class="mt-radio-inline">
                                <label class="mt-radio">
                                    <input type="radio" class="input-inline" name="accountNonLocked" value="true" />未锁定
                                    <span></span>
                                </label>
                                <label class="mt-radio">
                                    <input type="radio" class="input-inline text-danger" name="accountNonLocked" value="false" /><label class="font-red-thunderbird">已锁定</label>
                                    <span></span>
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" style="padding-top: 0px">认证未失效</label>
                        <div class="col-md-9 ">
                            <div class="mt-radio-inline">
                                <label class="mt-radio">
                                    <input type="radio" class="input-inline" name="credentialsNonExpired" value="true" />未失效
                                    <span></span>
                                </label>
                                <label class="mt-radio">
                                    <input type="radio" class="input-inline text-danger" name="credentialsNonExpired" value="false" /><label class="font-red-thunderbird">已失效</label>
                                    <span></span>
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label" style="padding-top: 0px">可用</label>
                        <div class="col-md-9 ">
                            <div class="mt-radio-inline">
                                <label class="mt-radio">
                                    <input type="radio" class="input-inline" name="enabled" value="true" />可用
                                    <span></span>
                                </label>
                                <label class="mt-radio">
                                    <input type="radio" class="input-inline" name="enabled" value="false" /><label class="font-red-thunderbird">不可用</label>
                                    <span></span>
                                </label>
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
            $(function () {
                $("input[name='accountNonExpired'][value=${user.accountNonExpired}]").attr("checked", true);
                $("input[name='accountNonLocked'][value=${user.accountNonLocked}]").attr("checked", true);
                $("input[name='credentialsNonExpired'][value=${user.credentialsNonExpired}]").attr("checked", true);
                $("input[name='enabled'][value=${user.enabled}]").attr("checked", true);
            });
        </script>
    </content-script>
</body>
</html>
