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
                <a href="index.html">首页</a>
                <i class="fa fa-circle"></i>
            </li>
            <li>
                <span>操作失败</span>
            </li>
        </ul>
    </div>
    <!-- END PAGE BAR -->

    <!-- BEGIN PAGE TITLE-->
    <h1 class="page-title"> 操作失败-404
        <small>请求的页面不存在</small>
    </h1>
    <!-- END PAGE TITLE-->
    <!-- END PAGE HEADER-->
    <div class="row">
        <div class="col-md-12 page-404">
            <div class="number font-green"> 404</div>
            <div class="details">
                <h3>哎呀！ 非常抱歉.</h3>
                <p> 我们找不到您请求的页面！
                    <br />
                    返回 <a href="/index.html"> 首页 </a> 或者 <a href="javascript:;" onclick="window.history.go(-1)">
                        上一页 </a>
                <form action="#" class="hide">
                    <div class="input-group input-medium">
                        <input type="text" class="form-control" placeholder="keyword...">
                        <span class="input-group-btn">
                            <button type="submit" class="btn green">
                                <i class="fa fa-search"></i>
                            </button>
                        </span>
                    </div>
                    <!-- /input-group -->
                </form>
            </div>
        </div>
    </div>
</body>

</html>