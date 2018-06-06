package org.mose.system.service;

import org.mose.system.modal.Role;
import org.mose.system.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * what:    角色服务
 *
 * @Author: 靳磊
 * @Date: 2017/8/18 14:43
 */
@Service
public class RoleService {
    /**
     * 角色数据获取对象
     */
    @Autowired
    private IRoleRepository roleRepository;
    /**
     * 用户角色服务
     */
    @Autowired
    private UserRoleService userRoleService;
    /**
     * 角色权限服务
     */
    @Autowired
    private RoleAuthorityService roleAuthorityService;

    /**
     * what:    根据给定的id查询
     *
     * @param id
     *
     * @return
     */
    public Role queryRole(int id) {
        return roleRepository.queryOne(id);
    }

    /**
     * what:    获取所有角色并按照树形组织排序
     *
     * @return
     */
    public List<Role> queryManyRoles(int pageNumber, int pageRowCount) {
        List<Role> authorities = roleRepository.queryMany(pageNumber, pageRowCount);
        return authorities;
    }


    /**
     * what:    获取所有角色列表
     *
     * @return
     */
    public List<Role> queryAllRoles() {
        List<Role> authorities = roleRepository.queryAll();
        return authorities;
    }

    /**
     * what:    获取用户总数. <br/>
     *
     * @param
     *
     * @return
     *
     * @author 靳磊 created on 2017/12/9
     */
    public int queryRoleCount() {
        return roleRepository.queryCount();
    }

    /**
     * what:    删除记录
     *
     * @param role
     *
     * @return
     *
     * @Author: 靳磊
     * @Date: 2017/10/18 13:19
     */
    @Transactional
    public int addRole(Role role) {
        return roleRepository.insertOne(role);
    }


    /**
     * what:    更新
     *
     * @param role
     */
    @Transactional
    public int updateRole(Role role) {
        return roleRepository.updateOne(role);
    }

    /**
     * what:    删除给定id对应的记录
     *
     * @param id
     *
     * @return
     */
    @Transactional
    public int deleteRole(int id) {
        userRoleService.deleteUserRolesByRoleId(id);
        roleAuthorityService.deleteRoleAuthoritiesByRoleId(id);
        return roleRepository.deleteOne(id);
    }

    public IRoleRepository getRoleRepository() {
        return roleRepository;
    }

    public void setRoleRepository(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public UserRoleService getUserRoleService() {
        return userRoleService;
    }

    public void setUserRoleService(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    public RoleAuthorityService getRoleAuthorityService() {
        return roleAuthorityService;
    }

    public void setRoleAuthorityService(RoleAuthorityService roleAuthorityService) {
        this.roleAuthorityService = roleAuthorityService;
    }
}
