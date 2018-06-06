package org.mose.common.dao.stream;

import org.mose.common.dao.IPaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * what:   流式编程数据获取对象
 * <p>
 * 根据DDD领域驱动设计建议以及Spring注解使用Repository作为数据获取对象，即DAO
 * <p>
 * 该类支持泛型，其中Entity代表实体类，Id代表实体类的主键类型
 * <p>
 * 该类基于Spring JdbctTemplate实现，提供了CRUD以及分页等多种简单易用的方法，并支持参数数组与命名参数形式的Sql语句
 * <p>
 * 该类为抽象类，其中CRUD方法均为protected范围，各个实体的数据获取对象可继承该类，利用的CRUD方法，建立对应业务的数据获取方法
 * <p>
 * 该类采用流式编程方式，分别提供了Query、Insert、Update、Delete四个对象，用于处理CRUD操作，减少了重载方法，调用过程清晰简单
 *
 * @Author: 靳磊
 * @Date: 2017/8/11:21
 */
public abstract class AbstractStreamRepository<Entity, Id> {
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
     * what:   获取实体类的Class
     *
     * @return
     */
    public Class<Entity> getEntityClass() {
        return ((Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    /**
     * what:   获取实体类的id的Class
     *
     * @return
     */
    public Class<Id> getIdClass() {
        return ((Class<Id>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1]);
    }

    /**
     * what:   返回一个Query对象用于处理查询操作
     * <p>
     * Query默认基于泛型Entity对应具体类型的BeanPropertyRowMapper，将查询结果映射为实体Entity
     * <p>
     * Query使用的Repository的分页处理器
     *
     * @return
     */
    protected Query<Entity, Id> query() {
        return new Query<>(jdbcTemplate, namedParameterJdbcTemplate, new BeanPropertyRowMapper<>(getEntityClass()), paging);
    }

    /**
     * what:   给定sql语句与参数查询
     *
     * @param sql
     * @param parameters
     * @param pageNumber
     * @param pageRowCount
     *
     * @return
     */
    public List<Entity> queryMany(String sql, int pageNumber, int pageRowCount, Object... parameters) {
        return query().sql(sql).parameters(parameters).paging(pageNumber, pageRowCount).queryMany();
    }

    /**
     * what:   给定sql语句与参数查询
     *
     * @param sql
     * @param parameterBean
     * @param pageNumber
     * @param pageRowCount
     *
     * @return
     */
    public List<Entity> queryMany(String sql, Object parameterBean, int pageNumber, int pageRowCount) {
        return query().sql(sql).parameterBean(parameterBean).paging(pageNumber, pageRowCount).queryMany();
    }

    /**
     * what:   给定sql语句与参数查询
     *
     * @param sql
     * @param parameters
     *
     * @return
     */
    public List<Entity> queryAll(String sql, Object... parameters) {
        return query().sql(sql).parameters(parameters).queryMany();
    }

    /**
     * what:   给定sql语句与参数查询
     *
     * @param sql
     * @param parameterBean
     *
     * @return
     */
    public List<Entity> queryAll(String sql, Object parameterBean) {
        return query().sql(sql).parameterBean(parameterBean).queryMany();
    }

    /**
     * what:   返回一个Insert对象用于处理插入操作
     *
     * @return
     */
    protected Insert<Entity, Id> insert() {
        return new Insert<>(jdbcTemplate, namedParameterJdbcTemplate);
    }

    /**
     * what:   返回一个Update对象用于处理更新操作
     *
     * @return
     */
    protected Update<Entity, Id> update() {
        return new Update<>(jdbcTemplate, namedParameterJdbcTemplate);
    }

    /**
     * what:   返回一个Delete对象用于处理删除操作
     *
     * @return
     */
    protected Delete<Entity, Id> delete() {
        return new Delete<>(jdbcTemplate, namedParameterJdbcTemplate);
    }
}
