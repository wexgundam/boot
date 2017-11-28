package org.mose.springboot.system.dao;

import org.mose.springboot.system.modal.Scenario;

import java.util.List;

/**
 * Description: 场景数据获取对象
 *
 * @Author: 靳磊
 * @Date: 2017/8/14:22
 */
public interface IScenarioRepository {
    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    Scenario queryOne(int id);

    /**
     * 查询全部场景
     */
    List<Scenario> queryAll();

    /**
     * 插入一条记录
     *
     * @param scenario
     */
    int insertOne(Scenario scenario);

    /**
     * 更新一条记录
     *
     * @param scenario
     */
    int updateOne(Scenario scenario);

    /**
     * 删除一条记录
     *
     * @param scenario
     */
    int deleteOne(Scenario scenario);

    /**
     * 根据Id删除记录
     *
     * @param id
     * @return
     */
    int deleteOne(int id);

    /**
     * Description:查询给定id的场景的直接孩子总数
     *
     * @param id
     * @return
     *
     * @Author: 靳磊
     * @Date: 2017/10/18 13:26
     */
    int queryChildrenCount(int id);
}
