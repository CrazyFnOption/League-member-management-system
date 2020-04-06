package Dao;

import Beans.Member;
import Utils.jdbcUtils;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class memberDaoIm extends BaseDAO<Member> implements memberDao {
    private DataSource ds = jdbcUtils.getDataSource();

    @Override
    public void addOn(Member member) {
        String sql = "insert into member (sno,sex,age,major,sdept,className,joinDate,assistant,name) " +
                "value(?,?,?,?,?,?,?,?,?)";
        Connection connection = null;
        try {
            connection = ds.getConnection();
            update(connection,sql,member.getSno(),member.getSex(),member.getAge(),member.getMajor(),
                    member.getSdept(),member.getClassName(),member.getJoinDate(),member.getAssistant(),member.getName());
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
    public Member findMember(int id) {

        String sql = "select * from member where id = " + id;
        Connection connection = null;
        try {
            connection = ds.getConnection();
            return getInstance(connection,sql);
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
    public List<Member> query(String condition, String... args) {
        String sql = "select * from member where " + condition + " = ?";
        for (int i = 1; i < args.length; i++) {
            sql += " or " + condition + " = ?";
        }
        Connection connection = null;
        List<Member> forList = null;
        try {
            connection = ds.getConnection();
            forList = getForList(connection, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return forList;
    }

    @Test
    public void test() {
        String name = "sdept";
        String [] condition = {"电气","电智"};
        List<Member> members = queryByPage(6, 1, condition, name);
        System.out.println(members);
    }

    @Override
    public List<Member> queryByPage(int row, int current, String[] condition,String name) {
        Connection connection = null;
        try {
            connection = ds.getConnection();
            String sql = "select * from member where ";
            StringBuilder sb = new StringBuilder(sql);
            List<Object>obj = new ArrayList<Object>();
            if (condition != null) {
                sb.append("0 ");
                for (String s : condition) {
                    if (s.equals("current") || s.equals("row")) continue;
                    sb.append(" or ").append(name).append(" like ?");
                    obj.add("%" + s + "%");
                }
            }
            else sb.append("1 = 1");
                obj.add((current - 1) * row);
                obj.add(row);
                sb.append(" limit ? , ?");
            return getForList(connection, sb.toString(), obj);
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
    public void delete(String condition, String... args) {
        String sql = "delete from member where " + condition + " = ?";

        for (int i = 1; i < args.length; i++) {
            sql += " or " + condition + " = ?";
        }
        Connection connection = null;
        try {
            connection = ds.getConnection();
            update(connection,sql, (Object) args);
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
    public void delete(int id) {
        String sql = "delete from member where id = " + id;
        Connection connection = null;
        try {
            connection = ds.getConnection();
            update(connection,sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //String sql = "insert into member (sno,sex,age,major,sdept,className,joinDate,assistant,name) " +
//                "value(?,?,?,?,?,?,?,?)";
    @Override
    public void update(Member member) {
        String sql = "update member set sex = ?, age = ?, major = ?, sdept = ?, className = ?,joinDate = ?,assistant = ?  where sno = ?";

        Connection connection = null;
        try {
            connection = ds.getConnection();
            update(connection,sql,member.getSex(),member.getAge(),member.getMajor(), member.getSdept(), member.getClassName(),
                    member.getJoinDate(),member.getAssistant(),member.getSno());
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
    public Long getNumber(String[] condition,String name) {
        Connection connection = null;
        try {
            connection = ds.getConnection();
            String sql = "select count(id) from member where ";
            StringBuilder sb = new StringBuilder(sql);
            List<Object>obj = new ArrayList<Object>();
            if (condition != null) {
                sb.append("0 ");
                for (String s:condition) {

                    if (s.equals("current") || s.equals("row")) continue;
                    sb.append(" or ").append(name).append(" like ?");
                    obj.add("%"+s+"%");
                }
            }
            else sb.append("1 = 1 ");

            Long num = (Long) getValue(connection,sb.toString(),obj);

            return num;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
