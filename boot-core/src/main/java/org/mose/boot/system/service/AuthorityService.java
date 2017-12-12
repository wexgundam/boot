package org.mose.boot.system.service;

import org.mose.boot.system.modal.Authority;
import org.mose.boot.system.repository.IAuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Description: 权限服务
 *
 * @Author: 靳磊
 * @Date: 2017/8/18 14:43
 */
@Service
public class AuthorityService {
    /**
     * 权限数据获取对象
     */
    @Autowired
    private IAuthorityRepository authorityRepository;
    @Autowired
    private RoleAuthorityService roleAuthorityService;

    /**
     * 根据给定的id查询
     *
     * @param id
     *
     * @return
     */
    public Authority queryAuthority(int id) {
        return authorityRepository.queryOne(id);
    }

    /**
     * 获取所有场景并按照树形组织排序
     *
     * @return
     */
    public List<Authority> queryManyAuthorities(int pageNumber, int pageRowCount) {
        List<Authority> authorities = authorityRepository.queryMany(pageNumber, pageRowCount);
        return authorities;
    }


    /**
     * 获取所有角色列表
     *
     * @return
     */
    public List<Authority> queryAllAuthorities() {
        List<Authority> authorities = authorityRepository.queryAll();
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
    public int queryAuthorityCount() {
        return authorityRepository.queryCount();
    }

    /**
     * Description:删除记录
     *
     * @param authority
     *
     * @return
     *
     * @Author: 靳磊
     * @Date: 2017/10/18 13:19
     */
    @Transactional
    public int addAuthority(Authority authority) {
        return authorityRepository.insertOne(authority);
    }


    /**
     * 更新
     *
     * @param authority
     */
    @Transactional
    public int updateAuthority(Authority authority) {
        return authorityRepository.updateOne(authority);
    }

    /**
     * 删除给定id对应的记录
     *
     * @param id
     *
     * @return
     */
    @Transactional
    public int deleteAuthority(int id) {
        roleAuthorityService.deleteRoleAuthoritiesByAuthorityId(id);
        return authorityRepository.deleteOne(id);
    }

    public IAuthorityRepository getAuthorityRepository() {
        return authorityRepository;
    }

    public void setAuthorityRepository(IAuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public RoleAuthorityService getRoleAuthorityService() {
        return roleAuthorityService;
    }

    public void setRoleAuthorityService(RoleAuthorityService roleAuthorityService) {
        this.roleAuthorityService = roleAuthorityService;
    }
}
