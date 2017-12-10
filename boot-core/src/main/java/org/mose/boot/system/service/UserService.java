package org.mose.boot.system.service;

import org.mose.boot.system.modal.User;
import org.mose.boot.system.repository.IUserRepository;
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
     * @param user
     */
    @Transactional
    public int updateUser(User user) {
        return userRepository.updateOne(user);
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
}
