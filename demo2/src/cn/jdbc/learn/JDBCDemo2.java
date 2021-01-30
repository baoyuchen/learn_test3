package cn.jdbc.learn;
import com.web.domain.UserDao;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class JDBCDemo2 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
        String sql="select *from user";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        conn.setAutoCommit(false);
        ResultSet rs=pstmt.executeQuery();
        conn.commit();
        List list=new ArrayList<UserDao>();
        while(rs.next()){
            String username=rs.getString("username");
            String password=rs.getString("password");
            UserDao userDao=new UserDao();
            userDao.setUsername(username);
            userDao.setPassword(password);
            list.add(userDao);
        }
        conn.close();
        pstmt.close();
        Iterator<UserDao> it=list.iterator();
        while(it.hasNext()){
            UserDao userDao= it.next();
            System.out.println(userDao.getUsername()+"=================="+userDao.getPassword());
        }
    }
}
