<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>新建场景</title>
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
                <span>系统配置</span>
                <i class="fa fa-circle"></i>
            </li>
            <li>
                <a href="${dynamicResourceServerUrl}/system/scenario/index.htm">场景管理</a>
                <i class="fa fa-circle"></i>
            </li>
            <li>
                <span>新建场景</span>
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
    <h1 class="page-title"> 新建场景
        <small>新建场景，设置场景的基本属性以及场景间的关系</small>
    </h1>
    <!-- END PAGE TITLE-->

    <!-- END PAGE HEADER-->

    <div class="row">
        <div class="col-xs-12">
            <form class="form-horizontal" role="form" action="${dynamicResourceServerUrl}/system/scenario/add.htm" method="post">
                <div class="form-body">
                    <div class="form-group">
                        <label class="col-md-3 control-label">场景名称</label>
                        <div class="col-md-9">
                            <input name="name" type="text" class="form-control input-xlarge" placeholder="输入场景名称">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">链接地址</label>
                        <div class="col-md-9">
                            <input name="url" type="text" class="form-control input-xlarge" placeholder="输入场景链接地址">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">链接目标</label>
                        <div class="col-md-9">
                            <div class="mt-radio-inline">
                                <label class="mt-radio">
                                    <input type="radio" name="urlTarget" value="_self" checked>
                                    _self
                                    <span></span>
                                </label>
                                <label class="mt-radio">
                                    <input type="radio" name="urlTarget" value="_blank">
                                    _blank
                                    <span></span>
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">图标</label>
                        <div class="col-md-9">
                            <div class="input-group input-xlarge">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="icon-tag"></i>
                                    </span>
                                    <input name="icon" value="icon-tag" type="text" class="form-control" placeholder="icon-tag" disabled>
                                    <span class="input-group-btn input-append">
                                        <button class="btn btn-primary" type="button">
                                            <i class="icon-magnifier"></i> 图标
                                        </button>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">父场景</label>
                        <div class="col-md-9">
                            <div class="input-group input-xlarge">
                                <div class="input-group">
                                    <input name="icon" value="icon-tag" type="text" class="form-control" placeholder="" disabled>
                                    <span class="input-group-btn input-append">
                                        <button class="btn btn-primary" type="button">
                                            <i class="icon-magnifier"></i> 场景
                                        </button>
                                     </span>
                                </div>
                                <span class="help-inline">选择所属的父场景</span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">场景排序</label>
                        <div class="col-md-9">
                            <input name="displayOrder" type="text" class="form-control input-inline input-xlarge" placeholder="在父场景中的排序" value="1">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">描述</label>
                        <div class="col-md-9">
                            <textarea class="form-control input-xlarge" rows="3" name="description"></textarea>
                        </div>
                    </div>
                </div>
                <div class="form-actions">
                    <div class="row">
                        <div class="col-md-offset-3 col-md-9">
                            <button type="submit" class="btn green">保存</button>
                            <button type="button" class="btn default">取消</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
