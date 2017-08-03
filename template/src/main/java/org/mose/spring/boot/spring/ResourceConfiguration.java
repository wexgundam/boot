package org.mose.spring.boot.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
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
@ConfigurationPropertiesBinding
public class ResourceConfiguration {
    /**
     * 静态资源服务器地址
     */
    public static final String STATIC_RESOURCE_SERVER_URL = "staticResourceServerUrl";
    @Value("${static.resource.server.url}")
    private String staticResourceServerUrl;

    /**
     * 动态资源服务器地址
     */
    public static final String DYNAMIC_RESOURCE_SERVER_URL = "dynamicResourceServerUrl";
    @Value("${dynamic.resource.server.url}")
    private String dynamicResourceServerUrl;

    /**
     * 图片服务器地址
     */
    public static final String IMAGE_SERVER_URL = "imageServerUrl";
    @Value("${image.server.url}")
    private String imageServerUrl;

    /**
     * 文件上传服务器地址
     */
    public static final String UPLOADED_FILE_SERVER_URL = "uploadedFileServerUrl";
    @Value("${uploaded.file.server.url}")
    private String uploadedFileServerUrl;


    /**
     * 网站名称
     */
    public static final String WEB_TITLE = "webTitle";
    @Value("${web.title}")
    private String webTitle;

    /**
     * 资源版本，应对浏览器缓存
     */
    public static final String RESOURCE_VERSION = "resourceVersion";
    @Value("${resource.version}")
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

}
