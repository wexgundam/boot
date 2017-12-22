package org.mose.boot.common.dao.stream;

import org.mose.boot.common.dao.IPaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.Assert;

import java.util.List;

/**
 * what:   查询流式处理器
 * <p>
 * 采用流式编程方法，向Query对象注入参数，最后调用查询方法获取结果
 * <p>
 * 参数数组与参数bean为互斥，一次查询只能使用其中一种，以最后注入的为最终查询参数
 *
 * @Author: 靳磊
 * @Date: 2017/8/11:20
 */
public class Query<Entity, Id> {
    /**
     * Spring JdbcTemplate
     */
    @Autowired
    protected JdbcTemplate jdbcTemplate;
    /**
     * Spring namedParameterJdbcTemplate
     */
    @Autowired
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    /**
     * 分页处理器
     */
    @Autowired
    protected IPaging paging;
    /**
     * 查询语句
     */
    protected String sql;
    /**
     * 参数数组，与参数bean互斥
     */
    protected Object[] parameters;
    /**
     * 参数bean，与参数数组互斥
     */
    protected Object parameterBean;
    /**
     * 查询结果与实体Entity映射处理器
     */
    protected RowMapper<Entity> rowMapper = null;

    /**
     * what:   构造函数，传入的参数除rowMapper外均不可修改
     * <p>
     * 默认基于泛型Entity对应具体类型的BeanPropertyRowMapper，将查询结果映射为实体Entity
     *
     * @param jdbcTemplate
     * @param namedParameterJdbcTemplate
     * @param rowMapper
     * @param paging
     */
    public Query(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                 RowMapper<Entity> rowMapper, IPaging paging) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.rowMapper = rowMapper;
        this.paging = paging;
    }

    /**
     * what:   注入查询语句
     *
     * @param sql
     *
     * @return
     */
    public Query<Entity, Id> sql(String sql) {
        this.sql = sql;
        return this;
    }

    /**
     * what:   注入查询参数数组，同时将参数bean设为null
     *
     * @param parameters
     *
     * @return
     */
    public Query<Entity, Id> parameters(Object... parameters) {
        this.parameters = parameters;
        this.parameterBean = null;
        return this;
    }

    /**
     * what:   注入查询参数bean，同时将参数数组设为null
     *
     * @param parameterBean
     *
     * @return
     */
    public Query<Entity, Id> parameterBean(Object parameterBean) {
        this.parameterBean = parameterBean;
        this.parameters = null;
        return this;
    }

    /**
     * what:   注入将查询结果映射为实体Entity的RowMapper
     *
     * @param rowMapper
     *
     * @return
     */
    public Query<Entity, Id> rowMapper(RowMapper<Entity> rowMapper) {
        this.rowMapper = rowMapper;
        return this;
    }

    /**
     * what:    分页. <br/>
     *
     * @param pageNumber
     * @param pageRowCount
     *
     * @return
     *
     * @author 靳磊 created on 2017/12/22
     */
    public Query<Entity, Id> paging(int pageNumber, int pageRowCount) {
        this.sql = this.paging.paging(sql, pageNumber, pageRowCount);
        return this;
    }

    /**
     * what:    查询单个对象
     *
     * @return 返回查询到的Entity，如果没有对应记录则返回null
     */
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

    /**
     * what:    查询实体集合
     *
     * @return
     */
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

    /**
     * what:    查询行数
     *
     * @return
     */
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
