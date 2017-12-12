package org.mose.boot.system.service;

import org.mose.boot.system.modal.Authority;
import org.mose.boot.system.modal.RoleAuthority;
import org.mose.boot.system.repository.IRoleAuthorityRepository;
import org.mose.boot.util.code.ReturnCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Description: 用户角色服务
 *
 * @Author: 靳磊
 * @Date: 2017/8/18 14:43
 */
@Service
public class RoleAuthorityService {
    public static final String AUTHORITY_ID_ARRAY_STRING_SPLITTER = "@";
    /**
     * 角色数据获取对象
     */
    @Autowired
    private IRoleAuthorityRepository roleAuthorityRepository;

    /**
     * 查询给定用户的角色
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
     * 查询给定用户的角色
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
     * 查询给定用户的角色总数
     *
     * @param roleId
     *
     * @return
     */
    public int queryAuthorityCountByRoleId(int roleId) {
        return roleAuthorityRepository.queryCountByRoleId(roleId);
    }


    public Object queryManyAuthoritiesByUserId(int userId, int pageNumber, int pageRowCount) {
        List<Authority> authorities = roleAuthorityRepository.queryManyAuthoritiesByUserId(userId, pageNumber, pageRowCount);
        return authorities;
    }

    public int queryAuthorityCountByUserId(int userId) {
        return roleAuthorityRepository.queryCountByUserId(userId);
    }


    /**
     * 更新
     *
     * @param roleId
     */
    @Transactional
    public int updateRoleAuthorities(int roleId, String authorityIdArrayString) {
        deleteRoleAuthoritiesByRoleId(roleId);
        if (authorityIdArrayString != null) {
            for (String authorityIdString : authorityIdArrayString.split(AUTHORITY_ID_ARRAY_STRING_SPLITTER)) {
                RoleAuthority roleAuthority = new RoleAuthority();
                roleAuthority.setRoleId(roleId);
                roleAuthority.setAuthorityId(Integer.parseInt(authorityIdString));
                roleAuthorityRepository.insertOne(roleAuthority);
            }
        }
        return ReturnCodeUtil.SUCCESS__UPDATE;
    }

    @Transactional
    public int deleteRoleAuthority(int roleId, int authorityId) {
        return roleAuthorityRepository.deleteOne(roleId, authorityId);
    }

    /**
     * 更新
     *
     * @param roleId
     */
    @Transactional
    public int deleteRoleAuthoritiesByRoleId(int roleId) {
        return roleAuthorityRepository.deleteAllByRoleId(roleId);
    }


    public int deleteRoleAuthoritiesByAuthorityId(int authorityId) {
        return roleAuthorityRepository.deleteAllByAuthorityId(authorityId);
    }

    public IRoleAuthorityRepository getRoleAuthorityRepository() {
        return roleAuthorityRepository;
    }

    public void setRoleAuthorityRepository(IRoleAuthorityRepository roleAuthorityRepository) {
        this.roleAuthorityRepository = roleAuthorityRepository;
    }
}
