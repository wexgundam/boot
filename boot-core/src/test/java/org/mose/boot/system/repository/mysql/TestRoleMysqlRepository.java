/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:boot
 * Module Name:TODO:Module
 */
package org.mose.boot.system.repository.mysql;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mose.boot.configuration.datasource.DruidConfiguration;
import org.mose.boot.system.modal.Role;
import org.mose.boot.system.modal.UserRole;
import org.mose.boot.system.repository.IRoleRepository;
import org.mose.boot.system.repository.IUserRoleRepository;
import org.mose.boot.test.ApplicationInitializer;
import org.mose.boot.util.code.ReturnCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
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
 * what:    测试RoleMysqlRepository.<br/>
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
public class TestRoleMysqlRepository {
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private IUserRoleRepository userRoleRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testRepository() {
        Role role = new Role();
        role.setName("name");
        int returnCode = roleRepository.insertOne(role);
        assertEquals(ReturnCodeUtil.SUCCESS__INSERT, returnCode);
        assertFalse(role.getId() == 0);
        Role queryOne = roleRepository.queryOne(role.getId());
        assertEquals(role, queryOne);
        List<Role> roles = roleRepository.queryAll();
        assertNotNull(roles);
        returnCode = roleRepository.updateOne(role);
        assertEquals(ReturnCodeUtil.SUCCESS__UPDATE, returnCode);
        returnCode = roleRepository.deleteOne(role);
        assertEquals(ReturnCodeUtil.SUCCESS__DELETE, returnCode);
        queryOne = roleRepository.queryOne(role.getId());
        assertNull(queryOne);
        roleRepository.queryAllByUserId(1);
    }
}