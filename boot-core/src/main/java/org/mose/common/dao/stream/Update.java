package org.mose.common.dao.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.Assert;

/**
 * what:    更新流式处理器
 * <p>
 * 采用流式编程方法，向Update对象注入参数，最后调用更新方法获取结果
 * <p>
 * 参数数组与参数bean为互斥，一次更新只能使用其中一种，以最后注入的为最终更新参数
 *
 * @Author: 靳磊
 * @Date: 2017/8/11:20
 */
public class Update<Entity, Id> {
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
     * what:    构造函数，传入的参数均不可修改
     *
     * @param jdbcTemplate
     * @param namedParameterJdbcTemplate
     */
    public Update(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * what:    注入更新语句
     *
     * @param sql
     * @return
     */
    public Update<Entity, Id> sql(String sql) {
        this.sql = sql;
        return this;
    }

    /**
     * what:    注入更新参数数组，同时将参数bean设为null
     *
     * @param parameters
     * @return
     */
    public Update<Entity, Id> parameters(Object... parameters) {
        this.parameters = parameters;
        this.parameterBean = null;
        return this;
    }

    /**
     * what:    注入更新参数bean，同时将参数数组设为null
     *
     * @param parameterBean
     * @return
     */
    public Update<Entity, Id> parameterBean(Object parameterBean) {
        this.parameterBean = parameterBean;
        this.parameters = null;
        return this;
    }

    /**
     * what:    更新数据
     *
     * @return 更新的行数
     */
    public int updateAny() {
        Assert.notNull(sql, "The sql of updateAny is null.");

        if (parameters != null) {
            return jdbcTemplate.update(sql, parameters);
        } else if (parameterBean != null) {
            return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(parameterBean));
        }
        return jdbcTemplate.update(sql);
    }
}
