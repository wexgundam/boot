package org.mose.boot.util.ztree;

import java.util.List;

/**
 * Description: 描述ZTree treeNode数据结构
 *
 * @Author: 靳磊
 * @Date: 2017/9/13:22
 */
public class TreeNode {
    // 节点标识
    private String id;
    // 父节点标识
    private String pId;
    // 显示名称
    private String name;
    // 是否展开
    private boolean open = false;
    // 包含的子节点集合
    private List<TreeNode> children;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TreeNode treeNode = (TreeNode) o;

        return getId() != null ? getId().equals(treeNode.getId()) : treeNode.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "id='" + id + '\'' +
                ", pId='" + pId + '\'' +
                ", name='" + name + '\'' +
                ", open=" + open +
                ", children=" + children +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPId() {
        return pId;
    }

    public void setPId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
}
