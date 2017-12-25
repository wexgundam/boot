<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>没有权限</title>
    <custom-css>
        <!-- BEGIN ERROR PAGE STYLE -->
        <link href="${staticResourceServerUrl}/assets/metronic_v4.7.5/pages/css/error.min.css" rel="stylesheet" type="text/css" />
        <!-- END ERROR PAGE STYLE -->
    </custom-css>
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
                <span>没有权限</span>
            </li>
        </ul>
    </div>
    <!-- END PAGE BAR -->

    <!-- BEGIN PAGE TITLE-->
    <h1 class="page-title"> 没有权限
        <small>用户没有权限访问</small>
    </h1>
    <!-- END PAGE TITLE-->
    <!-- END PAGE HEADER-->
    <div class="row">
        <div class="col-md-12 page-404">
            <div class="alert alert-danger">
                <h3>
                    <i class="fa fa-exclamation-circle"></i><strong>没有权限!</strong>
                </h3>
                没有访问权限，请联系管理员！<a href="${dynamicResourceServerUrl}/index.htm">首页</a>
            </div>
        </div>
    </div>
</body>

</html>