package org.mose.system.repository;

import org.mose.system.modal.Authority;

import java.util.List;

/**
 * what:    权限数据获取对象
 *
 * @Author: 靳磊
 * @Date: 2017/8/14:22
 */
public interface IAuthorityRepository {
    /**
     * what:    根据id查询
     *
     * @param id
     *
     * @return
     */
    Authority queryOne(int id);

    /**
     * what:    根据权限名称查询数据是否已存在
     *
     * @param name
     *
     * @return
     */
    boolean queryExistByName(String name);

    /**
     * what:    给定查询语句查询
     *
     * @param sql
     * @param pageNumber
     * @param pageRowCount
     *
     * @return
     */
    List<Authority> queryMany(String sql, int pageNumber, int pageRowCount, Object... parameters);

    /**
     * what:    分页查询
     */
    List<Authority> queryMany(int pageNumber, int pageRowCount);

    /**
     * what:    查询全部用户
     */
    List<Authority> queryAll();

    /**
     * what:    给定查询语句查询
     *
     * @param sql
     *
     * @return
     */
    List<Authority> queryAll(String sql, Object... parameters);

    /**
     * what:    查询总数
     *
     * @return
     */
    int queryCount();

    /**
     * what:    插入一条记录
     *
     * @param authority
     */
    int insertOne(Authority authority);

    /**
     * what:    更新一条记录
     *
     * @param authority
     */
    int updateOne(Authority authority);

    /**
     * what:    删除一条记录
     *
     * @param authority
     */
    int deleteOne(Authority authority);

    /**
     * what:    根据Id删除记录
     *
     * @param id
     *
     * @return
     */
    int deleteOne(int id);
}
