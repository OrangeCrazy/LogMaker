package utils;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author ZhouCheng
 * @version 1.0
 * @date 2020/9/6 15:46
 */
public class DbConnect {


    private static DruidDataSource dataSource = null;

    /**
     * 构造函数完成数据库的连接和连接对象的生成
     *
     * @throws Exception
     */
    public DbConnect() {

    }

    public void GetDbConnect() throws Exception {
        try {
            if (dataSource == null) {
                dataSource = new DruidDataSource();
                //设置连接参数
                dataSource.setUrl("mysql:jdbc:mysql://localhost:3306/orange");
                dataSource.setDriverClassName("com.mysql.jdbc.Driver ");
                dataSource.setUsername("root");
                dataSource.setPassword("123456");
                //配置初始化大小、最小、最大
                dataSource.setInitialSize(1);
                dataSource.setMinIdle(1);
                dataSource.setMaxActive(20);
                //连接泄漏监测
                dataSource.setRemoveAbandoned(true);
                dataSource.setRemoveAbandonedTimeout(30);
                //配置获取连接等待超时的时间
                dataSource.setMaxWait(20000);
                //配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
                dataSource.setTimeBetweenEvictionRunsMillis(20000);
                //防止过期
                dataSource.setValidationQuery("SELECT 'x'");
                dataSource.setTestWhileIdle(true);
                dataSource.setTestOnBorrow(true);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 取得已经构造生成的数据库连接
     *
     * @return 返回数据库连接对象
     * @throws Exception
     */
    public Connection getConnect() throws Exception {
        Connection con = null;
        try {
            GetDbConnect();
            con = dataSource.getConnection();
        } catch (Exception e) {
            throw e;
        }
        return con;
    }


    public static void main(String[] args) throws Exception {
        DbConnect dbConnect = new DbConnect();
        Connection connection = dbConnect.getConnect();
        String sql = "SELECT 1+1 from dual";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            System.out.println(resultSet.getInt(1));
        }
    }
}
