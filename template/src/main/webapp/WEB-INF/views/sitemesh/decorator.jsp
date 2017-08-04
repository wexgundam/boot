<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<!--[if IE 8]>
<html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]>
<html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->

<head>
    <meta charset="utf-8" />
    <title><sitemesh:write property='title' /> - ${webTitle}</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport" />
    <meta content="Preview page of Metronic Admin Theme #1 for blank page layout" name="description" />
    <meta content="" name="author" />

    <!-- BEGIN STYLES -->
    <%@include file="../common/styles.jspf" %>
    <!-- END STYLES -->

    <sitemesh:write property='head' />
</head>

<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white">
    <div class="page-wrapper">
        <!-- BEGIN HEADER -->
        <%@include file="../common/header.jspf" %>
        <!-- END HEADER -->

        <!-- BEGIN HEADER & CONTENT DIVIDER -->
        <div class="clearfix"></div>
        <!-- END HEADER & CONTENT DIVIDER -->

        <!-- BEGIN CONTAINER -->
        <div class="page-container">
            <!-- BEGIN SIDEBAR -->
            <%@include file="../common/sidebar.jspf" %>
            <!-- END SIDEBAR -->

            <!-- BEGIN CONTENT -->
            <div class="page-content-wrapper">
                <!-- BEGIN CONTENT BODY -->
                <div class="page-content">
                    <sitemesh:write property='body' />
                </div>
                <!-- END CONTENT BODY -->
            </div>
            <!-- END CONTENT -->

            <!-- BEGIN QUICK SIDEBAR -->
            <%@include file="../common/quick_sidebar.jspf" %>
            <!-- END QUICK SIDEBAR -->
        </div>
        <!-- END CONTAINER -->

        <!-- BEGIN FOOTER -->
        <%@include file="../common/footer.jspf" %>
        <!-- END FOOTER -->
    </div>

    <!-- BEGIN QUICK NAV -->
    <%@include file="../common/quick_nav.jspf.jsp" %>
    <!-- END QUICK NAV -->

    <!-- BEGIN STYLES -->
    <%@include file="../common/scripts.jspf" %>
    <!-- END STYLES -->
</body>

</html>
