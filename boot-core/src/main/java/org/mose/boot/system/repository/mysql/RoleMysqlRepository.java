package org.mose.boot.system.repository.mysql;

import org.mose.boot.common.dao.stream.AbstractStreamRepository;
import org.mose.boot.system.modal.Role;
import org.mose.boot.system.repository.IRoleAuthorityRepository;
import org.mose.boot.system.repository.IRoleRepository;
import org.mose.boot.util.code.ReturnCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: 靳磊
 * @Date: 2017/8/14:22
 */
@Component
public class RoleMysqlRepository extends AbstractStreamRepository<Role, Integer> implements IRoleRepository {
    @Autowired
    private IRoleAuthorityRepository roleAuthorityRepository;

    @Override
    public Role queryOne(int id) {
        String sql = "select id, name, description from t_system_role t where id=?";
        return query().sql(sql).parameters(id).queryOne();
    }

    @Override
    public boolean queryExistByName(String name) {
        String sql = "select count(id) from t_system_role t where t.name=?";
        int count = query().sql(sql).parameters(name).queryCount();
        return count > 0;
    }

    @Override
    public List<Role> queryMany(int pageNumber, int pageRowCount) {
        String sql = "select id, name, description from t_system_role t order by t.name";
        return query().sql(sql).paging(pageNumber, pageRowCount).queryMany();
    }

    @Override
    public List<Role> queryMany(String sql, Object[] parameters, int pageNumber, int pageRowCount) {
        return query().sql(sql).parameters(parameters).paging(pageNumber, pageRowCount).queryMany();
    }

    @Override
    public List<Role> queryAll(String sql, Object[] parameters) {
        return query().sql(sql).parameters(parameters).queryMany();
    }

    @Override
    public List<Role> queryAll() {
        String sql = "select id, name, description from t_system_role t order by t.name";
        return query().sql(sql).queryMany();
    }


    @Override
    public int queryCount() {
        String sql = "select count(id) from t_system_role";
        return query().sql(sql).queryCount();
    }

    @Override
    public int insertOne(Role role) {
        //不允许写入名称一样的权限
        boolean exist = queryExistByName(role.getName());
        if (exist) {
            return ReturnCodeUtil.FAIL__INSERT_EXIST;
        }

        StringBuffer sql = new StringBuffer();
        sql.append("insert into t_system_role(name, description)");
        sql.append(" values(:name, :description)");
        Number id = insert().sql(sql.substring(0)).entity(role).idColumnName("id").insertOneForAutoGeneratedId();
        if (id == null) {
            return ReturnCodeUtil.FAIL__INSERT;
        } else {
            role.setId(id.intValue());
            return ReturnCodeUtil.SUCCESS__INSERT;
        }
    }

    @Override
    public int updateOne(Role role) {
        StringBuffer sql = new StringBuffer();
        sql.append("update t_system_role set description=:description");
        sql.append(" where id=:id");
        int rowCount = update().sql(sql.substring(0)).parameterBean(role).updateAny();
        if (rowCount > 0) {
            return ReturnCodeUtil.SUCCESS__UPDATE;
        } else {
            return ReturnCodeUtil.FAIL__UPDATE_NONE;
        }
    }

    @Override
    public int deleteOne(Role role) {
        return deleteOne(role.getId());
    }

    @Override
    public int deleteOne(int id) {
        String sql = "delete from t_system_role where id=?";
        delete().sql(sql).parameters(id).deleteAny();
        return ReturnCodeUtil.SUCCESS__DELETE;
    }
}
