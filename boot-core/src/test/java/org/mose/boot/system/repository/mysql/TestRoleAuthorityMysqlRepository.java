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
import org.mose.boot.system.repository.IRoleAuthorityRepository;
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
public class TestRoleAuthorityMysqlRepository {
    @Autowired
    private IRoleAuthorityRepository roleAuthorityRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testRepository() {
        RoleAuthority roleAuthority = new RoleAuthority();
        int roleId = 2;
        int authorityId = 1;
        roleAuthority.setAuthorityId(authorityId);
        roleAuthority.setRoleId(roleId);
        int returnCode = roleAuthorityRepository.insertOne(roleAuthority);
        assertEquals(ReturnCodeUtil.SUCCESS__INSERT, returnCode);
        assertFalse(roleAuthority.getId() == 0);
        RoleAuthority queryOne = roleAuthorityRepository.queryOne(roleAuthority.getId());
        assertEquals(roleAuthority, queryOne);
        roleAuthorityRepository.queryManyAuthoritiesByRoleId(1, 1, 20);
        List<RoleAuthority> roleAuthoritys = roleAuthorityRepository.queryAllByAuthorityId(authorityId);
        assertNotNull(roleAuthoritys);
        roleAuthoritys = roleAuthorityRepository.queryAllByRoleId(roleId);
        assertNotNull(roleAuthoritys);
        roleAuthorityRepository.queryCountByRoleId(1);
        returnCode = roleAuthorityRepository.updateOne(roleAuthority);
        assertEquals(ReturnCodeUtil.SUCCESS__UPDATE, returnCode);
        returnCode = roleAuthorityRepository.deleteOne(roleAuthority);
        assertEquals(ReturnCodeUtil.SUCCESS__DELETE, returnCode);
        queryOne = roleAuthorityRepository.queryOne(roleAuthority.getId());
        assertNull(queryOne);
        roleAuthorityRepository.deleteAllByAuthorityId(authorityId);
        roleAuthorityRepository.deleteAllByRoleId(roleId);
        roleAuthorityRepository.queryManyAuthoritiesByRoleId(1, 1, 20);
        roleAuthorityRepository.queryAllAuthoritiesByRoleId(1);
        List<Authority> authorities = roleAuthorityRepository.queryManyAuthoritiesByUserId(3, 1, 20);
        System.out.println(authorities.size());
        roleAuthorityRepository.queryAllAuthoritiesByUserId(1);
        roleAuthorityRepository.queryCountByUserId(3);
        roleAuthorityRepository.queryAllRolesByAuthorityId(1);
    }
}
