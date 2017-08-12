package org.mose.springboot.dao.stream;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.Assert;

/**
 * Description:
 *
 * @Author: 靳磊
 * @Date: 2017/8/11:20
 */
public class Delete<Entity, Id> {
    protected JdbcTemplate jdbcTemplate;
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    protected String sql;
    protected Object[] parameters;
    protected Object parameterBean;

    public Delete(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Delete<Entity, Id> sql(String sql) {
        this.sql = sql;
        return this;
    }

    public Delete<Entity, Id> parameters(Object... conditions) {
        this.parameters = conditions;
        return this;
    }

    public Delete<Entity, Id> parameterBean(Object conditionBean) {
        this.parameterBean = conditionBean;
        return this;
    }

    public Delete<Entity, Id> entity(Entity entity) {
        this.parameterBean = entity;
        return this;
    }

    public Delete<Entity, Id> id(Id id) {
        this.parameters = new Object[]{id};
        return this;
    }

    public int deleteOne() {
        Assert.notNull(sql, "The sql of deleteOne is null.");

        if (parameters != null) {
            return jdbcTemplate.update(sql, parameters);
        } else if (parameterBean != null) {
            return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(parameterBean));
        }

        return jdbcTemplate.update(sql);
    }

    public int deleteMany() {
        Assert.notNull(sql, "The sql of deleteMany is null.");

        if (parameters != null) {
            return jdbcTemplate.update(sql, parameters);
        } else if (parameterBean != null) {
            return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(parameterBean));
        }
        return jdbcTemplate.update(sql);
    }
}
