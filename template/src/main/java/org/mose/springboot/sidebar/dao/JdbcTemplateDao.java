package org.mose.springboot.sidebar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Spring JdbcTemplate模板
 * Object... args：args是个数组，此参数可传也可不传
 *
 * @author hys
 */
@Repository
public class JdbcTemplateDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void update(String sql) {
        jdbcTemplate.update(sql);
    }

    //update t_sys_user set name = ?, age = ?  
    public void update(String sql, Object... args) {
        jdbcTemplate.update(sql, args);
    }

    //update t_sys_user set name = ?, age = ?  
    //argTypes：指定args参数的类型，例如： Types.DATE, Types.VARCHAR  
    public void update(String sql, Object[] args, int[] argTypes) {
        jdbcTemplate.update(sql, args, argTypes);
    }

    //批量处理  
    //update t_sys_user set name = ?, age = ? where id = ?;  
    public void batchUpdate(String sql) {
        jdbcTemplate.batchUpdate(sql);
    }

    //insert into t_sys_user (id, name, sex, age) values (?, ?, ?, ?)  
    public void batchUpdate(String sql, List<Object[]> batchArgs) {
        jdbcTemplate.batchUpdate(sql, batchArgs);
    }

    //insert into t_sys_user (id, name, sex, age) values (?, ?, ?, ?)  
    //argTypes：指定batchArgs参数的类型，例如： Types.DATE, Types.VARCHAR  
    public void batchUpdate(String sql, List<Object[]> batchArgs, int[] argTypes) {
        jdbcTemplate.batchUpdate(sql, batchArgs, argTypes);
    }

    //返回list集合，list里存放的是实体对象，不是map集合  
    //select * from t_sys_user where name = ""  
    public <T> List<T> queryForList(String sql, Class<T> elementType) {
        return jdbcTemplate.queryForList(sql, elementType);
    }

    //select * from t_sys_user where name = ?;  
    public <T> List<T> queryForList(String sql, Object[] args, Class<T> elementType) {
        return jdbcTemplate.queryForList(sql, args, elementType);
    }

    //select * from t_sys_user where name = ?;  
    public <T> List<T> queryForList(String sql, Class<T> elementType, Object... args) {
        return jdbcTemplate.queryForList(sql, elementType, args);
    }

    //select * from t_sys_user where name = ?;  
    //argTypes：指定args参数的类型，例如： Types.DATE, Types.VARCHAR  
    public <T> List<T> queryForList(String sql, Object[] args, int[] argTypes, Class<T> elementType) {
        return jdbcTemplate.queryForList(sql, args, argTypes, elementType);
    }

    //返回list集合，list里存放的是map集合，不是实体对象  
    //select * from t_sys_user where name = ""  
    public List<Map<String, Object>> queryForList(String sql) {
        return jdbcTemplate.queryForList(sql);
    }

    //select * from t_sys_user where name = ?  
    public List<Map<String, Object>> queryForList(String sql, Object... args) {
        return jdbcTemplate.queryForList(sql, args);
    }

    //select * from t_sys_user where name = ?  
    //argTypes：指定args参数的类型，例如： Types.DATE, Types.VARCHAR  
    public List<Map<String, Object>> queryForList(String sql, Object[] args, int[] argTypes) {
        return jdbcTemplate.queryForList(sql, args, argTypes);
    }

    //返回map集合，不是实体对象  
    //select * from t_sys_user where id = "";  
    public Map<String, Object> queryMap(String sql) {
        return jdbcTemplate.queryForMap(sql);
    }

    //select * from t_sys_user where id = ?;  
    public Map<String, Object> queryForMap(String sql, Object... args) {
        return jdbcTemplate.queryForMap(sql, args);
    }

    //select * from t_sys_user where id = ?;  
    //argTypes：指定传入参数的类型，例如： Types.DATE, Types.VARCHAR  
    public Map<String, Object> queryMap(String sql, Object[] args, int[] argTypes) {
        return jdbcTemplate.queryForMap(sql, args, argTypes);
    }

    //返回基本数据类型，如：String、Integer、Long  
    //select count(id) from t_sys_user sex = ''  
    public <T> T queryObject(String sql, Class<T> requiredType) {
        return jdbcTemplate.queryForObject(sql, requiredType);
    }

    //select count(id) from t_sys_user sex = ?  
    public <T> T queryForObject(String sql, Class<T> requiredType, Object... args) {
        return jdbcTemplate.queryForObject(sql, requiredType, args);
    }

    //select count(id) from t_sys_user sex = ?  
    public <T> T queryObject(String sql, Object[] args, Class<T> requiredType) {
        return jdbcTemplate.queryForObject(sql, args, requiredType);
    }

    //select count(id) from t_sys_user sex = ?  
    //argTypes：指定args参数的类型，例如： Types.DATE, Types.VARCHAR  
    public <T> T queryObject(String sql, Object[] args, int[] argTypes, Class<T> requiredType) {
        return jdbcTemplate.queryForObject(sql, args, argTypes, requiredType);
    }

    //返回实体对象,不是map集合  
    //select * from t_sys_user where loginname = ''   
    //RowMapper的实现类是：new BeanPropertyRowMapper<User>(User.class)  
    public <T> T queryObject(String sql, RowMapper<T> rowMapper) {
        return jdbcTemplate.queryForObject(sql, rowMapper);
    }

    //select * from t_sys_user where loginname = ?  
    public <T> T queryObject(String sql, Object[] args, RowMapper<T> rowMapper) {
        return jdbcTemplate.queryForObject(sql, args, rowMapper);
    }

    //select * from t_sys_user where loginname = ?  
    public <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object... args) {
        return jdbcTemplate.queryForObject(sql, rowMapper, args);
    }

    //select * from t_sys_user where loginname = ?  
    //argTypes：指定args参数的类型，例如： Types.DATE, Types.VARCHAR  
    public <T> T queryForObject(String sql, Object[] args, int[] argTypes, RowMapper<T> rowMapper) {
        return jdbcTemplate.queryForObject(sql, args, argTypes, rowMapper);
    }

    //创建一个Statement对象来将SQL语句发送到数据库  
    //创建一个PreparedStatement对象来将参数化的 SQL 语句发送到数据库  
    public Connection openConnection() {
        Connection connection = null;
        try {
            connection = jdbcTemplate.getDataSource().getConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block  
            e.printStackTrace();
        }
        return connection;
    }
}  