package org.mose.boot.system.repository.mysql;

import org.mose.boot.common.dao.stream.AbstractStreamRepository;
import org.mose.boot.system.modal.RoleAuthority;
import org.mose.boot.system.repository.IRoleAuthorityRepository;
import org.mose.boot.util.code.ReturnCodeUtil;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description:
 *
 * @Author: 靳磊
 * @Date: 2017/8/14:22
 */
@Component
public class RoleAuthorityMysqlRepository extends AbstractStreamRepository<Integer, RoleAuthority> implements IRoleAuthorityRepository {
    @Override
    public RoleAuthority queryOne(int id) {
        String sql = "select id, role_id, authority_id  from t_system_role_authority t where id=?";
        return query().sql(sql).parameters(id).queryOne();
    }


    @Override
    public List<RoleAuthority> queryAllByRoleId(int roleId) {
        String sql = "select id, role_id, authority_id  from t_system_role_authority t where role_id=?";
        return query().sql(sql).parameters(roleId).queryMany();
    }

    @Override
    public List<RoleAuthority> queryAllByAuthorityId(int authorityId) {
        String sql = "select id, role_id, authority_id  from t_system_role_authority t where authority_id=?";
        return query().sql(sql).parameters(authorityId).queryMany();
    }


    @Override
    public int insertOne(RoleAuthority roleAuthority) {
        StringBuffer sql = new StringBuffer();
        sql.append("insert into t_system_role_authority(role_id, authority_id)");
        sql.append(" values(:roleId, :authorityId)");
        Number id = insert().sql(sql.substring(0)).entity(roleAuthority).idColumnName(
                "id").insertOneForAutoGeneratedId();
        if (id == null) {
            return ReturnCodeUtil.FAIL__INSERT;
        } else {
            roleAuthority.setId(id.intValue());
            return ReturnCodeUtil.SUCCESS__INSERT;
        }
    }

    @Override
    public int updateOne(RoleAuthority roleAuthority) {
        StringBuffer sql = new StringBuffer();
        sql.append("update t_system_role_authority set role_id=:roleId, authority_id=:authorityId");
        sql.append(" where id=:id");
        int rowCount = update().sql(sql.substring(0)).parameterBean(roleAuthority).updateAny();
        if (rowCount > 0) {
            return ReturnCodeUtil.SUCCESS__UPDATE;
        } else {
            return ReturnCodeUtil.FAIL__UPDATE_NONE;
        }
    }

    @Override
    public int deleteOne(RoleAuthority roleAuthority) {
        return deleteOne(roleAuthority.getId());
    }

    @Override
    public int deleteOne(int id) {
        String sql = "delete from t_system_role_authority where id=?";
        int rowCount = delete().sql(sql).parameters(id).deleteAny();
        if (rowCount > 0) {
            return ReturnCodeUtil.SUCCESS__DELETE;
        } else {
            return ReturnCodeUtil.FAIL__DELETE_NONE;
        }
    }

    @Override
    public int deleteAllByAuthorityId(int authorityId) {
        String sql = "delete from t_system_role_authority where authority_id=?";
        delete().sql(sql).parameters(authorityId).deleteAny();
        return ReturnCodeUtil.SUCCESS__DELETE;
    }

    @Override
    public int deleteAllByRoleId(int roleId) {
        String sql = "delete from t_system_role_authority where role_id=?";
        delete().sql(sql).parameters(roleId).deleteAny();
        return ReturnCodeUtil.SUCCESS__DELETE;
    }
}
