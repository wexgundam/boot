package org.mose.boot.service.metronic.modal;

import java.util.List;

/**
 * Description:侧边菜单项
 *
 * @Author: 靳磊
 * @Date: 2017/8/14:22
 */
public class SidebarItem {
    /**
     * 主键
     */
    private int id;
    /**
     * 名称
     */
    private String name;
    /**
     * 链接地址
     */
    private String url;
    /**
     * 链接目标
     */
    private String urlTarget;
    /**
     * 父模块
     */
    private SidebarItem parent;
    /**
     * icon
     */
    private String icon;
    /**
     * 在父模块中的显示顺序
     */
    private int order = 0;
    /**
     * 包含的子模块
     *
     * @param o
     * @return
     */
    private List<SidebarItem> children;

    @Override
    public String toString() {
        return "SidebarItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", urlTarget='" + urlTarget + '\'' +
                ", parent=" + parent +
                ", icon='" + icon + '\'' +
                ", order=" + order +
                ", children=" + children +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SidebarItem module = (SidebarItem) o;

        return getId() == module.getId();

    }

    @Override
    public int hashCode() {
        return getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public SidebarItem getParent() {
        return parent;
    }

    public void setParent(SidebarItem parent) {
        this.parent = parent;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public List<SidebarItem> getChildren() {
        return children;
    }

    public void setChildren(List<SidebarItem> children) {
        this.children = children;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlTarget() {
        return urlTarget;
    }

    public void setUrlTarget(String urlTarget) {
        this.urlTarget = urlTarget;
    }
}
