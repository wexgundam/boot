package org.mose.boot.system.repository;

import org.mose.boot.system.modal.Authority;
import org.mose.boot.system.modal.Role;
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
     * 查询给定觉对应的权限总数
     */
    int queryCountByRoleId(int roleId);

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
     * 根据给定的属性删除
     *
     * @param roleId
     * @param authorityId
     *
     * @return
     */
    int deleteOne(int roleId, int authorityId);

    /**
     * 删除给定角色对应的全部角色权限
     */
    int deleteAllByRoleId(int roleId);

    /**
     * 删除给定权限对应的全部角色权限
     */
    int deleteAllByAuthorityId(int authorityId);

    /**
     * 分页查询给定角色对应的权限
     */
    List<Authority> queryManyAuthoritiesByRoleId(int roleId, int pageNumber, int pageRowCount);

    /**
     * 查询给定角色对应的全部权限
     */
    List<Authority> queryAllAuthoritiesByRoleId(int roleId);
//
//    /**
//     * 查询给定用户对应的全部权限
//     */
//    List<Authority> queryAllAuthoritiesByUserId(int userId);
}
