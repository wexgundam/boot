package org.mose.boot.common.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.Assert;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Description: 数据获取对象
 * <p>
 * 根据DDD领域驱动设计建议以及Spring注解使用Repository作为数据获取对象，即DAO
 * <p>
 * 该类支持泛型，其中Entity代表实体类，Id代表实体类的主键类型
 * <p>
 * 该类基于Spring JdbctTemplate实现，提供了CRUD以及分页等多种简单易用的方法，并支持参数数组与命名参数形式的Sql语句
 * <p>
 * 该类为抽象类，其中CRUD方法均为protected范围，各个实体的数据获取对象可继承该类，利用的CRUD方法，建立对应业务的数据获取方法
 *
 * @Author: 靳磊
 * @Date: 2017/8/12:15
 */

public abstract class AbstractRepository<Entity, Id> {
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
     * 获取实体类的Class
     *
     * @return
     */
    public Class<Entity> getEntityClass() {
        return ((Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    /**
     * 获取实体类的id的Class
     *
     * @return
     */
    public Class<Id> getIdClass() {
        return ((Class<Id>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    /**
     * 根据给定的sql语句与查询参数数组，查询单个实体
     * <p>
     * 查询结果通过给定的RowMapper映射为实体Entity
     *
     * @param rowMapper  查询结果与Entity的映射处理器
     * @param sql        查询语句
     * @param parameters 用于支持sql的参数数据，如果参数数据为null，则认为sql语句不包含参数
     * @return 返回查询到的Entity，如果没有对应记录则返回null
     */
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

    /**
     * 根据给定的sql语句与查询参数数组，查询单个实体
     * <p>
     * 查询结果基于泛型Entity对应具体类型的BeanPropertyRowMapper映射为实体Entity
     *
     * @param sql
     * @param parameters
     * @return
     */
    protected Entity queryOneByParameters(String sql, Object... parameters) {
        return queryOneByParameters(new BeanPropertyRowMapper<>(getEntityClass()), sql, parameters);
    }

    /**
     * 根据给定的sql语句与查询参数bean，查询单个实体
     * <p>
     * 查询结果通过给定的RowMapper映射为实体Entity
     *
     * @param rowMapper     查询结果与Entity的映射处理器
     * @param sql           查询语句
     * @param parameterBean 用于支持sql的参数bean，如果参数bean为null，则认为sql语句不包含命名参数
     * @return 返回查询到的Entity，如果没有对应记录则返回null
     */
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

    /**
     * 根据给定的sql语句与查询参数bean，查询单个实体
     * <p>
     * 查询结果基于泛型Entity对应具体类型的BeanPropertyRowMapper映射为实体Entity
     *
     * @param sql
     * @param parameterBean 用于支持sql的参数bean，如果参数bean为null，则认为sql语句不包含命名参数
     * @return
     */
    protected Entity queryOneByParameterBean(String sql, Object parameterBean) {
        return queryOneByParameterBean(new BeanPropertyRowMapper<>(getEntityClass()), sql, parameterBean);
    }

    /**
     * 根据给定的sql语句与查询参数数组，查询实体集合
     * <p>
     * 查询结果通过给定的RowMapper映射为实体Entity
     *
     * @param rowMapper  查询结果与Entity的映射处理器
     * @param sql        查询语句
     * @param parameters 用于支持sql的参数数据，如果参数数据为null，则认为sql语句不包含参数
     * @return 返回查询到的实体集合
     */
    protected List<Entity> queryManyByParameters(RowMapper<Entity> rowMapper, String sql, Object... parameters) {
        Assert.notNull(rowMapper, "The rowMapper of queryManyByParametersAndPaging is null.");
        Assert.notNull(sql, "The sql of queryManyByParametersAndPaging is null.");

        if (parameters == null) {
            return jdbcTemplate.query(sql, rowMapper);
        } else {
            return jdbcTemplate.query(sql, parameters, rowMapper);
        }
    }

    /**
     * 根据给定的sql语句与查询参数数组，查询实体集合
     * <p>
     * 查询结果基于泛型Entity对应具体类型的BeanPropertyRowMapper映射为实体Entity
     *
     * @param sql        查询语句
     * @param parameters 用于支持sql的参数数据，如果参数数据为null，则认为sql语句不包含参数
     * @return 返回查询到的实体集合
     */
    protected List<Entity> queryManyByParameters(String sql, Object... parameters) {
        return queryManyByParameters(new BeanPropertyRowMapper<>(getEntityClass()), sql, parameters);
    }

    /**
     * 根据给定的sql语句、分页参数与查询参数数组，查询实体集合
     * <p>
     * 查询结果通过给定的RowMapper映射为实体Entity
     *
     * @param rowMapper  查询结果与Entity的映射处理器
     * @param sql        查询语句
     * @param pageNumber 分页页码
     * @param pageSize   分页每页行数
     * @param parameters 用于支持sql的参数数据，如果参数数据为null，则认为sql语句不包含参数
     * @return 返回查询到的实体集合
     */
    protected List<Entity> queryManyByParametersAndPaging(RowMapper<Entity> rowMapper, String sql, int pageNumber,
                                                          int pageSize, Object... parameters) {
        sql = paging.doPaging(sql, pageNumber, pageSize);
        return queryManyByParameters(rowMapper, sql, parameters);
    }

    /**
     * 根据给定的sql语句、分页参数与查询参数数组，查询实体集合
     * <p>
     * 查询结果基于泛型Entity对应具体类型的BeanPropertyRowMapper映射为实体Entity
     *
     * @param sql        查询语句
     * @param pageNumber 分页页码
     * @param pageSize   分页每页行数
     * @param parameters 用于支持sql的参数数据，如果参数数据为null，则认为sql语句不包含参数
     * @return 返回查询到的实体集合
     */
    protected List<Entity> queryManyByParametersAndPaging(String sql, int pageNumber, int pageSize,
                                                          Object... parameters) {
        return queryManyByParametersAndPaging(new BeanPropertyRowMapper<>(getEntityClass()), sql, pageNumber, pageSize, parameters);
    }

    /**
     * 根据给定的sql语句与查询参数bean，查询实体集合
     * <p>
     * 查询结果通过给定的RowMapper映射为实体Entity
     *
     * @param rowMapper     查询结果与Entity的映射处理器
     * @param sql           查询语句
     * @param parameterBean 用于支持sql的参数bean，如果参数bean为null，则认为sql语句不包含命名参数
     * @return 返回查询到的实体集合
     */
    protected List<Entity> queryManyByParameterBean(RowMapper<Entity> rowMapper, String sql, Object parameterBean) {
        Assert.notNull(rowMapper, "The rowMapper of queryManyByParameterBeanAndPaging is null.");
        Assert.notNull(sql, "The sql of queryManyByParameterBeanAndPaging is null.");
        Assert.notNull(parameterBean, "The parameterBean of queryManyByParameterBeanAndPaging is null.");

        return namedParameterJdbcTemplate.query(sql, new BeanPropertySqlParameterSource(parameterBean), rowMapper);
    }

    /**
     * 根据给定的sql语句与查询参数bean，查询实体集合
     * <p>
     * 查询结果基于泛型Entity对应具体类型的BeanPropertyRowMapper映射为实体Entity
     *
     * @param sql           查询语句
     * @param parameterBean 用于支持sql的参数bean，如果参数bean为null，则认为sql语句不包含命名参数
     * @return 返回查询到的实体集合
     */
    protected List<Entity> queryManyByParameterBean(String sql, Object parameterBean) {
        return queryManyByParameterBean(new BeanPropertyRowMapper<>(getEntityClass()), sql, parameterBean);
    }

    /**
     * 根据给定的sql语句、分页参数与查询参数bean，查询实体集合
     * <p>
     * 查询结果通过给定的RowMapper映射为实体Entity
     *
     * @param rowMapper     查询结果与Entity的映射处理器
     * @param sql           查询语句
     * @param pageNumber    分页页码
     * @param pageSize      分页每页行数
     * @param parameterBean 用于支持sql的参数bean，如果参数bean为null，则认为sql语句不包含命名参数
     * @return 返回查询到的实体集合
     */
    protected List<Entity> queryManyByParameterBeanAndPaging(RowMapper<Entity> rowMapper, String sql, int pageNumber,
                                                             int pageSize, Object parameterBean) {
        sql = paging.doPaging(sql, pageNumber, pageSize);
        return queryManyByParameterBean(rowMapper, sql, parameterBean);
    }

    /**
     * 根据给定的sql语句、分页参数与查询参数bean，查询实体集合
     * <p>
     * 查询结果基于泛型Entity对应具体类型的BeanPropertyRowMapper映射为实体Entity
     *
     * @param sql           查询语句
     * @param pageNumber    分页页码
     * @param pageSize      分页每页行数
     * @param parameterBean 用于支持sql的参数bean，如果参数bean为null，则认为sql语句不包含命名参数
     * @return 返回查询到的实体集合
     */
    protected List<Entity> queryManyByParameterBeanAndPaging(String sql, int pageNumber, int pageSize,
                                                             Object parameterBean) {
        return queryManyByParameterBeanAndPaging(new BeanPropertyRowMapper<>(getEntityClass()), sql, pageNumber, pageSize, parameterBean);
    }

    /**
     * 根据给定的sql语句与查询参数数组，查询行数
     *
     * @param sql        查询语句
     * @param parameters 用于支持sql的参数数据，如果参数数据为null，则认为sql语句不包含参数
     * @return 返回查询到的实体集合
     */
    protected int queryCountByParameters(String sql, Object... parameters) {
        Assert.notNull(sql, "The sql of queryCount is null.");
        if (parameters == null) {
            return jdbcTemplate.queryForObject(sql, Integer.class);
        } else {
            return jdbcTemplate.queryForObject(sql, parameters, Integer.class);
        }
    }

    /**
     * 根据给定的sql语句与查询参数bean，查询行数
     *
     * @param sql           查询语句
     * @param parameterBean 用于支持sql的参数bean，如果参数bean为null，则认为sql语句不包含命名参数
     * @return 返回查询到的实体集合
     */
    protected int queryCountByParameterBean(String sql, Object parameterBean) {
        Assert.notNull(sql, "The sql of queryCountByParameterBean is null.");
        Assert.notNull(parameterBean, "The parameterBean of queryCountByParameterBean is null.");

        return namedParameterJdbcTemplate.queryForObject(sql, new BeanPropertySqlParameterSource(parameterBean), Integer.class);
    }

    /**
     * 根据给定sql语句，以entity为bean，插入数据
     *
     * @param sql
     * @param entity
     * @return 插入数据为true，否则为false
     */
    protected boolean insertOne(String sql, Entity entity) {
        Assert.notNull(sql, "The sql of insertOne is null.");
        Assert.notNull(entity, "The entity of insertOne is null.");

        int resultCount = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(entity));
        return resultCount == 1 ? true : false;
    }

    /**
     * 当实体主键有数据库自动生成时，根据给定sql语句，以entity为bean，插入数据，并获得自动生成的主键的值
     *
     * @param sql
     * @param entity
     * @param idColumnName 主键对应的列名
     * @return
     */
    protected Number insertOneForAutoGeneratedId(String sql, Entity entity, String idColumnName) {
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

    /**
     * 根据给定的sql语句与参数数组更新数据
     *
     * @param sql
     * @param parameters 用于支持sql的参数数据，如果参数数据为null，则认为sql语句不包含参数
     * @return 更新的行数
     */
    protected int updateAnyByParamters(String sql, Object... parameters) {
        Assert.notNull(sql, "The sql of updateAnyByParamters is null.");

        if (parameters == null) {
            return jdbcTemplate.update(sql);
        } else {
            return jdbcTemplate.update(sql, parameters);
        }
    }

    /**
     * 根据给定的sql语句与参数bean更新数据
     *
     * @param sql
     * @param parameterBean 用于支持sql的参数数据，如果参数bean为null，则认为sql语句不包含参数
     * @return 更新的行数
     */
    protected int updateAnyByParameterBean(String sql, Object parameterBean) {
        Assert.notNull(sql, "The sql of updateAnyByParameterBean is null.");
        Assert.notNull(parameterBean, "The entity of updateAnyByParameterBean is null.");

        return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(parameterBean));
    }

    /**
     * 根据给定的sql语句与参数数组删除数据
     *
     * @param sql
     * @param parameters 用于支持sql的参数数据，如果参数数据为null，则认为sql语句不包含参数
     * @return 删除的行数
     */
    protected int deleteAnyByParameters(String sql, Object... parameters) {
        Assert.notNull(sql, "The sql of deleteAnyByParameters is null.");

        if (parameters == null) {
            return jdbcTemplate.update(sql);
        } else {
            return jdbcTemplate.update(sql, parameters);
        }
    }

    /**
     * 根据给定的sql语句与参数bean删除数据
     *
     * @param sql
     * @param parameterBean 用于支持sql的参数数据，如果参数bean为null，则认为sql语句不包含参数
     * @return 删除的行数
     */
    protected int deleteAnyByParameterBean(String sql, Object parameterBean) {
        Assert.notNull(sql, "The sql of deleteAnyByParameterBean is null.");
        Assert.notNull(parameterBean, "The parameterBean of deleteAnyByParameterBean is null.");

        return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(parameterBean));
    }

}
