/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:boot
 * Module Name:TODO
 */
package org.mose.system.modal;

/**
 * what:    角色包含的权限项. <br/>
 *
 * @author 靳磊 created on 2017/12/6
 */
public class RoleAuthority {
    /**
     * 主键
     */
    private int id;
    /**
     * 权限id
     */
    private int authorityId;
    /**
     * 角色id
     */
    private int roleId;

    @Override
    public String toString() {
        return "RoleAuthority{" +
                "id=" + id +
                ", authorityId=" + authorityId +
                ", roleId=" + roleId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleAuthority that = (RoleAuthority) o;

        return getId() == that.getId();
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

    public int getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
