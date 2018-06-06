package org.mose.system.repository;

import org.mose.system.modal.Role;
import org.mose.system.modal.UserRole;

import java.util.List;

/**
 * what:    用户角色数据获取对象
 *
 * @Author: 靳磊
 * @Date: 2017/8/14:22
 */
public interface IUserRoleRepository {
    /**
     * what:    根据id查询
     *
     * @param id
     *
     * @return
     */
    UserRole queryOne(int id);

    /**
     * what:    查询给定用户对应的全部用户角色
     */
    List<UserRole> queryAllByUserId(int userId);

    /**
     * what:    查询给定角色对应的全部用户角色
     */
    List<UserRole> queryAllByRoleId(int roleId);

    /**
     * what:    查询给定用户对应的角色总数
     */
    int queryCountByUserId(int userId);

    /**
     * what:    插入一条记录
     *
     * @param userRole
     */
    int insertOne(UserRole userRole);

    /**
     * what:    更新一条记录
     *
     * @param userRole
     */
    int updateOne(UserRole userRole);

    /**
     * what:    删除一条记录
     *
     * @param userRole
     */
    int deleteOne(UserRole userRole);

    /**
     * what:    根据Id删除记录
     *
     * @param id
     *
     * @return
     */
    int deleteOne(int id);


    /**
     * what:    根据给定的属性删除
     *
     * @param userId
     * @param roleId
     *
     * @return
     */
    int deleteOne(int userId, int roleId);

    /**
     * what:    删除给定用户对应的全部用户角色
     */
    int deleteAllByUserId(int userId);

    /**
     * what:    删除给定角色对应的全部用户角色
     */
    int deleteAllByRoleId(int roleId);

    /**
     * what:    分页查询给定用户对应的角色
     */
    List<Role> queryManyRolesByUserId(int userId, int pageNumber, int pageRowCount);

    /**
     * what:    查询给定用户对应的全部角色
     */
    List<Role> queryAllRolesByUserId(int userId);
}
