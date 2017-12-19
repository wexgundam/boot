package org.mose.boot.system.repository;

import org.mose.boot.system.modal.Role;

import java.util.List;

/**
 * Description: 角色数据获取对象
 *
 * @Author: 靳磊
 * @Date: 2017/8/14:22
 */
public interface IRoleRepository {
    /**
     * 根据id查询
     *
     * @param id
     *
     * @return
     */
    Role queryOne(int id);

    /**
     * 根据角色名称查询数据是否已存在
     *
     * @param name
     *
     * @return
     */
    boolean queryExistByName(String name);

    /**
     * 分页查询
     */
    List<Role> queryMany(int pageNumber, int pageRowCount);

    /**
     * 给定查询语句查询
     *
     * @param sql
     * @param pageNumber
     * @param pageRowCount
     *
     * @return
     */
    List<Role> queryMany(String sql, Object[] parameters, int pageNumber, int pageRowCount);

    /**
     * 给定查询语句查询
     *
     * @param sql
     *
     * @return
     */
    List<Role> queryAll(String sql, Object... parameters);

    /**
     * 查询全部用户
     */
    List<Role> queryAll();

    /**
     * 查询总数
     *
     * @return
     */
    int queryCount();

    /**
     * 插入一条记录
     *
     * @param role
     */
    int insertOne(Role role);

    /**
     * 更新一条记录
     *
     * @param role
     */
    int updateOne(Role role);

    /**
     * 删除一条记录
     *
     * @param role
     */
    int deleteOne(Role role);

    /**
     * 根据Id删除记录
     *
     * @param id
     *
     * @return
     */
    int deleteOne(int id);
}
