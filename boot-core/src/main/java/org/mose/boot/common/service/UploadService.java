package org.mose.boot.common.service;

import org.mose.boot.util.date.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

@Controller
public class UploadService {
    private Logger logger = LoggerFactory.getLogger("exceptionLogger");
    @Autowired
    private ResourceService resourceService;

    public boolean uploadImage(HttpServletRequest request, String context) {
        String rootPath = resourceService.getFileUploadPath();
        try {
            storage(request, context == null ? rootPath : rootPath + context);
            return true;
        } catch (IOException e) {
            logger.error("Upload Image failed.", e);
            return false;
        }
    }

    public boolean uploadFile(HttpServletRequest request, String context) {
        String rootPath = resourceService.getFileUploadPath();
        try {
            storage(request, context == null ? rootPath : rootPath + context);
            return true;
        } catch (IOException e) {
            logger.error("Upload File failed.", e);
            return false;
        }
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