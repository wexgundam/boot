package org.mose.springboot.sidebar.dao;

import org.mose.springboot.sidebar.modal.Abc;
import org.mose.springboot.sidebar.dao.stream.StreamRepository;
import org.springframework.stereotype.Repository;

/**
 * Description:
 *
 * @Author: 靳磊
 * @Date: 2017/8/11:23
 */
@Repository
public class AbcStreamRepository extends StreamRepository<Abc, String> {
    public Abc queryOneById(String id) {
        String sql = new String("select id, name from resource where id=?");
        return query().sql(sql).parameters(id).queryOne();
    }
}
