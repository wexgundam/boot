package org.mose.springboot.system.modal;

import java.util.List;

/**
 * Description:场景
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
     * 场景连接地址
     */
    private String url;
    /**
     * icon
     */
    private String icon;
    /**
     * 在父场景中的顺序
     */
    private int displayOrder = 0;

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
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", displayOrder=" + displayOrder +
                ", children=" + children +
                '}';
    }

    @Override
    public int hashCode() {
        return getId();
    }


    public void setParentId(Integer parentId) {
        if (parentId == null) {
            parent = null;
            return;
        } else {
            parent = new Scenario();
            parent.setId(parentId);
        }
    }

    public Integer getParentId() {
        return parent == null ? null : parent.getId();
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

    public Scenario getParent() {
        return parent;
    }

    public void setParent(Scenario parent) {
        this.parent = parent;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
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
}
