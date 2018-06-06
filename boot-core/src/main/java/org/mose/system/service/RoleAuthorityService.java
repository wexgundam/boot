package org.mose.system.service;

import org.mose.common.service.ISessionService;
import org.mose.system.modal.Authority;
import org.mose.system.modal.Role;
import org.mose.system.modal.RoleAuthority;
import org.mose.system.repository.IRoleAuthorityRepository;
import org.mose.util.code.ReturnCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * what:    用户角色服务
 *
 * @Author: 靳磊
 * @Date: 2017/8/18 14:43
 */
@Service
public class RoleAuthorityService {
    /**
     * 权限数组分隔符
     */
    public static final String AUTHORITY_ID_ARRAY_STRING_SPLITTER = "@";
    /**
     * 角色数据获取对象
     */
    @Autowired
    private IRoleAuthorityRepository roleAuthorityRepository;
    /**
     * session服务
     */
    @Autowired
    private ISessionService sessionService;

    /**
     * what:    查询给定用户的角色
     *
     * @param roleId
     * @param pageNumber
     * @param pageRowCount
     *
     * @return
     */
    public List<Authority> queryManyAuthoritiesByRoleId(int roleId, int pageNumber, int pageRowCount) {
        List<Authority> authorities = roleAuthorityRepository.queryManyAuthoritiesByRoleId(roleId, pageNumber, pageRowCount);
        return authorities;
    }

    /**
     * what:    查询给定用户的角色
     *
     * @param roleId
     *
     * @return
     */
    public List<Authority> queryAllAuthoritiesByRoleId(int roleId) {
        List<Authority> authorities = roleAuthorityRepository.queryAllAuthoritiesByRoleId(roleId);
        return authorities;
    }

    /**
     * what:    查询给定用户的角色总数
     *
     * @param roleId
     *
     * @return
     */
    public int queryAuthorityCountByRoleId(int roleId) {
        return roleAuthorityRepository.queryCountByRoleId(roleId);
    }

    /**
     * what:    根据给定的用户id查询对应的权限
     *
     * @param userId
     * @param pageNumber
     * @param pageRowCount
     *
     * @return
     */
    public Object queryManyAuthoritiesByUserId(int userId, int pageNumber, int pageRowCount) {
        List<Authority> authorities = roleAuthorityRepository.queryManyAuthoritiesByUserId(userId, pageNumber, pageRowCount);
        return authorities;
    }

    /**
     * what:    根据给定的用户id查询对应的权限总数
     *
     * @param userId
     *
     * @return
     */
    public int queryAuthorityCountByUserId(int userId) {
        return roleAuthorityRepository.queryCountByUserId(userId);
    }

    /**
     * what:    根据给定的用户id查询全部权限
     *
     * @param userId
     *
     * @return
     */
    public List<Authority> queryAllAuthoritiesByUserId(int userId) {
        return roleAuthorityRepository.queryAllAuthoritiesByUserId(userId);
    }


    /**
     * 根据给定的权限id查询对应的全部角色
     *
     * @param authorityId
     *
     * @return
     */
    public List<Role> queryAllRolesByAuthorityId(int authorityId) {
        return roleAuthorityRepository.queryAllRolesByAuthorityId(authorityId);
    }

    /**
     * what:    根据权限数组根更新给定的角色id的权限
     *
     * @param roleId
     */
    @Transactional
    public int updateRoleAuthorities(int roleId, String authorityIdArrayString) {
        deleteRoleAuthoritiesByRoleId(roleId);
        if (authorityIdArrayString != null && authorityIdArrayString.contains(AUTHORITY_ID_ARRAY_STRING_SPLITTER)) {
            for (String authorityIdString : authorityIdArrayString.split(AUTHORITY_ID_ARRAY_STRING_SPLITTER)) {
                RoleAuthority roleAuthority = new RoleAuthority();
                roleAuthority.setRoleId(roleId);
                roleAuthority.setAuthorityId(Integer.parseInt(authorityIdString));
                roleAuthorityRepository.insertOne(roleAuthority);
            }
        }
        sessionService.deleteAllSessionsByRoleId(roleId);
        return ReturnCodeUtil.SUCCESS__UPDATE;
    }

    /**
     * what:    删除给定角色id对应的权限
     *
     * @param roleId
     * @param authorityId
     *
     * @return
     */
    @Transactional
    public int deleteRoleAuthority(int roleId, int authorityId) {
        sessionService.deleteAllSessionsByRoleId(roleId);
        return roleAuthorityRepository.deleteOne(roleId, authorityId);
    }

    /**
     * what:    删除给定角色的全部权限
     *
     * @param roleId
     */
    @Transactional
    public int deleteRoleAuthoritiesByRoleId(int roleId) {
        sessionService.deleteAllSessionsByRoleId(roleId);
        return roleAuthorityRepository.deleteAllByRoleId(roleId);
    }

    /**
     * what:    如果角色包含给定的权限，则删除权限
     *
     * @param authorityId
     *
     * @return
     */
    @Transactional
    public int deleteRoleAuthoritiesByAuthorityId(int authorityId) {
        sessionService.deleteAllSessionsByAuthorityId(authorityId);
        return roleAuthorityRepository.deleteAllByAuthorityId(authorityId);
    }

    public IRoleAuthorityRepository getRoleAuthorityRepository() {
        return roleAuthorityRepository;
    }

    public void setRoleAuthorityRepository(IRoleAuthorityRepository roleAuthorityRepository) {
        this.roleAuthorityRepository = roleAuthorityRepository;
    }
}
