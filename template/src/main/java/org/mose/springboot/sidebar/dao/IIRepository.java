package org.mose.springboot.sidebar.dao;

import java.util.List;
import java.util.Map;

interface IIRepository<Entity, Id> {
    /**
     * 持久化对象名称
     *
     * @return
     */
    Class<Entity> getEntityClass();

    Class<Id> getIdClass();

    int count();

    int count(Object... conditions);

    int count(Object bean);

    /**
     * 查询总数，无参数
     *
     * @param sql
     * @return
     */
    int count(String sql);

    /**
     * 查询总数，带参数
     *
     * @param sql
     * @return
     */
    int count(String sql, Object... conditions);

    /**
     * 查询总数，带查询对象
     *
     * @param sql
     * @param entity
     * @return
     */
    int count(String sql, Entity entity);

    Entity queryOneById(Id id);

    Entity queryOne(Map<String, Object> input);

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