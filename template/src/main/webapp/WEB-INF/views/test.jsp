<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Test</title>
</head>
<body>
    <!-- BEGIN PAGE HEADER-->

    <!-- BEGIN PAGE BAR -->
    <div class="page-bar">
        <ul class="page-breadcrumb">
            <li>
                <a href="index.html">Home</a>
                <i class="fa fa-circle"></i>
            </li>
            <li>
                <a href="#">Blank Page</a>
                <i class="fa fa-circle"></i>
            </li>
            <li>
                <span>Page Layouts</span>
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
    <h1 class="page-title"> Blank Page Layout
        <small>blank page layout</small>
    </h1>
    <!-- END PAGE TITLE-->

    <!-- END PAGE HEADER-->
    <div class="row">
        <div class="col-xs-12" id="insertDiv">
            <c:if test="${color == null}">
                <div class="color-view bg-white bg-font-white bold uppercase"> white</div>
            </c:if>
            <c:if test="${color != null}">
                <div class="color-view bg-${color} bg-font-${color} bold uppercase"> ${color}</div>
            </c:if>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <!-- BEGIN PAGINATION PORTLET-->
            <div id="pagination">
                <ul class="pagination">
                    <li class="disabled">
                        <a href="javascript:;">
                            <i class="fa fa-angle-left"></i>
                        </a>
                    </li>
                    <li>
                        <a href="${dynamicResourceServerUrl}/test.htm?color=blue"> 1 </a>
                    </li>
                    <li>
                        <a href="${dynamicResourceServerUrl}/test.htm?color=red"> 2 </a>
                    </li>
                    <li class="active">
                        <a href="javascript:;"> 3 </a>
                    </li>
                    <li>
                        <a href="javascript:;"> 4 </a>
                    </li>
                    <li>
                        <a href="javascript:;"> 5 </a>
                    </li>
                    <li>
                        <a href="javascript:;"> 6 </a>
                    </li>
                    <li>
                        <a href="javascript:;">
                            <i class="fa fa-angle-right"></i>
                        </a>
                    </li>
                </ul>
            </div>
            <!-- END PAGINATION PORTLET-->
        </div>
    </div>
</body>
</html>
