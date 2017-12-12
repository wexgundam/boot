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

    @Test
    public void contextLoads() {
    }

    @Test
    public void testRepository() {
        Role role = new Role();
        role.setName("name");
        role.setDescription("description");
        int returnCode = roleRepository.insertOne(role);
        assertEquals(ReturnCodeUtil.SUCCESS__INSERT, returnCode);
        assertFalse(role.getId() == 0);
        Role queryOne = roleRepository.queryOne(role.getId());
        assertEquals(role, queryOne);
        roleRepository.queryExistByName(role.getName());
        roleRepository.queryMany(1, 20);
        roleRepository.queryMany("select id from t_system_role where id=?", new Object[]{1}, 1, 20);
        roleRepository.queryAll("select id from t_system_role where id=?", new Object[]{1});
        List<Role> roles = roleRepository.queryAll();
        assertNotNull(roles);
        roleRepository.queryCount();
        returnCode = roleRepository.updateOne(role);
        assertEquals(ReturnCodeUtil.SUCCESS__UPDATE, returnCode);
        returnCode = roleRepository.deleteOne(role);
        assertEquals(ReturnCodeUtil.SUCCESS__DELETE, returnCode);
        queryOne = roleRepository.queryOne(role.getId());
        assertNull(queryOne);
    }
}
