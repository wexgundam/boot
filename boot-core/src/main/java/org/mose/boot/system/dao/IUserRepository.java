package org.mose.boot.system.dao;

import org.mose.boot.system.modal.User;

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
     * @return
     */
    User queryOne(int id);

    /**
     * 查询全部用户
     */
    List<User> queryAll();

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
     * @return
     */
    int deleteOne(int id);
}
