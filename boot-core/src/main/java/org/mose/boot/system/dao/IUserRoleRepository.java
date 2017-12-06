package org.mose.boot.system.dao;

import org.mose.boot.system.modal.UserRole;

import java.util.List;

/**
 * Description: 用户角色数据获取对象
 *
 * @Author: 靳磊
 * @Date: 2017/8/14:22
 */
public interface IUserRoleRepository {
    /**
     * 根据id查询
     *
     * @param id
     *
     * @return
     */
    UserRole queryOne(int id);

    /**
     * 查询全部用户角色
     */
    List<UserRole> queryAll();

    /**
     * 插入一条记录
     *
     * @param userRole
     */
    int insertOne(UserRole userRole);

    /**
     * 更新一条记录
     *
     * @param userRole
     */
    int updateOne(UserRole userRole);

    /**
     * 删除一条记录
     *
     * @param userRole
     */
    int deleteOne(UserRole userRole);

    /**
     * 根据Id删除记录
     *
     * @param id
     *
     * @return
     */
    int deleteOne(int id);
}
