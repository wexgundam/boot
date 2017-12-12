package org.mose.boot.system.service;

import org.mose.boot.system.modal.Role;
import org.mose.boot.system.modal.UserRole;
import org.mose.boot.system.repository.IUserRoleRepository;
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
public class UserRoleService {
    public static final String ROLE_ID_ARRAY_STRING_SPLITTER = "@";
    /**
     * 角色数据获取对象
     */
    @Autowired
    private IUserRoleRepository userRoleRepository;

    /**
     * 查询给定用户的角色
     *
     * @param userId
     * @param pageNumber
     * @param pageRowCount
     *
     * @return
     */
    public List<Role> queryManyRolesByUserId(int userId, int pageNumber, int pageRowCount) {
        List<Role> roles = userRoleRepository.queryManyRolesByUserId(userId, pageNumber, pageRowCount);
        return roles;
    }

    /**
     * 查询给定用户的角色
     *
     * @param userId
     *
     * @return
     */
    public List<Role> queryAllRolesByUserId(int userId) {
        List<Role> roles = userRoleRepository.queryAllRolesByUserId(userId);
        return roles;
    }

    /**
     * 查询给定用户的角色总数
     *
     * @param userId
     *
     * @return
     */
    public int queryRoleCountByUserId(int userId) {
        return userRoleRepository.queryCountByUserId(userId);
    }

    /**
     * 更新
     *
     * @param userId
     */
    @Transactional
    public int updateUserRoles(int userId, String roleIdArrayString) {
        deleteUserRolesByUserId(userId);
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

    @Transactional
    public int deleteUserRole(int userId, int roleId) {
        return userRoleRepository.deleteOne(userId, roleId);
    }

    /**
     * 更新
     *
     * @param userId
     */
    @Transactional
    public int deleteUserRolesByUserId(int userId) {
        return userRoleRepository.deleteAllByUserId(userId);
    }


    public int deleteUserRolesByRoleId(int roleId) {
        return userRoleRepository.deleteAllByRoleId(roleId);
    }


    public IUserRoleRepository getUserRoleRepository() {
        return userRoleRepository;
    }

    public void setUserRoleRepository(IUserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

}
