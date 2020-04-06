package Dao;

import Beans.Administer;
import Utils.jdbcUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class administerDaoIm extends BaseDAO<Administer> implements administerDao {
    private DataSource ds = jdbcUtils.getDataSource();
    @Override
    public void addOn(Administer administer) {
        if (administer == null) throw new IllegalArgumentException("the administer is null!");
        Connection connection = null;
        String sql = "insert into administer (name,email,password,sdept,code,status) value(?,?,?,?,?,?)";
        try {
            connection = ds.getConnection();
            update(connection,sql,administer.getName(),administer.getEmail(),administer.getPassword()
            ,administer.getSdept(),administer.getCode(),administer.getStatus());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Administer findByEmail(String email) {
        if (email == null) throw new IllegalArgumentException("the email is null");
        Connection connection = null;
        String sql = "select * from administer where email = ?";
        try {
            connection = ds.getConnection();
            Administer instance = getInstance(connection, sql, email);
            return instance;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Administer findByCode(String code) {
        if (code == null) throw new IllegalArgumentException("the code is null");
        Connection connection = null;
        String sql = "select * from administer where code = ?";
        try {
            connection = ds.getConnection();
            Administer instance = getInstance(connection, sql, code);
            return instance;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Administer login(String email, String password) {
        String sql = "select * from administer where email = ? and password = ?";
        Connection connection = null;
        try {
            connection = ds.getConnection();
            Administer instance = getInstance(connection, sql, email, password);
            return instance;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void change(String newPassworld, Administer administer) {
        String sql = "update administer where name = ? set password = ?";
        Connection connection = null;
        try {
            connection = ds.getConnection();
            update(connection,sql,administer.getName(),newPassworld);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
