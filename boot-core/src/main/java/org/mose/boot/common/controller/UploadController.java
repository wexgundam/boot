package org.mose.boot.common.controller;

import org.mose.boot.common.service.UploadService;
import org.mose.boot.util.json.JsonUtil;
import org.mose.boot.util.web.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class UploadController {
    @Autowired
    private UploadService uploadService;

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
    public void uploadImage(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false) String context) {
        long startTime = System.currentTimeMillis();
        boolean uploaded = uploadService.uploadImage(request, context);
        long endTime = System.currentTimeMillis();
        if (uploaded) {
            WebUtil.out(response, JsonUtil.createOperaStr(true, "图片上传成功！用时" + String.valueOf(endTime - startTime) + "ms"));
        } else {
            WebUtil.out(response, JsonUtil.createOperaStr(false, "图片上传失败！用时" + String.valueOf(endTime - startTime) + "ms"));
        }
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
    public void uploadFile(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false) String context) {
        long startTime = System.currentTimeMillis();
        boolean uploaded = uploadService.uploadFile(request, context);
        long endTime = System.currentTimeMillis();
        if (uploaded) {
            WebUtil.out(response, JsonUtil.createOperaStr(true, "文件上传成功！用时" + String.valueOf(endTime - startTime) + "ms"));
        } else {
            WebUtil.out(response, JsonUtil.createOperaStr(false, "文件上传失败！用时" + String.valueOf(endTime - startTime) + "ms"));
        }
    }
}