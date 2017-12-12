/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:boot
 * Module Name:TODO:Module
 */
package org.mose.boot.system.repository.mysql;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mose.boot.configuration.datasource.DruidConfiguration;
import org.mose.boot.system.modal.Authority;
import org.mose.boot.system.modal.RoleAuthority;
import org.mose.boot.system.modal.UserRole;
import org.mose.boot.system.repository.IAuthorityRepository;
import org.mose.boot.system.repository.IRoleAuthorityRepository;
import org.mose.boot.system.repository.IRoleRepository;
import org.mose.boot.system.repository.IUserRepository;
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
 * what:    测试AuthorityMysqlRepository.<br/>
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
public class TestAuthorityMysqlRepository {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IUserRoleRepository userRoleRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private IAuthorityRepository authorityRepository;
    @Autowired
    private IRoleAuthorityRepository roleAuthorityRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testRepository() {
        Authority authority = new Authority();
        authority.setName("name");
        authority.setDescription("description");
        int returnCode = authorityRepository.insertOne(authority);
        assertEquals(ReturnCodeUtil.SUCCESS__INSERT, returnCode);
        assertFalse(authority.getId() == 0);
        Authority queryOne = authorityRepository.queryOne(authority.getId());
        assertEquals(authority, queryOne);
        authorityRepository.queryExistByName(authority.getName());
        List<Authority> authoritys = authorityRepository.queryAll();
        assertNotNull(authoritys);
        authorityRepository.queryAll("select id from t_system_authority where id=?", new Object[]{1});
        returnCode = authorityRepository.updateOne(authority);
        assertEquals(ReturnCodeUtil.SUCCESS__UPDATE, returnCode);
        returnCode = authorityRepository.deleteOne(authority);
        assertEquals(ReturnCodeUtil.SUCCESS__DELETE, returnCode);
        queryOne = authorityRepository.queryOne(authority.getId());
        assertNull(queryOne);
    }
}
