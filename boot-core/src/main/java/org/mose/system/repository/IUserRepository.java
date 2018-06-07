package org.mose.system.repository;

import org.mose.system.modal.User;

import java.util.List;

/**
 * what:    用户数据获取对象
 *
 * @Author: 靳磊
 * @Date: 2017/8/14:22
 */
public interface IUserRepository {
    /**
     * what:    根据id查询
     *
     * @param id
     *
     * @return
     */
    User queryOne(int id);


    /**
     * what:    根据用户账户名查询
     *
     * @param username
     *
     * @return
     */
    User queryOneByUsername(String username);

    /**
     * what:    查询是否已存在给定用户名的数据
     *
     * @param username
     *
     * @return
     */
    boolean queryExistByUsername(String username);

    /**
     * what:    分页查询用户
     */
    List<User> queryMany(int pageNumber, int pageRowCount);

    /**
     * what:    查询全部用户
     */
    List<User> queryAll();

    /**
     * what:    查询具有给定角色id的所有用户
     *
     * @param roleId
     *
     * @return
     */
    List<User> queryAllByRoleId(int roleId);

    /**
     * what:    查询用户总数
     *
     * @return
     */
    int queryCount();

    /**
     * what:    插入一条记录
     *
     * @param user
     */
    int insertOne(User user);

    /**
     * what:    更新一条记录
     *
     * @param user
     */
    int updateOne(User user);

    /**
     * what:    删除一条记录
     *
     * @param user
     */
    int deleteOne(User user);

    /**
     * what:    根据Id删除记录
     *
     * @param id
     *
     * @return
     */
    int deleteOne(int id);
}
