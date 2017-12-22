package org.mose.boot.common.dao;

/**
 * what:    分页处理器. <br/>
 * warning: 不同类型的数据的分页语句均不一样，该接口屏蔽了数据库的差异，为Sql补充分页处理.<br/>
 *
 * @author 靳磊 created on 2017/12/22
 */
public interface IPaging {
    /**
     * what:    为sql语句补充分页语句
     *
     * @param sql          查询语句
     * @param pageNumber   页码
     * @param pageRowCount 每页行数
     *
     * @return
     */
    String paging(String sql, int pageNumber, int pageRowCount);
}
