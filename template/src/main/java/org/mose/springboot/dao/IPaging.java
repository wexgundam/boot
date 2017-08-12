package org.mose.springboot.dao;

/**
 * Description:
 *
 * @Author: 靳磊
 * @Date: 2017/8/12:21
 */
public interface IPaging {
    String doPaging(String sql, int pageNumber, int pageSize);
}
