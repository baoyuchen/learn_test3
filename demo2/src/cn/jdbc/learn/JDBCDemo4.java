package cn.jdbc.learn;

import com.web.domain.UserDao;
import com.web.utils.JDBCutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JDBCDemo4 {
    public static void main(String[] args) throws SQLException {
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        conn= JDBCutils.getConnection();
        String sql="select *from user";
        conn.setAutoCommit(false);
        pstmt=conn.prepareStatement(sql);
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
        JDBCutils.close(rs,pstmt,conn);
    }
}
