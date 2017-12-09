package org.mose.boot.system.repository.mysql;

import org.mose.boot.common.dao.stream.AbstractStreamRepository;
import org.mose.boot.system.modal.User;
import org.mose.boot.system.repository.IUserRepository;
import org.mose.boot.system.repository.IUserRoleRepository;
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
public class UserMysqlRepository extends AbstractStreamRepository<User, Integer> implements IUserRepository {
    @Autowired
    private IUserRoleRepository userRoleRepository;

    @Override
    public User queryOne(int id) {
        String sql = "select id, username, password, account_non_expired, account_non_locked, credentials_non_expired, enabled from t_system_user t where id=?";
        return query().sql(sql).parameters(id).queryOne();
    }

    @Override
    public List<User> queryMany(int pageNumber, int pageRowCount) {
        String sql = "select id, username, password, account_non_expired, account_non_locked, credentials_non_expired, enabled from t_system_user t";
        return query().sql(sql).paging(pageNumber, pageRowCount).queryMany();
    }

    @Override
    public List<User> queryAll() {
        String sql = "select id, username, password, account_non_expired, account_non_locked, credentials_non_expired, enabled from t_system_user t";
        return query().sql(sql).queryMany();
    }

    @Override
    public int queryCount() {
        String sql = "select count(id) from t_system_user";
        return query().sql(sql).queryCount();
    }

    @Override
    public int insertOne(User user) {
        StringBuffer sql = new StringBuffer();
        sql.append("insert into t_system_user(username, password, account_non_expired, account_non_locked, credentials_non_expired, enabled)");
        sql.append(" values(:username, :password, :accountNonExpired, :accountNonLocked, :credentialsNonExpired, :enabled)");
        Number id = insert().sql(sql.substring(0)).entity(user).idColumnName("id").insertOneForAutoGeneratedId();
        if (id == null) {
            return ReturnCodeUtil.FAIL__INSERT;
        } else {
            user.setId(id.intValue());
            return ReturnCodeUtil.SUCCESS__INSERT;
        }
    }

    @Override
    public int updateOne(User user) {
        StringBuffer sql = new StringBuffer();
        sql.append("update t_system_user set username=:username, password=:password, account_non_expired=:accountNonExpired, account_non_locked=:accountNonLocked, credentials_non_expired=:credentialsNonExpired, enabled=:enabled");
        sql.append(" where id=:id");
        int rowCount = update().sql(sql.substring(0)).parameterBean(user).updateAny();
        if (rowCount > 0) {
            return ReturnCodeUtil.SUCCESS__UPDATE;
        } else {
            return ReturnCodeUtil.FAIL__UPDATE_NONE;
        }
    }

    @Override
    public int deleteOne(User user) {
        return deleteOne(user.getId());
    }

    @Override
    public int deleteOne(int id) {
        int returnCode = userRoleRepository.deleteAllByUserId(id);
        if (ReturnCodeUtil.isFail(returnCode)) {
            return returnCode;
        }

        String sql = "delete from t_system_user where id=?";
        delete().sql(sql).parameters(id).deleteAny();
        return ReturnCodeUtil.SUCCESS__DELETE;
    }

    public IUserRoleRepository getUserRoleRepository() {
        return userRoleRepository;
    }

    public void setUserRoleRepository(IUserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }
}