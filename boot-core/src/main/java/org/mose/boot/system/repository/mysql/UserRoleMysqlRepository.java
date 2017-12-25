package org.mose.boot.system.repository.mysql;

import org.mose.boot.common.dao.stream.AbstractStreamRepository;
import org.mose.boot.system.modal.Role;
import org.mose.boot.system.modal.UserRole;
import org.mose.boot.system.repository.IRoleRepository;
import org.mose.boot.system.repository.IUserRoleRepository;
import org.mose.boot.util.code.ReturnCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: 靳磊
 * @Date: 2017/8/14:22
 */
@Component
public class UserRoleMysqlRepository extends AbstractStreamRepository<UserRole, Integer> implements IUserRoleRepository {
    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public UserRole queryOne(int id) {
        String sql = "select id, user_id, role_id  from t_system_user_role t where id=?";
        return query().sql(sql).parameters(id).queryOne();
    }

    @Override
    public List<UserRole> queryAllByUserId(int userId) {
        String sql = "select id, user_id, role_id  from t_system_user_role t where user_id=?";
        return query().sql(sql).parameters(userId).queryMany();
    }

    @Override
    public List<UserRole> queryAllByRoleId(int roleId) {
        String sql = "select id, user_id, role_id  from t_system_user_role t where role_id=?";
        return query().sql(sql).parameters(roleId).queryMany();
    }

    @Override
    public int queryCountByUserId(int userId) {
        String sql = "select count(r.id) from t_system_role r left outer join t_system_user_role ur on ur.role_id=r.id where ur.user_id=?";
        return query().sql(sql).parameters(userId).queryCount();
    }

    @Override
    public int insertOne(UserRole userRole) {
        StringBuffer sql = new StringBuffer();
        sql.append("insert into t_system_user_role(user_id, role_id)");
        sql.append(" values(:userId, :roleId)");
        Number id = insert().sql(sql.substring(0)).entity(userRole).idColumnName(
                "id").insertOneForAutoGeneratedId();
        if (id == null) {
            return ReturnCodeUtil.FAIL__INSERT;
        } else {
            userRole.setId(id.intValue());
            return ReturnCodeUtil.SUCCESS__INSERT;
        }
    }

    @Override
    public int updateOne(UserRole userRole) {
        StringBuffer sql = new StringBuffer();
        sql.append("update t_system_user_role set user_id=:userId, role_id=:roleId");
        sql.append(" where id=:id");
        int rowCount = update().sql(sql.substring(0)).parameterBean(userRole).updateAny();
        if (rowCount > 0) {
            return ReturnCodeUtil.SUCCESS__UPDATE;
        } else {
            return ReturnCodeUtil.FAIL__UPDATE_NONE;
        }
    }

    @Override
    public int deleteOne(UserRole userRole) {
        return deleteOne(userRole.getId());
    }

    @Override
    public int deleteOne(int id) {
        String sql = "delete from t_system_user_role where id=?";
        int rowCount = delete().sql(sql).parameters(id).deleteAny();
        if (rowCount > 0) {
            return ReturnCodeUtil.SUCCESS__DELETE;
        } else {
            return ReturnCodeUtil.FAIL__DELETE_NONE;
        }
    }

    @Override
    public int deleteOne(int userId, int roleId) {
        String sql = "delete from t_system_user_role where user_id=? and role_id=?";
        delete().sql(sql).parameters(userId, roleId).deleteAny();
        return ReturnCodeUtil.SUCCESS__DELETE;
    }

    @Override
    public int deleteAllByUserId(int userId) {
        String sql = "delete from t_system_user_role where user_id=?";
        delete().sql(sql).parameters(userId).deleteAny();
        return ReturnCodeUtil.SUCCESS__DELETE;
    }

    @Override
    public int deleteAllByRoleId(int roleId) {
        String sql = "delete from t_system_user_role where role_id=?";
        delete().sql(sql).parameters(roleId).deleteAny();
        return ReturnCodeUtil.SUCCESS__DELETE;
    }

    private String getSql4QueryRolesByUserId() {
        return "select r.id, r.name, r.description from t_system_role r left outer join t_system_user_role ur on ur.role_id=r.id where ur.user_id=? order by r.name";
    }

    @Override
    public List<Role> queryManyRolesByUserId(int userId, int pageNumber, int pageRowCount) {
        String sql = getSql4QueryRolesByUserId();
        return roleRepository.queryMany(sql, new Object[]{userId}, pageNumber, pageRowCount);
    }

    @Override
    public List<Role> queryAllRolesByUserId(int userId) {
        String sql = getSql4QueryRolesByUserId();
        return roleRepository.queryAll(sql, new Object[]{userId});
    }
}
