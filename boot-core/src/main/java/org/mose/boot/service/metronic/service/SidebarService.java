package org.mose.boot.service.metronic.service;

import org.mose.boot.service.metronic.modal.SidebarItem;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Description: 基于Metronic侧边栏服务
 *
 * @Author: 靳磊
 * @Date: 2017/8/21 15:22
 */
@Service
public class SidebarService {
    /**
     * 给定侧边栏集合，生成基于Metronic的侧边栏Html
     *
     * @param sidebarItems
     * @return
     */
    public String creatHtml(List<SidebarItem> sidebarItems) {
        StringBuffer stringBuffer = new StringBuffer();
        Collections.sort(sidebarItems, Comparator.comparingInt(SidebarItem::getOrder));
        for (SidebarItem sidebarItem : sidebarItems) {
            stringBuffer.append(createHeadingHtml(sidebarItem));
        }
        return stringBuffer.substring(0);
    }

    /**
     * 构建侧边栏中的Heading控件
     *
     * @param sidebarItem
     * @return
     */
    private String createHeadingHtml(SidebarItem sidebarItem) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<li class=\"heading\">");
        stringBuffer.append("<h3 class=\"uppercase\">" + sidebarItem.getName() + "</h3>");
        stringBuffer.append("</li>");
        if (sidebarItem.getChildren() != null && !sidebarItem.getChildren().isEmpty()) {
            Collections.sort(sidebarItem.getChildren(), Comparator.comparingInt(SidebarItem::getOrder));
            for (SidebarItem child : sidebarItem.getChildren()) {
                stringBuffer.append(createNodeHtml(child));
            }
        }
        return stringBuffer.substring(0);
    }

    /**
     * 构建侧边栏菜单项
     *
     * @param sidebarItem
     * @return
     */
    private String createNodeHtml(SidebarItem sidebarItem) {
        if (sidebarItem.getChildren() == null || sidebarItem.getChildren().isEmpty()) {
            return createLeafNodeHtml(sidebarItem);
        } else {
            return createBranchNodeHtml(sidebarItem);
        }
    }

    /**
     * 构建菜单栏叶子菜单项
     *
     * @param sidebarItem
     * @return
     */
    private String createLeafNodeHtml(SidebarItem sidebarItem) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<li class=\"nav-item\">");
        stringBuffer.append(" <a href=\"" + sidebarItem.getUrl() + "\" target=" + sidebarItem.getUrlTarget() + " class=\"nav-link \">");
        stringBuffer.append("<i class=\"" + sidebarItem.getIcon() + "\"></i>");
        stringBuffer.append("<span class=\"title\">" + sidebarItem.getName() + "</span>");
        stringBuffer.append("</a>");
        stringBuffer.append("</li>");
        return stringBuffer.substring(0);
    }

    /**
     * 构建菜单栏分支菜单项
     *
     * @param sidebarItem
     * @return
     */
    private String createBranchNodeHtml(SidebarItem sidebarItem) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<li class=\"nav-item\">");
        stringBuffer.append("<a href=\"javascript:;\" class=\"nav-link nav-toggle\">");
        stringBuffer.append("<i class=\"" + sidebarItem.getIcon() + "\"></i>");
        stringBuffer.append("<span class=\"title\">" + sidebarItem.getName() + "</span>");
        stringBuffer.append("<span class=\"arrow\"></span>");
        stringBuffer.append("</a>");
        stringBuffer.append("<ul class=\"sub-menu\">");
        for (SidebarItem child : sidebarItem.getChildren()) {
            stringBuffer.append(createNodeHtml(child));
        }
        stringBuffer.append("</ul>");
        stringBuffer.append("</li>");

        return stringBuffer.substring(0);
    }
}
