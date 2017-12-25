/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:boot
 * Module Name:TODO
 */
package org.mose.boot.system.modal;

/**
 * what:    用户角色. <br/>
 * <p>
 * 为了便于管理用户的权限，通过角色对权限进行了分组
 * 再通过为用户赋予角色快速、批量设置权限
 *
 * @author 靳磊 created on 2017/12/6
 */
public class UserRole {
    /**
     * 主键
     */
    private int id;
    /**
     * 用户id
     */
    private int userId;
    /**
     * 角色id
     */
    private int roleId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRole userRole = (UserRole) o;

        return getId() == userRole.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
