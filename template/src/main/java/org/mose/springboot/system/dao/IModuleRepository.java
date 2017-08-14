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
     * 查询全部模块
     */
    List<Module> queryAll();
}
