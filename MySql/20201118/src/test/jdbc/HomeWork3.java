package test.jdbc;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ParameterBindings;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 1.删除emp表中工资大于11000的数据
 * 2.删除test_user表中id大于6的数据
 * 3.要求：只要有其中一条语句发生异常，那么终止所有sql命令执行，并且回到初始状态
 */

public class HomeWork3 {
    public static void main(String[] args)throws Exception {
        //创建数据库连接池
        DataSource dataSource = new MysqlDataSource();
        ((MysqlDataSource) dataSource).setUrl("jdbc:mysql://localhost:3306/chong?user=root&password=19991230&useUnicode=true&useSSL=false&characterEncoding=UTF-8");

        //创建数据库连接
        Connection connection = (Connection) dataSource.getConnection();

        //sql命令
        String sql1 = "delete from emp where salary > 11000";
        String sql2 = "delete from test_user where id > 6";

        //开启事务
        connection.setAutoCommit(false);

        //创建sql命令对象
        PreparedStatement ps = connection.prepareStatement("");

        //推送sql命令
        try {
            ps.executeUpdate(sql1);
            ps.executeUpdate(sql2);
            //没有异常就提交
            connection.commit();
        }catch (SQLException e) {
            //有异常就回滚
            connection.rollback();
        }
    }
}
