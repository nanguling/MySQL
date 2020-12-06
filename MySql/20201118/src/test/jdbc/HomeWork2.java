package test.jdbc;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


//一次性向test表中添加100行数据
public class HomeWork2 {
    public static void main(String[] args)throws Exception {
        //创建数据库连接池
        DataSource dataSource = new MysqlDataSource();
        ((MysqlDataSource) dataSource).setUrl("jdbc:mysql://localhost:3306/chong?user=root&password=19991230&useUnicode=true&useSSL=false&characterEncoding=UTF-8");

        //创建数据库连接
        Connection connection = dataSource.getConnection();

        //sql命令
        String sql = "insert into test (id,name,age) values (?,?,?)";

        //创建sql命令对象
        PreparedStatement ps = connection.prepareStatement(sql);

        //开始循环生成sql命令
        for (int i = 1; i <= 100; i++) {
            //通过预编译sql命令填充数据生成新的sql命令
            ps.setInt(1,i);
            ps.setString(2,"name"+i);
            ps.setInt(3,i);
            //将sql全部放到弹夹里，但不发送
            ps.addBatch();
        }
        //将刚刚弹夹里的sql语句一次性发送给数据库服务器
        ps.executeBatch();

        //返回结果
        int result = ps.executeUpdate();
        System.out.println(result);

        //关闭资源
        if (connection != null) {
            connection.close();
        }
        if (ps != null) {
            ps.close();
        }
    }
}
