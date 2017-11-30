package org.mose.boot.springmvc.controller;

import org.mose.boot.springmvc.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

@Controller
public class FileUploadController {
    @Autowired
    private ResourceService resourceService;

    /**
     * what:    单张图片上传主页
     * when:    请求单张图片上传主页时
     *
     * @return uploadImg
     * @author 杨超凡 created on 2017年11月2日
     */
    @RequestMapping("/uploadImg")
    private String uploadImg() {
        return "/uploadImg";
    }

    /**
     * what:    上传文件接收方法
     * when:    前台上传文件时
     * how:     传入request response即可
     * warning: 存储默认路径为图片上传路径
     *
     * @param request  request
     * @param response response
     * @throws IllegalStateException IllegalStateException
     * @throws IOException           IOException
     * @author 杨超凡 created on 2017年11月2日
     */
    @RequestMapping("/upload")
    public void upload(HttpServletRequest request,
                       HttpServletResponse response) throws IllegalStateException, IOException {

        long startTime = System.currentTimeMillis();
        // 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        // 检查form中是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(request)) {
            // 将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 获取multiRequest 中所有的文件名
            Iterator<String> iter = multiRequest.getFileNames();

            while (iter.hasNext()) {
                // 一次遍历所有文件
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                if (file != null) {
//                    String path = pubConfig.getImageUploadPath() + "\\example\\fileupload\\" + new Date().getTime() + file.getOriginalFilename();
                    String path = "D:\\upload\\abc\\" + new Date().getTime() + file.getOriginalFilename();
                    File f = new File(path);
                    if (!f.exists()) {
                        f.mkdirs();
                    }
                    // 上传
                    file.transferTo(new File(path));
                }
            }
        }
        long endTime = System.currentTimeMillis();
//        WebUtil.out(response, JsonUtil.createOperaStr(false, "文件上传成功！用时" + String.valueOf(endTime - startTime) + "ms"));
    }

    @GetMapping("/fileUpload")
    public String toFileUploadView() {
        return "fileUpload";
    }

    // Handling file upload request
    @PostMapping("/fileUpload")
    public ResponseEntity<Object> fileUpload(@RequestParam("file") MultipartFile file)
            throws IOException {

        // Save file on system
        if (!file.getOriginalFilename().isEmpty()) {
            BufferedOutputStream outputStream = new BufferedOutputStream(
                    new FileOutputStream(
                            new File("D:/Upload", file.getOriginalFilename())));
            outputStream.write(file.getBytes());
            outputStream.flush();
            outputStream.close();
        } else {
            return new ResponseEntity<>("Invalid file.", HttpStatus.BAD_REQUEST);
        }

        String json = "{\"success\":true}";
        return new ResponseEntity<>(json, HttpStatus.OK);
    }
}