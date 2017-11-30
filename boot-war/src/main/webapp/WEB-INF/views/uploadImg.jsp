<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="utf-8" %>
<head>
    <meta charset="UTF-8">
    <title>单图片上传</title>
    <content-css>
        <link href="${dynamicResourceServerUrl}/assets/fileupload/css/bootstrap-fileinput.css" rel="stylesheet">
    </content-css>
</head>
<body>
    <div class="page-bar">
        <ul class="page-breadcrumb">
            <li><i class="fa fa-home"></i> <a
                    href="${dynamicResourceServerUrl}/index.htm">首页 > </a></li>
            <li>代码示例 ></li>
            <li>单张图片上传</li>
        </ul>
    </div>
    <div class="container  col-md-12">
        <div class="portlet-title col-md-12">
            <div class="caption">
                <h4>
                    <i class="fa fa-file-image-o"></i><b>单张图片上传</b>
                </h4>
            </div>
        </div>
        <div class="col-md-4">
            <p>需要引入bootstrap-fileinput.js包。</p>
            <p>绑定按钮监听事件，新建FromData传输文件</p>
            <p>具体代码使用及参数说明请查看页面元素。</p>
        </div>
        <div class="text-center col-md-6">
            <form class="form-group" id="uploadForm" enctype='multipart/form-data'>
                <div>
                    <div class="fileinput fileinput-new" data-provides="fileinput"
                         id="exampleInputUpload">
                        <div class="fileinput-new thumbnail"
                             style="width: 100%; height: 100%; max-height: 850px;">
                            <img id='picImg'
                                 style="width: 100%; height: auto; max-height: 400px;"
                                 src="${staticResourceServerUrl}/assets/fileupload/images/noimage.png"
                                 alt=""/>
                        </div>
                        <div class="fileinput-preview fileinput-exists thumbnail" style="max-height: 400px;"></div>
                        <div>
							<span class="btn btn-default btn-file">
                                <span class="fileinput-new">选择文件</span>
                                <span class="fileinput-exists">换一张</span>
								<input type="file" name="pic1" id="picID" accept="image/gif,image/jpeg,image/x-png"/>
							</span>
                            <a href="javascript:;" class="btn btn-warning fileinput-exists" data-dismiss="fileinput">移除</a>
                            <button type="button" id="uploadSubmit" class="btn btn-primary">提交</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <div class="row">
            <div class="col-md-12">
                <form action="/upload.htm" method="POST" enctype="multipart/form-data">
                    <input type="file" name="file"/>
                    <input type="submit" value="Upload"/>
                </form>
            </div>
        </div>
    </div>
</body>
<content-script>
    <script src="${staticResourceServerUrl}/assets/fileupload/js/bootstrap-fileinput.js" type="text/javascript"></script>
    <script type="text/javascript">
        function uploadComplete(data) {
            /* 服务器端返回响应时候触发event事件*/
        }
        $(function () {
            //比较简洁，细节可自行完善
            $('#uploadSubmit').click(function () {
                var fd = new FormData();
                var file = document.getElementById('picID').files[0];
                console.log(document.getElementById('picID').files[0]);
                if (file == undefined) {
                    bootbox.alert("请选择上传图片！");
                } else {
                    fd.append("files", file);
                    var xhr = new XMLHttpRequest();
                    xhr.addEventListener("load", uploadComplete, false);
                    xhr.open("POST", "upload.htm");
                    xhr.send(fd);
                    bootbox.alert("上传成功");
                }
            })
        });
    </script>
</content-script>