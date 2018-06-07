package org.mose.system.repository;

import org.mose.system.modal.Authority;
import org.mose.system.modal.Role;
import org.mose.system.modal.RoleAuthority;

import java.util.List;

/**
 * what:    角色权限数据获取对象
 *
 * @Author: 靳磊
 * @Date: 2017/8/14:22
 */
public interface IRoleAuthorityRepository {
    /**
     * what:    根据id查询
     *
     * @param id
     *
     * @return
     */
    RoleAuthority queryOne(int id);

    /**
     * what:    查询给定角色对应的全部角色权限
     */
    List<RoleAuthority> queryAllByRoleId(int roleId);

    /**
     * what:    查询给定权限对应的全部角色权限
     */
    List<RoleAuthority> queryAllByAuthorityId(int authorityId);

    /**
     * what:    查询给定觉对应的权限总数
     */
    int queryCountByRoleId(int roleId);

    /**
     * what:    插入一条记录
     *
     * @param roleAuthority
     */
    int insertOne(RoleAuthority roleAuthority);

    /**
     * what:    更新一条记录
     *
     * @param roleAuthority
     */
    int updateOne(RoleAuthority roleAuthority);

    /**
     * what:    删除一条记录
     *
     * @param roleAuthority
     */
    int deleteOne(RoleAuthority roleAuthority);

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
     * @param roleId
     * @param authorityId
     *
     * @return
     */
    int deleteOne(int roleId, int authorityId);

    /**
     * what:    删除给定角色对应的全部角色权限
     */
    int deleteAllByRoleId(int roleId);

    /**
     * what:    删除给定权限对应的全部角色权限
     */
    int deleteAllByAuthorityId(int authorityId);

    /**
     * what:    分页查询给定角色对应的权限
     */
    List<Authority> queryManyAuthoritiesByRoleId(int roleId, int pageNumber, int pageRowCount);

    /**
     * what:    查询给定角色对应的全部权限
     */
    List<Authority> queryAllAuthoritiesByRoleId(int roleId);

    /**
     * what:    查询给定用户对应的全部权限
     */
    List<Authority> queryManyAuthoritiesByUserId(int userId, int pageNumber, int pageRowCount);

    /**
     * what:    查询给定用户对应的全部权限
     */
    List<Authority> queryAllAuthoritiesByUserId(int userId);

    /**
     * what:    查询给定用户的权限总数
     *
     * @param userId
     *
     * @return
     */
    int queryCountByUserId(int userId);

    /**
     * what:    查询给定权限关联的角色
     *
     * @param authorityId
     *
     * @return
     */
    List<Role> queryAllRolesByAuthorityId(int authorityId);
}
