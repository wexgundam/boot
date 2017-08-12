package org.mose.springboot.sidebar.dao;

import org.mose.springboot.sidebar.modal.Abc;
import org.mose.springboot.dao.stream.AbstractStreamRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description:
 *
 * @Author: 靳磊
 * @Date: 2017/8/11:23
 */
@Repository
public class AbcStreamRepository extends AbstractStreamRepository<Abc, String> {
    public Abc queryOneById(String id) {
        String sql = new String("select id, name from resource where id=?");
        return query().sql(sql).parameters(id).queryOne();
    }
}
