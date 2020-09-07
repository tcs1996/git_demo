package com.itheima.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Druid���ӳصĹ�����
 */
public class JDBCUtils {

    //1.�����Ա���� DataSource
    private static DataSource ds;

    static {
        try {
            //1.���������ļ�
            Properties pro = new Properties();
            pro.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
            //2.��ȡDataSource
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ��ȡ����
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * �ͷ���Դ
     */
    public static void close(Statement stmt, Connection conn) {
        close(null, stmt, conn);
    }


    public static void close(ResultSet rs, Statement stmt, Connection conn) {


        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();//�黹����
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * ��ȡ���ӳط���
     */

    public static DataSource getDataSource() {
        return ds;
    }

}