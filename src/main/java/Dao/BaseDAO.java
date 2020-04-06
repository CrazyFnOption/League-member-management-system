package Dao;

import Utils.jdbcUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
 * 主要是封装 各个通用的增删改的各个版本 一般提供Java直接操作数据库来写，
 * 然后具体表的相关功能的话，仅仅只是提供相应的interface
 * 然后如果有新的类的话，我们就去实现相应的接口就可以了。
 * 对应没有一额相应的数据库都有相应的操作。
 */
public abstract class BaseDAO<T> {

    private Class<T> clazz = null;

    public BaseDAO() {
        // 这个操作就是负责获取父类的泛型对象。下面这里一步一步解释其的由来。
        // 获取父类（超类）在这个类中也就是其本身，因为首先去实例化的是子类，然后其的超类就是本来的类。
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        // 将得到的类的信息进行一个参数化，便于得到后面的泛型信息
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        // 然后将其实际的类型返回成一个数组，取出第一个即可。
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        clazz = (Class<T>) actualTypeArguments[0];
    }
    // 通用的增删改的操作
    public void update(Connection connection,String sql,Object ... args) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            // 填充占位符，其里面相应占位符的个数
            for (int i = 0; i < args.length; i++) {
                if (args[i] == null) break;
                if (args[i].getClass().toString().equals("class java.util.ArrayList") ) {
                    List<Object>objj = (List<Object>) args[i];
                    if (objj.size() == 0) break;
                    int j = 0;
                    for (j = 0; j < objj.size(); j++){
                        ps.setObject(i + 1 + j, objj.get(j));
                    }
                    i += j;
                    if (i >= args.length) break;
                }
                else ps.setObject(i + 1, args[i]);
            }
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtils.closeResoure(null, ps);
        }
    }
    // 返回的是一条对象。
    public T getInstance(Connection connection,String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                if (args[i] == null) break;
                if (args[i].getClass().toString().equals("class java.util.ArrayList") ) {
                    List<Object>objj = (List<Object>) args[i];
                    if (objj.size() == 0) break;
                    int j = 0;
                    for (j = 0; j < objj.size(); j++){
                        ps.setObject(i + 1 + j, objj.get(j));
                    }
                    i += j;
                    if (i >= args.length) break;
                }
                else ps.setObject(i + 1, args[i]);
            }

            rs = ps.executeQuery();
            // 应用底层反射原理，反射到数据库上
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            if (rs.next()) {
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    Object colValue = rs.getObject(i + 1);
                    String colName = metaData.getColumnLabel(i + 1);
                    // 接下来就是开始反射映射到相应的值上面去
                    // getField 只能获取父类继承下来public 而 下面使用的这个可以获取全部
                    Field field = clazz.getDeclaredField(colName);
                    field.setAccessible(true);
                    field.set(t,colValue);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtils.closeResoure(null, ps, rs);
        }
        return null;
    }

    // 返回多 条对象
    public List<T> getForList(Connection connection, String sql, Object...args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                if (args[i] == null) break;
                if (args[i].getClass().toString().equals("class java.util.ArrayList") ) {
                    List<Object>objj = (List<Object>) args[i];
                    if (objj.size() == 0) break;
                    int j = 0;
                    for (j = 0; j < objj.size(); j++){
                        ps.setObject(i + 1 + j, objj.get(j));
                    }
                    i += j;
                    if (i >= args.length) break;
                }
                else ps.setObject(i + 1, args[i]);
            }

            rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            ArrayList<T> list = new ArrayList<>();
            while (rs.next()) {
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    Object colValue = rs.getObject(i + 1);
                    String colName = metaData.getColumnLabel(i + 1);

                    // 接下来就是开始反射映射到相应的值上面去
                    // getField 只能获取父类继承下来public 而 下面使用的这个可以获取全部
                    Field field = clazz.getDeclaredField(colName);
                    field.setAccessible(true);
                    field.set(t,colValue);
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtils.closeResoure(null, ps, rs);
        }
        return null;
    }

    public Long getValue(Connection connection,String sql,Object ...args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                if (args[i] == null) break;
                if (args[i].getClass().toString().equals("class java.util.ArrayList") ) {
                    List<Object>objj = (List<Object>) args[i];
                    if (objj.size() == 0) break;
                    int j = 0;
                    for (j = 0; j < objj.size(); j++){
                        ps.setObject(i + 1 + j, objj.get(j));
                    }
                    i += j;
                    if (i >= args.length) break;
                }
                else ps.setObject(i + 1, args[i]);
            }

            rs = ps.executeQuery();
            if (rs.next()) {
                return (Long) rs.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtils.closeResoure(null, ps, rs);
        }
        return null;
    }
}
