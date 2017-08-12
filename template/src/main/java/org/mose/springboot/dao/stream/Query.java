package org.mose.springboot.dao.stream;

import org.mose.springboot.dao.IPaging;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Description:
 *
 * @Author: 靳磊
 * @Date: 2017/8/11:20
 */
public class Query<Entity, Id> {
    protected JdbcTemplate jdbcTemplate;
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    protected String sql;
    protected Object[] parameters;
    protected Object parameterBean;
    protected RowMapper<Entity> rowMapper = null;
    protected IPaging paging;

    public Query(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                 RowMapper<Entity> rowMapper, IPaging paging) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.rowMapper = rowMapper;
        this.paging = paging;
    }

    public Query<Entity, Id> sql(String sql) {
        this.sql = sql;
        return this;
    }

    public Query<Entity, Id> parameters(Object... parameters) {
        this.parameters = parameters;
        this.parameterBean = null;
        return this;
    }

    public Query<Entity, Id> parameterBean(Object parameterBean) {
        this.parameterBean = parameterBean;
        this.parameters = null;
        return this;
    }

    public Query<Entity, Id> rowMapper(RowMapper<Entity> rowMapper) {
        this.rowMapper = rowMapper;
        return this;
    }

    public Query<Entity, Id> paging(int pageNumber, int pageSize) {
        Assert.notNull(sql, "The sql of queryMany is null.");
        sql = paging.doPaging(sql, pageNumber, pageSize);
        return this;
    }

    public Entity queryOne() {
        Assert.notNull(sql, "The sql of queryOne is null.");
        Assert.notNull(rowMapper, "The rowMapper of queryOne is null.");

        try {
            if (parameters != null) {
                return jdbcTemplate.queryForObject(sql, parameters, rowMapper);
            } else if (parameterBean != null) {
                Assert.notNull(parameterBean, "The parameterBean of queryOne is null.");
                return namedParameterJdbcTemplate.queryForObject(sql, new BeanPropertySqlParameterSource(parameterBean), rowMapper);
            }

            return jdbcTemplate.queryForObject(sql, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Entity> queryMany() {
        Assert.notNull(sql, "The sql of queryMany is null.");
        Assert.notNull(rowMapper, "The rowMapper of queryMany is null.");

        if (parameters != null) {
            return jdbcTemplate.query(sql, parameters, rowMapper);
        } else if (parameterBean != null) {
            Assert.notNull(parameterBean, "The parameterBean of queryMany is null.");
            return namedParameterJdbcTemplate.query(sql, new BeanPropertySqlParameterSource(parameterBean), rowMapper);
        }
        return jdbcTemplate.query(sql, rowMapper);
    }

    public int queryCount() {
        Assert.notNull(sql, "The sql of queryCount is null.");

        if (parameters != null) {
            return jdbcTemplate.queryForObject(sql, parameters, Integer.class);
        } else if (parameterBean != null) {
            Assert.notNull(parameterBean, "The parameterBean of queryCount is null.");
            return namedParameterJdbcTemplate.queryForObject(sql, new BeanPropertySqlParameterSource(parameterBean), Integer.class);
        }
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
