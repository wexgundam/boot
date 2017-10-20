<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>修改场景</title>
    <content-css>
        <link href="${staticResourceServerUrl}/assets/zTree3.5/css/zTreeStyle/metro.css" rel="stylesheet" type="text/css" />
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
                <span>系统配置</span>
                <i class="fa fa-circle"></i>
            </li>
            <li>
                <a href="${dynamicResourceServerUrl}/system/scenario/index.htm">场景管理</a>
                <i class="fa fa-circle"></i>
            </li>
            <li>
                <span>修改场景</span>
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
    <h1 class="page-title"> 修改场景
        <small>修改场景，设置场景的基本属性以及场景间的关系</small>
    </h1>
    <!-- END PAGE TITLE-->

    <!-- END PAGE HEADER-->

    <div class="row">
        <div class="col-xs-12">
            <form class="form-horizontal" role="form" action="${dynamicResourceServerUrl}/system/scenario/update.htm?id=${scenario.id }" method="post">
                <div class="form-body">
                    <div class="form-group">
                        <label class="col-md-3 control-label">场景名称</label>
                        <div class="col-md-9">
                            <input name="name" type="text" class="form-control input-xlarge" placeholder="输入场景名称" value="${scenario.name}">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">链接地址</label>
                        <div class="col-md-9">
                            <input name="url" type="text" class="form-control input-xlarge" placeholder="输入场景链接地址" value="${scenario.url}">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">链接目标</label>
                        <div class="col-md-9">
                            <div class="mt-radio-inline">
                                <label class="mt-radio">
                                    <input type="radio" name="urlTarget" value="_self">
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
                                        <i class="${scenario.icon}"></i>
                                    </span>
                                    <input name="icon" value="${scenario.icon}" type="text" class="form-control" placeholder="icon-tag" readonly>
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
                                    <input id="parentId" type="hidden" name="parentId" value="${scenario.parent.id}">
                                    <input id="parentName" name="parentName" type="text" class="form-control" placeholder="" disabled value="${scenario.parent.name}">
                                    <span class="input-group-btn input-append">
                                         <a class="btn btn-primary" data-toggle="modal" href="#parentScenarioSelectModal">
                                            <i class="icon-magnifier"></i> 场景
                                         </a>
                                        <div class="modal fade" id="parentScenarioSelectModal" tabindex="-1" role="basic" aria-hidden="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                                        <h4 class="modal-title">选择父场景</h4>
                                                    </div>
                                                    <div class="modal-body">
                                                         <ul id="scenarioZTree" class="ztree"></ul>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-primary" onclick="javascript:parentScenarioSelected();">确认</button>
                                                        <button type="button" class="btn " data-dismiss="modal">取消</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                     </span>
                                </div>
                                <span class="help-inline">选择所属的父场景</span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">场景排序</label>
                        <div class="col-md-9">
                            <input name="displayOrder" type="text" class="form-control input-inline input-xlarge" placeholder="在父场景中的排序" value="${scenario.displayOrder}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">描述</label>
                        <div class="col-md-9">
                            <textarea class="form-control input-xlarge" rows="3" name="description" value="${scenario.description}"></textarea>
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
        <script src="${staticResourceServerUrl}/assets/zTree3.5/js/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>

        <script language="JavaScript">
            var zTreeObj;
            // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
            var setting = {
                data: {
                    simpleData: {
                        enable: true,
                        idKey: "id",
                        pIdKey: "pId",
                        rootPId: ""
                    }
                }
            };
            // zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
            var zNodes = ${scenarioZTreeJson};
            $(document).ready(function () {
                zTreeObj = $.fn.zTree.init($("#scenarioZTree"), setting, zNodes);
            });

            function parentScenarioSelected() {
                var zTreeObj = $.fn.zTree.getZTreeObj("scenarioZTree");
                var nodes = zTreeObj.getSelectedNodes();
                if (nodes.length > 0) {
                    $("#parentId").val(nodes[0].id);
                    $("#parentName").val(nodes[0].name);
                    $('#parentScenarioSelectModal').modal('hide');
                }
                else return;

            }

            $(function () {
                $("input[name=urlTarget][value=${scenario.urlTarget}]").attr("checked", true);
            });
        </script>
    </content-script>
</body>
</html>
