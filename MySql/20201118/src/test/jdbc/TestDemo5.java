package test.jdbc;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDemo5 {
    public static void main(String[] args) {
        update("李");
    }

    public static void update(String updateName) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //创建数据库连接池
            DataSource dataSource = new MysqlDataSource();
            ((MysqlDataSource) dataSource).setUrl("jdbc:mysql://localhost:3306/chong?user=root&password=19991230&useUnicode=true&useSSL=false&characterEncoding=UTF-8");

            // 第一步：创建数据库连接
            connection = dataSource.getConnection();
            //检测是否连接成功
            //System.out.println(connection);

            //？表示占位符
            String sql = "update emp set salary = salary+100 where name like ?";
            // 第二步：创建了操作命令对象（带占位符的sql在数据库预编译，可以提高效率，占位符的方式可以防止sql注入）
            statement = connection.prepareStatement(sql);

            //替换占位符
            statement.setString(1,"%"+updateName+"%");
            System.out.println(statement);
            // 第三步：执行sql
            //得到结果 int类型
            //新增/修改/删除操作都是executeUpdate
            int result = statement.executeUpdate();
            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
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
