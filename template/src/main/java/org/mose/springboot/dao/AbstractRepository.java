package org.mose.springboot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.Assert;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Description:
 *
 * @Author: 靳磊
 * @Date: 2017/8/12:15
 */
public abstract class AbstractRepository<Entity, Id> {
    @Autowired
    protected JdbcTemplate jdbcTemplate;
    @Autowired
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    protected IPaging paging;

    public Class<Entity> getEntityClass() {
        return ((Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    public Class<Id> getIdClass() {
        return ((Class<Id>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    protected Entity queryOneByParameters(RowMapper<Entity> rowMapper, String sql, Object... parameters) {
        Assert.notNull(rowMapper, "The rowMapper of queryOneByParameters is null.");
        Assert.notNull(sql, "The sql of queryOneByParameters is null.");

        try {
            if (parameters == null) {
                return jdbcTemplate.queryForObject(sql, rowMapper);
            } else {
                return jdbcTemplate.queryForObject(sql, parameters, rowMapper);
            }
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    protected Entity queryOneByParameters(String sql, Object... parameters) {
        return queryOneByParameters(new BeanPropertyRowMapper<>(getEntityClass()), sql, parameters);
    }

    protected Entity queryOneByParameterBean(RowMapper<Entity> rowMapper, String sql, Object parameterBean) {
        Assert.notNull(rowMapper, "The rowMapper of queryOneByParameterBean is null.");
        Assert.notNull(sql, "The sql of queryOneByParameterBean is null.");
        Assert.notNull(parameterBean, "The parameterBean of queryOneByParameters is null.");

        try {
            return namedParameterJdbcTemplate.queryForObject(sql, new BeanPropertySqlParameterSource(parameterBean), rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    protected Entity queryOneByParameterBean(String sql, Object parameterBean) {
        return queryOneByParameterBean(new BeanPropertyRowMapper<>(getEntityClass()), sql, parameterBean);
    }

    protected List<Entity> queryManyByParameters(RowMapper<Entity> rowMapper, String sql, Object... parameters) {
        Assert.notNull(rowMapper, "The rowMapper of queryManyByParametersAndPaging is null.");
        Assert.notNull(sql, "The sql of queryManyByParametersAndPaging is null.");

        if (parameters == null) {
            return jdbcTemplate.query(sql, rowMapper);
        } else {
            return jdbcTemplate.query(sql, parameters, rowMapper);
        }
    }

    protected List<Entity> queryManyByParameters(String sql, Object... parameters) {
        return queryManyByParameters(new BeanPropertyRowMapper<>(getEntityClass()), sql, parameters);
    }

    protected List<Entity> queryManyByParametersAndPaging(RowMapper<Entity> rowMapper, String sql, int pageNumber,
                                                          int pageSize, Object... parameters) {
        sql = paging.doPaging(sql, pageNumber, pageSize);
        return queryManyByParameters(rowMapper, sql, parameters);
    }

    protected List<Entity> queryManyByParametersAndPaging(String sql, int pageNumber, int pageSize,
                                                          Object... parameters) {
        return queryManyByParametersAndPaging(new BeanPropertyRowMapper<>(getEntityClass()), sql, pageNumber, pageSize, parameters);
    }

    protected List<Entity> queryManyByParameterBean(RowMapper<Entity> rowMapper, String sql, Object parameterBean) {
        Assert.notNull(rowMapper, "The rowMapper of queryManyByParameterBeanAndPaging is null.");
        Assert.notNull(sql, "The sql of queryManyByParameterBeanAndPaging is null.");
        Assert.notNull(parameterBean, "The parameterBean of queryManyByParameterBeanAndPaging is null.");

        return namedParameterJdbcTemplate.query(sql, new BeanPropertySqlParameterSource(parameterBean), rowMapper);
    }

    protected List<Entity> queryManyByParameterBean(String sql, Object parameterBean) {
        return queryManyByParameterBean(new BeanPropertyRowMapper<>(getEntityClass()), sql, parameterBean);
    }

    protected List<Entity> queryManyByParameterBeanAndPaging(RowMapper<Entity> rowMapper, String sql, int pageNumber,
                                                             int pageSize, Object parameterBean) {
        sql = paging.doPaging(sql, pageNumber, pageSize);
        return queryManyByParameterBean(rowMapper, sql, parameterBean);
    }

    protected List<Entity> queryManyByParameterBeanAndPaging(String sql, int pageNumber, int pageSize,
                                                             Object parameterBean) {
        return queryManyByParameterBeanAndPaging(new BeanPropertyRowMapper<>(getEntityClass()), sql, pageNumber, pageSize, parameterBean);
    }

    protected int queryCountByParameters(String sql, Object... parameters) {
        Assert.notNull(sql, "The sql of queryCount is null.");
        if (parameters == null) {
            return jdbcTemplate.queryForObject(sql, Integer.class);
        } else {
            return jdbcTemplate.queryForObject(sql, parameters, Integer.class);
        }
    }

    protected int queryCountByParameterBean(String sql, Object parameterBean) {
        Assert.notNull(sql, "The sql of queryCountByParameterBean is null.");
        Assert.notNull(parameterBean, "The parameterBean of queryCountByParameterBean is null.");

        return namedParameterJdbcTemplate.queryForObject(sql, new BeanPropertySqlParameterSource(parameterBean), Integer.class);
    }
}
