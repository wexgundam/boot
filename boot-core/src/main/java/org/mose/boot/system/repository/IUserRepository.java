package org.mose.boot.system.repository;

import org.mose.boot.system.modal.User;
import org.mose.boot.util.page.PageNavigate;

import java.util.List;

/**
 * Description: 用户数据获取对象
 *
 * @Author: 靳磊
 * @Date: 2017/8/14:22
 */
public interface IUserRepository {
    /**
     * 根据id查询
     *
     * @param id
     *
     * @return
     */
    User queryOne(int id);


    /**
     * 根据用户账户名查询
     *
     * @param username
     *
     * @return
     */
    User queryOneByUsername(String username);

    /**
     * 查询是否已存在给定用户名的数据
     *
     * @param username
     *
     * @return
     */
    boolean queryExistByUsername(String username);

    /**
     * 分页查询用户
     */
    List<User> queryMany(int pageNumber, int pageRowCount);

    /**
     * 查询全部用户
     */
    List<User> queryAll();

    /**
     * 查询用户总数
     *
     * @return
     */
    int queryCount();

    /**
     * 插入一条记录
     *
     * @param user
     */
    int insertOne(User user);

    /**
     * 更新一条记录
     *
     * @param user
     */
    int updateOne(User user);

    /**
     * 删除一条记录
     *
     * @param user
     */
    int deleteOne(User user);

    /**
     * 根据Id删除记录
     *
     * @param id
     *
     * @return
     */
    int deleteOne(int id);
}
