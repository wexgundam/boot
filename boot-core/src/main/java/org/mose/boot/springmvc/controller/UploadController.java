package org.mose.boot.springmvc.controller;

import org.mose.boot.springmvc.service.ResourceService;
import org.mose.boot.util.date.DateUtil;
import org.mose.boot.util.json.JsonUtil;
import org.mose.boot.util.web.WebUtil;
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
import java.util.Iterator;

@Controller
public class UploadController {
    @Autowired
    private ResourceService resourceService;

    /**
     * what:    单张图片上传主页
     * when:    请求单张图片上传主页时
     *
     * @return uploadImg
     *
     * @author 杨超凡 created on 2017年11月2日
     */
    @GetMapping("/uploadImage")
    private String uploadImage() {
        return "/uploadImage";
    }

    /**
     * what:    上传文件接收方法
     * when:    前台上传文件时
     * how:     传入request response即可
     * warning: 存储默认路径为图片上传路径
     *
     * @throws IllegalStateException IllegalStateException
     * @throws IOException           IOException
     * @author 杨超凡 created on 2017年11月2日
     */
    @PostMapping("/uploadImage")
    public void uploadImage(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false) String context) throws IllegalStateException, IOException {
        long startTime = System.currentTimeMillis();
        String rootPath = resourceService.getImageUploadPath();
        storage(request, context == null ? rootPath : rootPath + context);
        long endTime = System.currentTimeMillis();
        WebUtil.out(response, JsonUtil.createOperaStr(true, "图片上传成功！用时" + String.valueOf(endTime - startTime) + "ms"));
    }

    /**
     * what:    单张图片上传主页
     * when:    请求单张图片上传主页时
     *
     * @return uploadImg
     *
     * @author 杨超凡 created on 2017年11月2日
     */
    @GetMapping("/uploadFile")
    private String uploadFile() {
        return "/uploadFile";
    }

    /**
     * what:    上传文件接收方法
     * when:    前台上传文件时
     * how:     传入request response即可
     * warning: 存储默认路径为图片上传路径
     *
     * @throws IllegalStateException IllegalStateException
     * @throws IOException           IOException
     * @author 杨超凡 created on 2017年11月2日
     */
    @PostMapping("/uploadFile")
    public void uploadFile(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false) String context) throws IllegalStateException, IOException {
        long startTime = System.currentTimeMillis();
        String rootPath = resourceService.getFileUploadPath();
        storage(request, context == null ? rootPath : rootPath + context);
        long endTime = System.currentTimeMillis();
        WebUtil.out(response, JsonUtil.createOperaStr(true, "文件上传成功！用时" + String.valueOf(endTime - startTime) + "ms"));
    }

    private void storage(HttpServletRequest request, String uploadPath) throws IOException {
        // 将当前上下文初始化给 CommonsMultipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        // 检查form中是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(request)) {
            // 将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 获取multiRequest 中所有的文件名
            Iterator<String> iterator = multiRequest.getFileNames();

            while (iterator.hasNext()) {
                // 一次遍历所有文件
                MultipartFile multipartFile = multiRequest.getFile(iterator.next().toString());
                if (multipartFile != null) {
                    File uploadFile = new File(uploadPath);
                    if (!uploadFile.exists()) {
                        uploadFile.mkdirs();
                    }
                    uploadPath += "\\" + DateUtil.getShortSystemTime() + "_" + multipartFile.getOriginalFilename();
                    // 上传
                    multipartFile.transferTo(new File(uploadPath));
                }
            }
        }
    }
}