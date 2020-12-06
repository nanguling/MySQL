package test.jdbc;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HomeWork {
    public static void main(String[] args) {
        //insert("程咬金");
        //update("程咬金");
        delete("程咬金");
        select();
    }
    //添加
    public static void insert(String name) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //创建数据库连接池
            DataSource dataSource = new MysqlDataSource();
            ((MysqlDataSource) dataSource).setUrl("jdbc:mysql://localhost:3306/chong?user=root&password=19991230&useUnicode=true&useSSL=false&characterEncoding=UTF-8");

            //创建数据库连接
            connection = dataSource.getConnection();


            //创建操作命令对象
            ps = connection.prepareStatement("insert into student values(9,996,?,null,3)");

            //替换占位符
            ps.setString(1,name);
            System.out.println(ps);

            //执行sql语句
            int val = ps.executeUpdate();
            System.out.println(val);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
                if (ps != null)
                    ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    //修改
    public static void update(String name) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //创建数据库连接池
            DataSource dataSource = new MysqlDataSource();
            ((MysqlDataSource) dataSource).setUrl("jdbc:mysql://localhost:3306/chong?user=root&password=19991230&useUnicode=true&useSSL=false&characterEncoding=utf-8");

            //创建数据库连接
            connection = dataSource.getConnection();

            //创建操作命令对象
            statement = connection.prepareStatement("update student set classes_id=2 where name = ?");

            //替换占位符
            statement.setString(1,name);
            System.out.println(statement);

            //执行sql
            int val = statement.executeUpdate();
            System.out.println(val);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
                if (statement != null)
                    statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    //查询
    public static void select() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            //创建数据库连接池
            DataSource dataSource = new MysqlDataSource();
            ((MysqlDataSource) dataSource).setUrl("jdbc:mysql://localhost:3306/chong?user=root&password=19991230&useUnicode=true&useSSL=false&characterEncoding=utf-8");

            //创建数据库连接
            connection = dataSource.getConnection();

            //创建操作命令对象
            statement = connection.prepareStatement("select id,name from student where classes_id=2");

            //执行sql
            resultSet = statement.executeQuery();

            //处理结果集
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String stuName = resultSet.getString("name");
                System.out.printf("id=%s,stuName=%s%n",id,stuName);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    //删除
    public static void delete(String name) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //创建数据库连接池
            DataSource dataSource = new MysqlDataSource();
            ((MysqlDataSource) dataSource).setUrl("jdbc:mysql://localhost:3306/chong?user=root&password=19991230&useUnicode=true&useSSL=false&characterEncoding=utf-8");

            //创建数据库连接
            connection = dataSource.getConnection();

            //创建操作命令对象
            statement = connection.prepareStatement("delete from student where name = ?");

            //替换占位符
            statement.setString(1,name);

            //执行sql
            int val = statement.executeUpdate();
            System.out.println(val);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
