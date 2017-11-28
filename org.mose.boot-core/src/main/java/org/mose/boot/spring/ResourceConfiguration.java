package org.mose.boot.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:各种资源服务地址等配置
 *
 * @Author: 靳磊
 * @Date: 2017/8/2:23
 */
@Component
@ConfigurationProperties("custom.resource")
public class ResourceConfiguration {
    /**
     * 静态资源服务器地址
     */
    public static final String STATIC_RESOURCE_SERVER_URL = "staticResourceServerUrl";
    private String staticResourceServerUrl;

    /**
     * 动态资源服务器地址
     */
    public static final String DYNAMIC_RESOURCE_SERVER_URL = "dynamicResourceServerUrl";
    private String dynamicResourceServerUrl;

    /**
     * 图片服务器地址
     */
    public static final String IMAGE_SERVER_URL = "imageServerUrl";
    private String imageServerUrl;

    /**
     * 文件上传服务器地址
     */
    public static final String UPLOADED_FILE_SERVER_URL = "uploadedFileServerUrl";
    private String uploadedFileServerUrl;


    /**
     * 网站名称
     */
    public static final String WEB_TITLE = "webTitle";
    private String webTitle;

    /**
     * 资源版本，应对浏览器缓存
     */
    public static final String RESOURCE_VERSION = "resourceVersion";
    private String resourceVersion;

    /**
     * 将以上配置参数转为map
     *
     * @return
     */
    public Map<? extends String, ?> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put(STATIC_RESOURCE_SERVER_URL, staticResourceServerUrl);
        map.put(DYNAMIC_RESOURCE_SERVER_URL, dynamicResourceServerUrl);
        map.put(IMAGE_SERVER_URL, imageServerUrl);
        map.put(WEB_TITLE, webTitle);
        map.put(RESOURCE_VERSION, resourceVersion);
        return map;
    }

    public String getStaticResourceServerUrl() {
        return staticResourceServerUrl;
    }

    public void setStaticResourceServerUrl(String staticResourceServerUrl) {
        this.staticResourceServerUrl = staticResourceServerUrl;
    }

    public String getDynamicResourceServerUrl() {
        return dynamicResourceServerUrl;
    }

    public void setDynamicResourceServerUrl(String dynamicResourceServerUrl) {
        this.dynamicResourceServerUrl = dynamicResourceServerUrl;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public String getResourceVersion() {
        return resourceVersion;
    }

    public void setResourceVersion(String resourceVersion) {
        this.resourceVersion = resourceVersion;
    }

    public String getUploadedFileServerUrl() {
        return uploadedFileServerUrl;
    }

    public void setUploadedFileServerUrl(String uploadedFileServerUrl) {
        this.uploadedFileServerUrl = uploadedFileServerUrl;
    }

    public String getImageServerUrl() {
        return imageServerUrl;
    }

    public void setImageServerUrl(String imageServerUrl) {
        this.imageServerUrl = imageServerUrl;
    }
}
