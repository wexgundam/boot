package org.mose.springboot.dao.stream;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.Assert;

/**
 * Description:
 *
 * @Author: 靳磊
 * @Date: 2017/8/11:20
 */
public class Insert<Entity, Id> {
    protected JdbcTemplate jdbcTemplate;
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    protected String sql;
    protected Entity entity;
    protected String idColumnName;

    public Insert(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Insert<Entity, Id> sql(String sql) {
        this.sql = sql;
        return this;
    }

    public Insert<Entity, Id> entity(Entity entity) {
        this.entity = entity;
        return this;
    }

    public Insert<Entity, Id> idColumnName(String idColumnName) {
        this.idColumnName = idColumnName;
        return this;
    }

    public int insertOne() {
        Assert.notNull(sql, "The sql of insertOne is null.");
        Assert.notNull(entity, "The entity of insertOne is null.");

        return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(entity));
    }

    public Number insertOneForAutoGeneratedId() {
        Assert.notNull(sql, "The sql of insertOneForAutoGeneratedPk is null.");
        Assert.notNull(entity, "The entity of insertOneForAutoGeneratedPk is null.");
        Assert.notNull(idColumnName, "The idColumnName of insertOneForAutoGeneratedPk is null.");

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int resultCount = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(entity),
                keyHolder, new String[]{idColumnName});
        if (resultCount > 0) {
            return keyHolder.getKey();
        } else {
            return null;
        }
    }
}
