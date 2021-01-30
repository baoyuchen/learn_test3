package cn.jdbc.learn;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.Connection;
import javax.sql.DataSource;
public class C3POTest1 {
    public static ComboPooledDataSource dataSource=new ComboPooledDataSource("c3p0");
    /*
     * 获得数据源(连接池)
     */
    public static DataSource getDataSource() {
        return dataSource;
    }
    //获得连接
    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException(e);
        }
    }
}
