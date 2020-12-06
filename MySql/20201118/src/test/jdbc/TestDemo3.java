package test.jdbc;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;

    public class TestDemo3 {
        public static void main(String[] args) {
            query("李四 'or '1' = '1");
        }

        public static void query(String queryName) {
            Connection connection = null;
            Statement statement = null;
            ResultSet resultSet = null;
            try {
                //创建数据库连接池
                DataSource dataSource = new MysqlDataSource();
                ((MysqlDataSource) dataSource).setUrl("jdbc:mysql://localhost:3306/chong?user=root&password=19991230&useUnicode=true&useSSL=false&characterEncoding=UTF-8");

                // 第一步：创建数据库连接
                connection = dataSource.getConnection();
                //检测是否连接成功
                //System.out.println(connection);

                // 第二步：创建了操作命令对象
                statement = connection.createStatement();

                // 第三步：执行sql
                String sql = "select id,name,salary from emp where name = '"+queryName+"'";
                System.out.println(sql);
                resultSet = statement.executeQuery(sql);

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
