<head>
    <meta charset="utf-8" />
    <title><sitemesh:write property='title' /> - ${webTitle}</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport" />
    <meta content="Preview page of Metronic Admin Theme #1 for blank page layout" name="description" />
    <meta content="" name="author" />

    <!-- BEGIN STYLES -->
    <%@include file="../layout/styles.jspf" %>
    <!-- END STYLES -->

    <sitemesh:write property='head' />
</head>

<body>
    <div class="page-wrapper">
        <!-- BEGIN HEADER -->
        <%@include file="../layout/header.jspf" %>
        <!-- END HEADER -->

        <!-- BEGIN HEADER & CONTENT DIVIDER -->
        <div class="clearfix"></div>
        <!-- END HEADER & CONTENT DIVIDER -->

        <!-- BEGIN CONTAINER -->
        <div class="page-container">
            <%--<!-- BEGIN SIDEBAR -->--%>
            <%--<%@include file="../layout/sidebar.jspf" %>--%>
            <%--<!-- END SIDEBAR -->--%>

            <!-- BEGIN CONTENT -->
            <div class="page-content-wrapper">
                <!-- BEGIN CONTENT BODY -->
                <div class="page-content">
                    <%--<content-css>--%>
                        <%--<sitemesh:write property='content-css' />--%>
                    <%--</content-css>--%>
                    <sitemesh:write property='body' />
                    <%--<content-script>--%>
                        <%--<sitemesh:write property='content-script' />--%>
                    <%--</content-script>--%>
                </div>
                <!-- END CONTENT BODY -->
            </div>
            <!-- END CONTENT -->

            <%--<!-- BEGIN QUICK SIDEBAR -->--%>
            <%--<%@include file="../layout/quick_sidebar.jspf" %>--%>
            <%--<!-- END QUICK SIDEBAR -->--%>
        </div>
        <!-- END CONTAINER -->

        <%--<!-- BEGIN FOOTER -->--%>
        <%--<%@include file="../layout/footer.jspf" %>--%>
        <%--<!-- END FOOTER -->--%>
    </div>

    <%--<!-- BEGIN QUICK NAV -->--%>
    <%--<%@include file="../layout/quick_nav.jspf" %>--%>
    <%--<!-- END QUICK NAV -->--%>

    <!-- BEGIN SCRIPTS -->
    <%@include file="../layout/scripts.jspf" %>
    <!-- END SCRIPTS -->
</body>
