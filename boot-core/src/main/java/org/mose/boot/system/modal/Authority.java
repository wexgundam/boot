/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:boot
 * Module Name:TODO
 */
package org.mose.boot.system.modal;

/**
 * what:    权限. <br/>
 * how:     命名方式：场景全域名+权限名.<br/>
 * <p>
 * 描述各场景下各种资源的各种权限
 * 场景全域：某场景的全索引域。例如系统管理下的用户管理，其全域名为：系统管理/用户管理
 *
 * @author 靳磊 created on 2017/12/6
 */
public class Authority {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Authority authority = (Authority) o;

        return getId() == authority.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
