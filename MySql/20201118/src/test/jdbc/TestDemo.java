package test.jdbc;

import java.math.BigDecimal;
import java.sql.*;

public class TestDemo {
    public static void main2(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            // 加载JDBC驱动程序：反射，这样调用初始化com.mysql.jdbc.Driver类，即将该类加载到JVM方法
            //区，并执行该类的静态方法块、静态属性。
            Class.forName("com.mysql.jdbc.Driver");

            //1.创建数据库连接
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/chong?user=root&password=19991230&useUnicode=true&useSSL=false&characterEncoding=UTF-8");
            System.out.println(connection);

            //2.创建操作命令对象
            statement = connection.createStatement();

            //3.执行sql语句
            resultSet = statement.executeQuery(
                    "select stu.id,stu.sn,stu.name,cl.name from student stu join classes cl on cl.id = stu.classes_id");

            //4.处理结果集
            while (resultSet.next()) {
                int id = resultSet.getInt("stu.id");
                int sn = resultSet.getInt("stu.sn");
                String stuName = resultSet.getString("stu.name");
                String clNanme = resultSet.getString("cl.name");
                System.out.printf("stu.id=%s,stu.sn=%s,stu.name=%s,cl.name=%s%n",
                                    id,sn,stuName,clNanme);;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                //释放资源
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            // 加载JDBC驱动程序：反射，这样调用初始化com.mysql.jdbc.Driver类，即将该类加载到JVM方法区，并执行该类的静态方法块、静态属性。
            Class.forName("com.mysql.jdbc.Driver");

            // 第一步：创建数据库连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chong?user=root&password=19991230&useUnicode=true&useSSL=false&characterEncoding=UTF-8");
            //检测是否连接成功
            System.out.println(connection);

            // 第二步：创建了操作命令对象
            statement = connection.createStatement();

            // 第三步：执行sql
            resultSet = statement.executeQuery("select id,name,salary from emp");

            // 第四步：处理结果集ResultSet（类似List<Map<String, 字段类型>>）
            while (resultSet.next()) {//遍历每一行数据
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                BigDecimal salary = resultSet.getBigDecimal("salary");
                System.out.printf("id=%s, name=%s,salary=%s%n",
                        id, name, salary);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
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
}
