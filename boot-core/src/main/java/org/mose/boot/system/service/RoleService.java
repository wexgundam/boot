package org.mose.boot.system.service;

import org.mose.boot.system.modal.Role;
import org.mose.boot.system.repository.IRoleRepository;
import org.mose.boot.system.repository.IUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Description: 角色服务
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
     * 根据给定的id查询
     *
     * @param id
     *
     * @return
     */
    public Role queryRole(int id) {
        return roleRepository.queryOne(id);
    }

    /**
     * 获取所有角色并按照树形组织排序
     *
     * @return
     */
    public List<Role> queryManyRoles(int pageNumber, int pageRowCount) {
        List<Role> authorities = roleRepository.queryMany(pageNumber, pageRowCount);
        return authorities;
    }


    /**
     * 获取所有角色列表
     *
     * @return
     */
    public List<Role> queryAllRoles() {
        List<Role> authorities = roleRepository.queryAll();
        return authorities;
    }

    /**
     * what:    获取用户总数. <br/>
     * when:    (这里描述这个方法适用时机 – 可选).<br/>
     * how:     (这里描述这个方法的执行流程或使用方法 – 可选).<br/>
     * warning: (这里描述这个方法的注意事项 – 可选).<br/>
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
     * Description:删除记录
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
     * 更新
     *
     * @param role
     */
    @Transactional
    public int updateRole(Role role) {
        return roleRepository.updateOne(role);
    }

    /**
     * 删除给定id对应的记录
     *
     * @param id
     *
     * @return
     */
    @Transactional
    public int deleteRole(int id) {
        return roleRepository.deleteOne(id);
    }

    public IRoleRepository getRoleRepository() {
        return roleRepository;
    }

    public void setRoleRepository(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}
