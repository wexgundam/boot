package org.mose.boot.system.service;

import org.mose.boot.system.modal.Role;
import org.mose.boot.system.modal.User;
import org.mose.boot.system.modal.UserRole;
import org.mose.boot.system.repository.IUserRepository;
import org.mose.boot.system.repository.IUserRoleRepository;
import org.mose.boot.util.code.ReturnCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Description: 用户服务
 *
 * @Author: 靳磊
 * @Date: 2017/8/18 14:43
 */
@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * 权限数据获取对象
     */
    @Autowired
    private IUserRepository userRepository;
    /**
     * 用户角色获取对象
     */
    @Autowired
    private IUserRoleRepository userRoleRepository;
    /**
     * 角色获取对象
     */
    @Autowired
    private RoleService roleService;
    public static final String ROLE_ID_ARRAY_STRING_SPLITTER = "@";

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
     * 获取所有场景并按照树形组织排序
     *
     * @return
     */
//    @Cacheable(value = "sysCache", key = "'userList'")
    public List<User> queryUserList(int pageNumber, int pageRowCount) {
        List<User> users = userRepository.queryMany(pageNumber, pageRowCount);
        return users;
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
    public int queryUserCount() {
        return userRepository.queryCount();
    }


    public Object queryRoleListByUserId(int userId) {
        return roleService.queryRoleListByUserId(userId);
    }

    ;

    /**
     * 查询给定用户的角色
     *
     * @param userId
     * @param pageNumber
     * @param pageRowCount
     *
     * @return
     */
    public List<Role> queryRoleListByUserId(int userId, int pageNumber, int pageRowCount) {
        return roleService.queryRoleListByUserId(userId, pageNumber, pageRowCount);
    }

    /**
     * 查询给定用户的角色数
     *
     * @param userId
     *
     * @return
     */
    public int queryRoleCountByUserId(int userId) {
        return roleService.queryRoleCountByUserId(userId);
    }


    /**
     * Description:删除记录
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
     * 更新
     *
     * @param user
     */
    @Transactional
    public int updateUser(User user) {
        return userRepository.updateOne(user);
    }

    /**
     * 更新
     *
     * @param userId
     */
    @Transactional
    public int updateUserRoles(int userId, String roleIdArrayString) {
        deleteUserRoles(userId);
        if (roleIdArrayString != null) {
            for (String roleIdString : roleIdArrayString.split(ROLE_ID_ARRAY_STRING_SPLITTER)) {
                UserRole userRole = new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(Integer.parseInt(roleIdString));
                userRoleRepository.insertOne(userRole);
            }
        }
        return ReturnCodeUtil.SUCCESS__UPDATE;
    }


    /**
     * 删除给定id对应的记录
     *
     * @param id
     *
     * @return
     */
    @Transactional
    public int deleteUser(int id) {
        return userRepository.deleteOne(id);
    }

    /**
     * 更新
     *
     * @param userId
     */
    @Transactional
    public int deleteUserRoles(int userId) {
        return userRoleRepository.deleteAllByUserId(userId);
    }

    @Transactional
    public int deleteUserRole(int userId, int roleId) {
        return userRoleRepository.deleteUserRole(userId, roleId);
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
}

