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
    <sitemesh:write property='head' />
    <!-- BEGIN CUSTOM CSS -->
    <sitemesh:write property="custom-css" />
    <!-- END CUSTOM CSS -->
</head>

<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white">
    <sitemesh:write property="body" />
    <!-- BEGIN CUSTOM SCRIPT -->
    <sitemesh:write property="custom-script" />
    <!-- END CUSTOM SCRIPTS -->
</body>

</html>
