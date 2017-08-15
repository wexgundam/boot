package org.mose.springboot.system.dao;

import org.mose.springboot.system.modal.Module;

import java.util.List;

/**
 * Description: 模块数据获取对象
 *
 * @Author: 靳磊
 * @Date: 2017/8/14:22
 */
public interface IModuleRepository {
    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    Module queryOne(int id);

    /**
     * 查询全部模块
     */
    List<Module> queryAll();

    /**
     * 插入一条记录
     *
     * @param module
     */
    Integer insertOne(Module module);

    /**
     * 更新一条记录
     *
     * @param module
     */
    int updateOne(Module module);

    /**
     * 删除一条记录
     *
     * @param module
     */
    int deleteOne(Module module);
}
