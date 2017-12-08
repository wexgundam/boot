package org.mose.boot.system.repository;

import org.mose.boot.system.modal.RoleAuthority;

import java.util.List;

/**
 * Description: 角色权限数据获取对象
 *
 * @Author: 靳磊
 * @Date: 2017/8/14:22
 */
public interface IRoleAuthorityRepository {
    /**
     * 根据id查询
     *
     * @param id
     *
     * @return
     */
    RoleAuthority queryOne(int id);


    /**
     * 查询给定角色对应的全部角色权限
     */
    List<RoleAuthority> queryAllByRoleId(int roleId);

    /**
     * 查询给定权限对应的全部角色权限
     */
    List<RoleAuthority> queryAllByAuthorityId(int authorityId);

    /**
     * 插入一条记录
     *
     * @param roleAuthority
     */
    int insertOne(RoleAuthority roleAuthority);

    /**
     * 更新一条记录
     *
     * @param roleAuthority
     */
    int updateOne(RoleAuthority roleAuthority);

    /**
     * 删除一条记录
     *
     * @param roleAuthority
     */
    int deleteOne(RoleAuthority roleAuthority);

    /**
     * 根据Id删除记录
     *
     * @param id
     *
     * @return
     */
    int deleteOne(int id);

    /**
     * 删除给定角色对应的全部角色权限
     */
    int deleteAllByRoleId(int userId);

    /**
     * 删除给定权限对应的全部角色权限
     */
    int deleteAllByAuthorityId(int authorityId);
}
