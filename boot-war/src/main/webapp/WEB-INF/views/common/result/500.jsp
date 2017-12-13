<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>操作失败</title>
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
                <span>操作失败</span>
            </li>
        </ul>
    </div>
    <!-- END PAGE BAR -->

    <!-- BEGIN PAGE TITLE-->
    <h1 class="page-title">操作失败-500
        <small>服务器内部错误</small>
    </h1>
    <!-- END PAGE TITLE-->
    <!-- END PAGE HEADER-->
    <div class="row">
        <div class="col-md-12 page-500">
            <div class=" number font-red"> 500</div>
            <div class=" details">
                <h3>我的天哪! 非常抱歉，我们内部出错了.</h3>
                <p>
                    我们很快就会修复这个问题，请稍后重试. <br />
                </p>
                <p>
                    <a href="${dynamicResourceServerUrl}/index.html" class="btn red btn-outline"> 首页 </a> <br>
                </p>
            </div>
        </div>
    </div>
</body>

</html>