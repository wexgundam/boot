package org.mose.boot.system.repository;

import org.mose.boot.system.modal.Authority;

import java.util.List;

/**
 * Description: 权限数据获取对象
 *
 * @Author: 靳磊
 * @Date: 2017/8/14:22
 */
public interface IAuthorityRepository {
    /**
     * 根据id查询
     *
     * @param id
     *
     * @return
     */
    Authority queryOne(int id);

    /**
     * 根据权限名称查询数据是否已存在
     *
     * @param name
     *
     * @return
     */
    boolean queryExistByName(String name);

    /**
     * 分页查询
     */
    List<Authority> queryMany(int pageNumber, int pageRowCount);

    /**
     * 查询全部用户
     */
    List<Authority> queryAll();

    /**
     * 查询总数
     *
     * @return
     */
    int queryCount();

    /**
     * 查询给定角色的全部权限
     */
    List<Authority> queryAllByRoleId(int roleId);

    /**
     * 查询给定用户的全部权限
     */
    List<Authority> queryAllByUserId(int userId);

    /**
     * 插入一条记录
     *
     * @param authority
     */
    int insertOne(Authority authority);

    /**
     * 更新一条记录
     *
     * @param authority
     */
    int updateOne(Authority authority);

    /**
     * 删除一条记录
     *
     * @param authority
     */
    int deleteOne(Authority authority);

    /**
     * 根据Id删除记录
     *
     * @param id
     *
     * @return
     */
    int deleteOne(int id);
}
