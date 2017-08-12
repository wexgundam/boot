package org.mose.springboot.sidebar.dao;

import org.mose.springboot.dao.AbstractRepository;
import org.mose.springboot.sidebar.modal.Abc;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description:
 *
 * @Author: 靳磊
 * @Date: 2017/8/11:23
 */
@Repository
public class AbcRepository extends AbstractRepository<Abc, String> {
    public Abc queryOneById(String id) {
        String sql = new String("select id, name from resource where id=?");
        return queryOneByParameters(sql, id);
    }

    public List<Abc> queryManyByName(String name) {
        String sql = new String("select id, name from resource where name=?");
        return queryManyByParametersAndPaging(sql, 1, 2, name);
    }
}
