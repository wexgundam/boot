package org.mose.boot.system.modal;

import java.util.List;

/**
 * what:    场景
 * warning: 每个模块都有访问权限authorityName，只有具备该权限名称所描述的权限的用户才能访问该场景
 * <p>
 * 描述系统的模块、结构与功能布局。大场景可已拆分成小场景，直到最小场景。
 *
 * @Author: 靳磊
 * @Date: 2017/8/14:22
 */
public class Scenario {
    /**
     * 主键
     */
    private int id;
    /**
     * 名称
     */
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 父场景
     */
    private Scenario parent;
    /**
     * 父场景id
     */
    private Integer parentId;
    /**
     * 父场景名称
     */
    private String parentName;
    /**
     * 场景连接地址
     */
    private String url;
    /**
     * 链接打开目标
     */
    private String urlTarget;
    /**
     * icon
     */
    private String icon;
    /**
     * 访问场景的权限名称
     */
    private String authorityName;
    /**
     * 在父场景中的顺序
     */
    private int orderIndex = 0;

    /**
     * 包含的子场景
     *
     * @param o
     * @return
     */
    private List<Scenario> children;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Scenario scenario = (Scenario) o;

        return getId() == scenario.getId();
    }

    @Override
    public String toString() {
        return "Scenario{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", parent=" + parent +
                ", parentId=" + parentId +
                ", parentName='" + parentName + '\'' +
                ", url='" + url + '\'' +
                ", urlTarget='" + urlTarget + '\'' +
                ", icon='" + icon + '\'' +
                ", authorityName='" + authorityName + '\'' +
                ", orderIndex=" + orderIndex +
                ", children=" + children +
                '}';
    }

    @Override
    public int hashCode() {
        return getId();
    }

    public Scenario getParent() {
        if (parentId == null) {
            parent = null;
        } else {
            parent = parent == null ? new Scenario() : parent;
            parent.setId(parentId);
            parent.setName(parentName);
        }
        return parent;
    }

    public void setParent(Scenario parent) {
        if (parent == null) {
            parentId = null;
            parentName = null;
        } else {
            parentId = parent.getId();
            parentName = parent.getName();
        }
        this.parent = getParent();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Scenario> getChildren() {
        return children;
    }

    public void setChildren(List<Scenario> children) {
        this.children = children;
    }

    public String getUrlTarget() {
        return urlTarget;
    }

    public void setUrlTarget(String urlTarget) {
        this.urlTarget = urlTarget;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }
}
