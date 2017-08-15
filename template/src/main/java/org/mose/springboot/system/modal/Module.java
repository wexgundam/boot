package org.mose.springboot.system.modal;

import java.util.List;

/**
 * Description:系统模块
 *
 * @Author: 靳磊
 * @Date: 2017/8/14:22
 */
public class Module {
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
     * 父模块
     */
    private Module parent;
    /**
     * 场景
     */
    private Integer scenarioId;
    /**
     * icon
     */
    private String icon;
    /**
     * 在父模块中的显示顺序
     */
    private int displayOrder = 0;

    /**
     * 包含的子模块
     *
     * @param o
     * @return
     */
    private List<Module> children;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Module module = (Module) o;

        return getId() == module.getId();
    }

    @Override
    public String toString() {
        return "Module{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", parent=" + parent +
                ", icon='" + icon + '\'' +
                ", displayOrder=" + displayOrder +
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
            parent = new Module();
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

    public Module getParent() {
        return parent;
    }

    public void setParent(Module parent) {
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

    public Integer getScenarioId() {
        return scenarioId;
    }

    public void setScenarioId(Integer scenarioId) {
        this.scenarioId = scenarioId;
    }

    public List<Module> getChildren() {
        return children;
    }

    public void setChildren(List<Module> children) {
        this.children = children;
    }
}
