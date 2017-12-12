package org.mose.boot.system.repository.mysql;

import org.mose.boot.common.dao.stream.AbstractStreamRepository;
import org.mose.boot.system.modal.Authority;
import org.mose.boot.system.modal.Role;
import org.mose.boot.system.modal.RoleAuthority;
import org.mose.boot.system.repository.IAuthorityRepository;
import org.mose.boot.system.repository.IRoleAuthorityRepository;
import org.mose.boot.util.code.ReturnCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description:
 *
 * @Author: 靳磊
 * @Date: 2017/8/14:22
 */
@Component
public class RoleAuthorityMysqlRepository extends AbstractStreamRepository<RoleAuthority, Integer> implements IRoleAuthorityRepository {
    @Autowired
    private IAuthorityRepository authorityRepository;

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
    public int queryCountByRoleId(int roleId) {
        String sql = "select count(a.id) from t_system_authority a left outer join t_system_role_authority ra on ra.authority_id=a.id where ra.role_id=?";
        return query().sql(sql).parameters(roleId).queryCount();
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
    public int deleteOne(int roleId, int authorityId) {
        String sql = "delete from t_system_role_authority where role_id=? and authority_id=?";
        delete().sql(sql).parameters(roleId, authorityId).deleteAny();
        return ReturnCodeUtil.SUCCESS__DELETE;
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

    private String getSql4QueryAuthoritiesByRoleId() {
        return "select a.id, a.name, a.description from t_system_authority a left outer join t_system_role_authority ra on ra.authority_id=a.id where ra.role_id=? order by a.name";
    }

    @Override
    public List<Authority> queryManyAuthoritiesByRoleId(int roleId, int pageNumber, int pageRowCount) {
        String sql = getSql4QueryAuthoritiesByRoleId();
        return authorityRepository.queryMany(sql, new Object[]{roleId}, pageNumber, pageRowCount);
    }

    @Override
    public List<Authority> queryAllAuthoritiesByRoleId(int roleId) {
        String sql = getSql4QueryAuthoritiesByRoleId();
        return authorityRepository.queryAll(sql, new Object[]{roleId});
    }


    private StringBuilder getSql4QueryAuthoritiesByUserId() {
        StringBuilder sql = new StringBuilder();
        sql.append("select distinct(nra.id)");
        sql.append(" ,(select a.name from t_system_authority a where a.id=nra.id) name");
        sql.append(" ,(select a.description from t_system_authority a where a.id=nra.id) description");
        sql.append(" from");
        sql.append(" (select ra.role_id, a.id, a.name, a.description from t_system_authority a left outer join t_system_role_authority ra on ra.authority_id=a.id) nra");
        sql.append(" left outer join t_system_user_role ur on nra.role_id=ur.role_id");
        sql.append(" where ur.user_id=?");
        return sql;
    }

    @Override
    public List<Authority> queryManyAuthoritiesByUserId(int userId, int pageNumber, int pageRowCount) {
        StringBuilder sql = getSql4QueryAuthoritiesByUserId();
        return authorityRepository.queryMany(sql.substring(0), new Object[]{userId}, pageNumber, pageRowCount);
    }

    @Override
    public List<Authority> queryAllAuthoritiesByUserId(int userId) {
        StringBuilder sql = getSql4QueryAuthoritiesByUserId();
        return authorityRepository.queryAll(sql.substring(0), new Object[]{userId});
    }

    @Override
    public int queryCountByUserId(int userId) {
        StringBuffer sql = new StringBuffer();
        sql.append("select count(a.id) from (");
        sql.append(getSql4QueryAuthoritiesByUserId());
        sql.append(") a");
        return query().sql(sql.substring(0)).parameters(userId).queryCount();
    }

    public IAuthorityRepository getAuthorityRepository() {
        return authorityRepository;
    }

    public void setAuthorityRepository(IAuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }
}