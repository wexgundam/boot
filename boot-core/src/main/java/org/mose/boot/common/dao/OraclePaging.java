package org.mose.boot.common.dao;

import org.springframework.util.Assert;

/**
 * what:    Oracle的分页处理器
 *
 * @Author: 靳磊
 * @Date: 2017/8/12:21
 */
public class OraclePaging implements IPaging {
    @Override
    public String paging(String sql, int pageNumber, int pageRowCount) {
        Assert.notNull(sql, "The sql is null.");
        return "SELECT * FROM ( SELECT A.*, ROWNUM RN FROM (" + sql + ") A WHERE ROWNUM <=" + pageNumber * pageRowCount
                + " ) WHERE RN > " + (pageNumber - 1) * pageRowCount;
    }
}
