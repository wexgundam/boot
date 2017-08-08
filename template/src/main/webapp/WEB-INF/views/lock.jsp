<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
    <!--<![endif]-->
    <!-- BEGIN HEAD -->

    <head>
        <meta charset="utf-8"/>
        <title>Metronic Admin Theme #1 | User Lock Screen 2</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport"/>
        <meta content="Preview page of Metronic Admin Theme #1 for " name="description"/>
        <meta content="" name="author"/>
        <!-- BEGIN GLOBAL MANDATORY STYLES -->
        <link href="${staticResourceServerUrl}assets/google/google.css" rel="stylesheet" type="text/css"/>
        <link href="${staticResourceServerUrl}assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <link href="${staticResourceServerUrl}assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
        <link href="${staticResourceServerUrl}assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="${staticResourceServerUrl}assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css"/>
        <!-- END GLOBAL MANDATORY STYLES -->
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN THEME GLOBAL STYLES -->
        <link href="${staticResourceServerUrl}assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css"/>
        <link href="${staticResourceServerUrl}assets/global/css/plugins.min.css" rel="stylesheet" type="text/css"/>
        <!-- END THEME GLOBAL STYLES -->
        <!-- BEGIN PAGE LEVEL STYLES -->
        <link href="${staticResourceServerUrl}assets/pages/css/lock-2.min.css" rel="stylesheet" type="text/css"/>
        <!-- END PAGE LEVEL STYLES -->
        <!-- BEGIN THEME LAYOUT STYLES -->
        <!-- END THEME LAYOUT STYLES -->
        <link rel="shortcut icon" href="favicon.ico"/>
    </head>
    <!-- END HEAD -->

    <body class="">
        <div class="page-lock">
            <div class="page-logo">
                <a class="brand" href="index.html">
                    <img src="${staticResourceServerUrl}assets/pages/img/logo-big.png" alt="logo"/> </a>
            </div>
            <div class="page-body">
                <img class="page-lock-img" src="${staticResourceServerUrl}assets/pages/media/profile/profile.jpg" alt="">
                <div class="page-lock-info">
                    <h1><security:authentication property="principal.username"/></h1>
                    <span class="email"> bob@keenthemes.com </span>
                    <span class="locked"> Locked </span>
                    <form class="form-inline" action="index.html">
                        <div class="input-group input-medium">
                            <input type="password" class="form-control" name="userpassword" placeholder="Password">
                            <input type="hidden" class="form-control" name="username" value="<security:authentication property="principal.username"/>" placeholder="Password">
                            <span class="input-group-btn">
                                <button type="submit" class="btn green icn-only">
                                    <i class="m-icon-swapright m-icon-white"></i>
                                </button>
                            </span>
                        </div>
                        <!-- /input-group -->
                        <div class="relogin">
                            <a href="login.htm"> Not <security:authentication property="principal.username"/> ? </a>
                        </div>
                    </form>
                </div>
            </div>
            <div class="page-footer-custom"> 2014 &copy; Metronic. Admin Dashboard Template.</div>
        </div>
        <!--[if lt IE 9]>
        <script src="${staticResourceServerUrl}assets/global/plugins/respond.min.js"></script>
        <script src="${staticResourceServerUrl}assets/global/plugins/excanvas.min.js"></script>
        <script src="${staticResourceServerUrl}assets/global/plugins/ie8.fix.min.js"></script>
        <![endif]-->
        <!-- BEGIN CORE PLUGINS -->
        <script src="${staticResourceServerUrl}assets/global/plugins/jquery.min.js" type="text/javascript"></script>
        <script src="${staticResourceServerUrl}assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="${staticResourceServerUrl}assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>
        <script src="${staticResourceServerUrl}assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
        <script src="${staticResourceServerUrl}assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
        <script src="${staticResourceServerUrl}assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
        <!-- END CORE PLUGINS -->
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <script src="${staticResourceServerUrl}assets/global/plugins/backstretch/jquery.backstretch.min.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN THEME GLOBAL SCRIPTS -->
        <script src="${staticResourceServerUrl}assets/global/scripts/app.min.js" type="text/javascript"></script>
        <!-- END THEME GLOBAL SCRIPTS -->
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
        <script src="${staticResourceServerUrl}assets/pages/scripts/lock-2.min.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL SCRIPTS -->
        <!-- BEGIN THEME LAYOUT SCRIPTS -->
        <!-- END THEME LAYOUT SCRIPTS -->
        <script>
            $(document).ready(function () {
                $('#clickmewow').click(function () {
                    $('#radio1003').attr('checked', 'checked');
                });
            })
        </script>
    </body>

</html>