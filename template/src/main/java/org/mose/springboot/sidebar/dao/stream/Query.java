package org.mose.springboot.sidebar.dao.stream;

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

    public Query(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                 RowMapper<Entity> rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.rowMapper = rowMapper;
    }

    public Query<Entity, Id> sql(String sql) {
        this.sql = sql;
        return this;
    }

    public Query<Entity, Id> parameters(Object... parameters) {
        this.parameters = parameters;
        return this;
    }

    public Query<Entity, Id> parameterBean(Object conditionBean) {
        this.parameterBean = conditionBean;
        return this;
    }

    public Query<Entity, Id> rowMapper(RowMapper<Entity> rowMapper) {
        this.rowMapper = rowMapper;
        return this;
    }

    public Query<Entity, Id> id(Id id) {
        this.parameters = new Object[]{id};
        return this;
    }

    public Query<Entity, Id> page() {
        return this;
    }

    public Entity queryOne() {
        Assert.notNull(sql, "The sql of queryOne is null.");
        Assert.notNull(rowMapper, "The rowMapper of queryOne is null.");

        if (parameters != null) {
            return jdbcTemplate.queryForObject(sql, parameters, rowMapper);
        } else if (parameterBean != null) {
            return namedParameterJdbcTemplate.queryForObject(sql, new BeanPropertySqlParameterSource(parameterBean),
                    rowMapper);
        }

        return jdbcTemplate.queryForObject(sql, rowMapper);
    }

    public List<Entity> queryMany() {
        Assert.notNull(sql, "The sql of queryMany is null.");
        Assert.notNull(rowMapper, "The rowMapper of queryMany is null.");

        //TODO 分页

        if (parameters != null) {
            return jdbcTemplate.query(sql, parameters, rowMapper);
        } else if (parameterBean != null) {
            return namedParameterJdbcTemplate.query(sql, new BeanPropertySqlParameterSource(parameterBean), rowMapper);
        }
        return jdbcTemplate.query(sql, rowMapper);
    }

    public int queryCount() {
        Assert.notNull(sql, "The sql of queryCount is null.");
        Assert.notNull(rowMapper, "The rowMapper of queryCount is null.");

        if (parameters != null) {
            return jdbcTemplate.queryForObject(sql, parameters, Integer.class);
        } else if (parameterBean != null) {
            return namedParameterJdbcTemplate.queryForObject(sql, new BeanPropertySqlParameterSource(parameterBean),
                    Integer.class);
        }
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
