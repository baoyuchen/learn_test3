package cn.jdbc.learn;

import com.web.domain.UserDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JDBCDemo3 {
    public static void main(String[] args) throws SQLException {
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
            String sql="select *from user";
            pstmt=conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            rs=pstmt.executeQuery();
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
            Iterator<UserDao> it=list.iterator();
            while(it.hasNext()){
                UserDao userDao= it.next();
                System.out.println(userDao.getUsername()+"=================="+userDao.getPassword());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(conn!=null) conn.close();
            if(pstmt!=null) pstmt.close();
        }
    }
}
