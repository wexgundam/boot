package org.mose.boot.system.service;

import org.mose.boot.system.modal.Authority;
import org.mose.boot.system.modal.User;
import org.mose.boot.system.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * what:    用户服务
 *
 * @Author: 靳磊
 * @Date: 2017/8/18 14:43
 */
@Service
public class UserService {
    /**
     * spring security提供的密码加密解密服务
     */
    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * 权限数据获取对象
     */
    @Autowired
    private IUserRepository userRepository;
    /**
     * 用户角色服务
     */
    @Autowired
    private UserRoleService userRoleService;
    /**
     * 角色获取对象
     */
    @Autowired
    private RoleService roleService;
    /**
     * 角色权限服务
     */
    @Autowired
    private RoleAuthorityService roleAuthorityService;

    /**
     * 根据给定的id查询
     *
     * @param id
     *
     * @return
     */
    public User queryUser(int id) {
        return userRepository.queryOne(id);
    }

    /**
     * what:    根据给定的用户名获取用户并且附带用户的权限
     *
     * @param username
     *
     * @return
     */
    public User queryUserWithAuthoritiesByUsername(String username) {
        User user = userRepository.queryOneByUsername(username);
        List<Authority> authorities = roleAuthorityService.queryAllAuthoritiesByUserId(user.getId());
        user.setAuthorities(authorities);
        return user;
    }

    /**
     * what:    获取用户
     *
     * @return
     */
    public List<User> queryManyUsers(int pageNumber, int pageRowCount) {
        List<User> users = userRepository.queryMany(pageNumber, pageRowCount);
        return users;
    }

    /**
     * what:    获取给定角色对应的用户
     *
     * @param roleId
     *
     * @return
     */
    public List<User> queryAllUsersByRoleId(int roleId) {
        List<User> users = userRepository.queryAllByRoleId(roleId);
        return users;
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
    public int queryUserCount() {
        return userRepository.queryCount();
    }

    /**
     * what:    删除记录
     *
     * @param user
     *
     * @return
     *
     * @Author: 靳磊
     * @Date: 2017/10/18 13:19
     */
    @Transactional
    public int addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.insertOne(user);
    }

    /**
     * what:    更新
     *
     * @param user
     */
    @Transactional
    public int updateUser(User user) {
        return userRepository.updateOne(user);
    }

    /**
     * what:    删除给定id对应的记录
     *
     * @param id
     *
     * @return
     */
    @Transactional
    public int deleteUser(int id) {
        userRoleService.deleteUserRolesByUserId(id);
        return userRepository.deleteOne(id);
    }


    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public IUserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public UserRoleService getUserRoleService() {
        return userRoleService;
    }

    public void setUserRoleService(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }


}

