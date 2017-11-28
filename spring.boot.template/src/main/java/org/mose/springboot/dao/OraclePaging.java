package org.mose.springboot.dao;

import org.springframework.util.Assert;

/**
 * Description:
 *
 * @Author: 靳磊
 * @Date: 2017/8/12:21
 */
public class OraclePaging implements IPaging {
    @Override
    public String doPaging(String sql, int pageNumber, int pageSize) {
        Assert.notNull(sql, "The sql is null.");
        return "SELECT * FROM ( SELECT A.*, ROWNUM RN FROM (" + sql + ") A WHERE ROWNUM <=" + pageNumber * pageSize
                + " ) WHERE RN > " + (pageNumber - 1) * pageSize;
    }
}
