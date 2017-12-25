/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:boot
 * Module Name:TODO
 */
package org.mose.boot.system.modal;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * what:    角色. <br/>
 * when:    角色是对权限的分组.<br/>
 * <p>
 * 角色是对权限的分组，可以将权限划分为若干组，每一组用角色表示
 *
 * @author 靳磊 created on 2017/12/6
 */
public class Role {
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

    private List<Authority> authorities;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", authorities=" + authorities +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        return getId() == role.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Authority> getAuthorities() {
        authorities = authorities == null ? new ArrayList<>() : authorities;
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
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
}
