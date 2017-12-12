/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:boot
 * Module Name:TODO:Module
 */
package org.mose.boot.system.repository.mysql;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mose.boot.configuration.datasource.DruidConfiguration;
import org.mose.boot.system.modal.User;
import org.mose.boot.system.modal.UserRole;
import org.mose.boot.system.repository.IUserRoleRepository;
import org.mose.boot.test.ApplicationInitializer;
import org.mose.boot.util.code.ReturnCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * what:    测试UserMysqlRepository.<br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author 靳磊 created on 2017/12/2
 */
@SpringBootTest(classes = ApplicationInitializer.class)
@Import(DruidConfiguration.class)
@ComponentScan("org.mose.boot.system.repository")
@ActiveProfiles({"mysql"})
@RunWith(SpringRunner.class)
@Transactional
public class TestUserRoleMysqlRepository {
    @Autowired
    private IUserRoleRepository userRoleRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testRepository() {
        UserRole userRole = new UserRole();
        int userId = 1;
        int roleId = 2;
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        int returnCode = userRoleRepository.insertOne(userRole);
        assertEquals(ReturnCodeUtil.SUCCESS__INSERT, returnCode);
        assertFalse(userRole.getId() == 0);
        UserRole queryOne = userRoleRepository.queryOne(userRole.getId());
        assertEquals(userRole, queryOne);
        List<UserRole> userRoles = userRoleRepository.queryAllByUserId(userId);
        assertNotNull(userRoles);
        userRoles = userRoleRepository.queryAllByRoleId(roleId);
        assertNotNull(userRoles);
        userRoleRepository.queryCountByUserId(1);
        returnCode = userRoleRepository.updateOne(userRole);
        assertEquals(ReturnCodeUtil.SUCCESS__UPDATE, returnCode);
        returnCode = userRoleRepository.deleteOne(userRole);
        assertEquals(ReturnCodeUtil.SUCCESS__DELETE, returnCode);
        queryOne = userRoleRepository.queryOne(userRole.getId());
        assertNull(queryOne);
        userRoleRepository.deleteOne(1, 2);
        userRoleRepository.deleteAllByUserId(userId);
        userRoleRepository.deleteAllByRoleId(roleId);
        userRoleRepository.queryManyRolesByUserId(1, 1, 20);
        userRoleRepository.queryAllRolesByUserId(1);
    }
}
