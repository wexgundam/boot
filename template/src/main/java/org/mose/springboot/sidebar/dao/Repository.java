/**
 * <copyright>
 * </copyright>
 * <p>
 * $Id$
 */
package org.mose.springboot.sidebar.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.lang.reflect.ParameterizedType;

public abstract class Repository<E, Id> implements IRepository<E, Id> {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private Class<E> entityClass;
    private Class<Id> idClass;

    @Override
    public Class<E> getEntityClass() {
        entityClass = entityClass == null ? ((Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]) : entityClass;
        return entityClass;
    }

    @Override
    public Class<Id> getIdClass() {
        idClass = idClass == null ? ((Class<Id>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1]) : idClass;
        return idClass;
    }

    public String getMapperName() {
        mapperName = mapperName == null ? getEntityClass().getName() + "Mapper" : mapperName;
        return mapperName;
    }

    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
    }

    @Override
    public E getOne(Map<String, Object> input) {
        return getJdbcTemplate().selectOne(getMapperName() + ".select", input);
    }

    @Override
    public E getOneById(String id) {
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("id", id);
        return getOne(input);
    }

    @Override
    public List<E> queryMany() {
        return getMany(null, null, null, null);
    }

    @Override
    public List<E> queryMany(Integer pages, Integer rows) {
        return getMany(null, pages, rows, null);
    }

    @Override
    public List<E> queryMany(String order) {
        return getMany(null, null, null, order);
    }

    @Override
    public List<E> queryMany(Integer pages, Integer rows, String order) {
        return getMany(null, pages, rows, order);
    }

    public List<E> queryMany(Map<String, Object> input) {
        return queryMany(input, null, null, null);
    }


    public List<E> queryMany(Map<String, Object> input, Integer pages, Integer rows) {
        return queryMany(input, pages, rows, null);
    }

    public List<E> queryMany(Map<String, Object> input, String order) {
        return queryMany(input, null, null, order);
    }


    public List<E> queryMany(Map<String, Object> input, Integer page, Integer rows, String order) {
        PageBounds pageBounds = new PageBounds();
        if (page != null && rows != null) {
            pageBounds.setPage(page);
            pageBounds.setLimit(rows);
            pageBounds.setContainsTotalCount(true);
        }
        if (order != null) {
            pageBounds.setOrders(com.github.miemiedev.mybatis.paginator.domain.Order.formString(order));
        }
        return getJdbcTemplate().selectList(getMapperName() + ".select", input, pageBounds);
    }

    @Override
    public int count() {
        return count((Map<String, Object>) null);
    }

    public int count(Map<String, Object> input) {
        return getJdbcTemplate().selectOne(getMapperName() + ".selectTotalCount", input);
    }

    @Override
    public void saveOne(E e) {
        getJdbcTemplate().insert(getMapperName() + ".insert", e);
        logger.info("插入实体:" + getEntityClass().getSimpleName() + "@" + e.getId());
    }

    @Override
    public void updateOne(E e) {
        getJdbcTemplate().update(getMapperName() + ".update", e);
        logger.info("更新实体:" + getEntityClass().getSimpleName() + "@" + e.getId());
    }

    @Override
    public void saveOrUpdateOne(E e) {
        if (getOneById(e.getId()) == null) {
            saveOne(e);
        } else {
            updateOne(e);
        }
    }

    @Override
    public void removeOne(E e) {
        removeOneById(e.getId());
    }

    @Override
    public void removeOneById(String id) {
        Assert.INSTANCE.isNull(id, getEntityClass());
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("id", id);
        getJdbcTemplate().delete(getMapperName() + ".delete", input);
        logger.info("删除实体:" + getEntityClass().getSimpleName() + "@" + id);
    }

    @Override
    public void remove(Map<String, Object> input) {
        getJdbcTemplate().delete(getMapperName() + ".delete", input);
        logger.info("删除实体:" + getEntityClass().getSimpleName());
    }

    public SqlSessionTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(SqlSessionTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
} // EntityRepositoryImpl
