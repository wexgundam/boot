package org.mose.springboot.metronic.sidebar.modal;

import java.util.List;

/**
 * Description:侧边菜单项Sidebar Item
 *
 * 包括两类，heading和nav-item
 *
 * heading显示为标题，不含任何子菜单项
 *
 * nav-item显示为菜单，可作为父菜单包含若干菜单子项
 *
 * @Author: 靳磊
 * @Date: 2017/8/14:22
 */
public class SidebarItem {
    /**
     * 菜单项类型：标题
     */
    public static final String TYPE_HEADING = "heading";
    /**
     * 菜单项类型：菜单
     */
    public static final String TYPE_NAV_ITEM = "nav-item";

    /**
     * 主键
     */
    private int id;
    /**
     * 名称
     */
    private String name;
    /**
     * 类型
     */
    private String type = TYPE_NAV_ITEM;
    /**
     * icon
     */
    private String icon;
    /**
     * 在父模块中的显示顺序
     */
    private int order = 0;

    /**
     * 父模块
     */
    private SidebarItem parent;
    /**
     * 包含的子模块
     *
     * @param o
     * @return
     */
    private List<SidebarItem> children;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SidebarItem module = (SidebarItem) o;

        return getId() == module.getId();
    }

    @Override
    public String toString() {
        return "SidebarItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", parent=" + parent +
                ", icon='" + icon + '\'' +
                ", order=" + order +
                ", children=" + children +
                '}';
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
