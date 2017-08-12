package org.mose.springboot.sidebar.dao.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.lang.reflect.ParameterizedType;

/**
 * Description: 流式编程数据持久化抽象类
 *
 * @Author: 靳磊
 * @Date: 2017/8/11:21
 */
public class AbstractStreamRepository<Entity, Id> {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Class<Entity> getEntityClass() {
        return ((Class<Entity>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    public Class<Id> getIdClass() {
        return ((Class<Id>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    public Query<Entity, Id> query() {
        return new Query<>(jdbcTemplate, namedParameterJdbcTemplate, new BeanPropertyRowMapper<>(getEntityClass()));
    }

    public Insert<Entity, Id> insert() {
        return new Insert<>(jdbcTemplate, namedParameterJdbcTemplate);
    }

    public Update<Entity, Id> update() {
        return new Update<>(jdbcTemplate, namedParameterJdbcTemplate);
    }

    public Delete<Entity, Id> delete() {
        return new Delete<>(jdbcTemplate, namedParameterJdbcTemplate);
    }
}
