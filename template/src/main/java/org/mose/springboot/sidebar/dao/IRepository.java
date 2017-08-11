package org.mose.springboot.sidebar.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;

import java.util.List;
import java.util.Map;

interface IRepository<Entity, Id> {
    /**
     * 持久化对象名称
     *
     * @return
     */
    Class<Entity> getEntityClass();

    Class<Id> getIdClass();

    /**
     * 查询总数，无参数
     *
     * @return
     */
    int count();

    /**
     * 查询总数，带参数
     *
     * @param conditions
     * @return
     */
    int count(Object... conditions);

    /**
     * 查询总数，bean
     *
     * @param bean
     * @return
     */
    int count(Object bean);

    /**
     * 根据参数查询实体
     *
     * @param conditions
     * @return
     */
    Entity queryOne(Object... conditions);

    /**
     * 根据Id查询实体
     *
     * @param id
     * @return
     */
    Entity queryOneById(Id id);

    List<Entity> queryMany();

    /**
     * 根据sql和多参数获取列表
     *
     * @param conditions
     * @return
     */
    List<Entity> queryMany(Object... conditions);

    /**
     * 根据查询条件获取列表
     *
     * @param bean
     * @return
     */
    List<Entity> queryMany(Object bean);

    /**
     * 下拉框列表
     *
     * @param sql
     * @return
     */
    <Bean> Bean queryMany(String sql, Class<Bean> clazz);

    /**
     * 下拉框列表
     *
     * @param sql
     * @return
     */
    <Bean> Bean queryMany(String sql, Class<Bean> clazz, Object... objects);

    List<Entity> queryMany();

    List<Entity> queryMany(Integer pages, Integer rows);

    List<Entity> queryMany(String order);

    List<Entity> queryMany(Integer pages, Integer rows, String order);

    List<Entity> queryMany(Map<String, Object> input);

    List<Entity> queryMany(Map<String, Object> input, Integer pages, Integer rows);

    List<Entity> queryMany(Map<String, Object> input, String order);

    List<Entity> queryMany(Map<String, Object> input, Integer pages, Integer rows, String order);

    void saveOne(Entity entity);

    void updateOne(Entity entity);

    void saveOrUpdateOne(Entity entity);

    void removeOne(Entity entity);

    void removeOneById(Id id);

    void remove(Map<String, Object> input);
}