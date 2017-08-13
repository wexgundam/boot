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

    public boolean insertOne(Abc abc) {
        String sql = "insert into resource set id=:id, name=:name";
        return insertOne(sql, abc);
    }

    public int deleteOne(Abc abc) {
        String sql = "delete from resource where id=:id";
        return deleteAnyByParameterBean(sql, abc);
    }

    public int updateOne(Abc abc) {
        String sql = "update resource set name=:name where id=:id";
        return updateAnyByParameterBean(sql, abc);
    }
}
