<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>操作成功</title>
    <custom-css>
        <!-- BEGIN ERROR PAGE STYLE -->
        <link href="${staticResourceServerUrl}/assets/metronic_v4.7.5/pages/css/error.min.css" rel="stylesheet" type="text/css" />
        <!-- END ERROR PAGE STYLE -->
    </custom-css>
</head>

<body>
    <!-- BEGIN PAGE HEADER-->
    <!-- BEGIN PAGE BAR -->z
    <div class="page-bar">
        <ul class="page-breadcrumb">
            <li>
                <a href="${dynamicResourceServerUrl}/index.htm">首页</a>
                <i class="fa fa-circle"></i>
            </li>
            <li>
                <span>操作结果</span>
            </li>
        </ul>
    </div>
    <!-- END PAGE BAR -->

    <!-- BEGIN PAGE TITLE-->
    <h1 class="page-title"> 操作结果
        <small>本次操作结果</small>
    </h1>
    <!-- END PAGE TITLE-->
    <!-- END PAGE HEADER-->
    <div class="row">
        <div class="col-md-12 page-404">
            <div class="alert alert-success">
                <h3>
                    <i class="fa fa-check-circle"></i><strong>操作成功!</strong>
                    <small>${message}</small>
                </h3>
                <a href="${redirectUrl}">如果你的浏览器没有自动跳转，请点击此链接</a>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
        </div>
    </div>

    <custom-script>
        <script type="text/javascript">
            setTimeout(function () {
                location.href = "${redirectUrl}";
            }, 3000);
        </script>
    </custom-script>
</body>

</html>