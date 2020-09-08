package com.orange.issueCheck.dbUtils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author ZhouCheng
 * @version 1.0
 * @date 2020/8/19 15:17
 */
public class MySQLDbUtils {

    //这里可以设置数据库名称
    private final static String URL = "jdbc:sqlserver://localhost:3306;DatabaseName=master";
    private static final String USER = "sa";
    private static final String PASSWORD = "";


    private static Connection conn = null;

    //静态代码块（将加载驱动、连接数据库放入静态块中）
    static {
        try {
            //1.加载驱动程序
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //2.获得数据库的连接
            conn = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //对外提供一个方法来获取数据库连接
    public static Connection getConnection() {
        return conn;
    }

}
