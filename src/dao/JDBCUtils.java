package dao;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * JDBC宸ュ叿绫� 浣跨敤Durid杩炴帴姹�
 */
public class JDBCUtils {

    private static DataSource ds ;

    static {

        try {
            //1.鍔犺浇閰嶇疆鏂囦欢
            Properties pro = new Properties();
            //浣跨敤ClassLoader鍔犺浇閰嶇疆鏂囦欢锛岃幏鍙栧瓧鑺傝緭鍏ユ祦
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            pro.load(is);

            //2.鍒濆鍖栬繛鎺ユ睜瀵硅薄
            ds = DruidDataSourceFactory.createDataSource(pro);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 鑾峰彇杩炴帴姹犲璞�
     */
    public static DataSource getDataSource(){
        return ds;
    }


    /**
     * 鑾峰彇杩炴帴Connection瀵硅薄
     */
    public static Connection getConnection() throws SQLException {
        return  ds.getConnection();
    }
}
