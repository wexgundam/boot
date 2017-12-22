package org.mose.boot.common.dao;

import org.springframework.util.Assert;

/**
 * what:    Mysql的分页处理器
 *
 * @Author: 靳磊
 * @Date: 2017/8/12:21
 */
public class MysqlPaging implements IPaging {
    @Override
    public String paging(String sql, int pageNumber, int pageRowCount) {
        Assert.notNull(sql, "The sql is null.");
        return sql + " limit " + (pageNumber - 1) * pageRowCount + "," + pageRowCount;
    }
}
