package org.mose.springboot.sidebar.dao.stream;

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
public class Update<Entity, Id> {
    protected JdbcTemplate jdbcTemplate;
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    protected String sql;
    protected Object[] parameters;
    protected Object parameterBean;

    public Update(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    public Update<Entity, Id> sql(String sql) {
        this.sql = sql;
        return this;
    }

    public Update<Entity, Id> parameters(Object... conditions) {
        this.parameters = conditions;
        return this;
    }

    public Update<Entity, Id> parameterBean(Object conditionBean) {
        this.parameterBean = conditionBean;
        return this;
    }

    public Update<Entity, Id> entity(Entity entity) {
        this.parameterBean = entity;
        return this;
    }

    public int updateOne() {
        Assert.notNull(sql, "The sql of updateOne is null.");
        Assert.notNull(parameterBean, "The entity of updateOne is null.");

        return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(parameterBean));
    }

    public int updateMany() {
        Assert.notNull(sql, "The sql of updateMany is null.");

        if (parameters != null) {
            return jdbcTemplate.update(sql, parameters);
        } else if (parameterBean != null) {
            return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(parameterBean));
        }
        return jdbcTemplate.update(sql);
    }
}
