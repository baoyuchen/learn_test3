package cn.jdbc.learn;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.web.domain.UserDao;
import com.web.utils.C3P0utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CPTest2 {
    public static void main(String[] args) throws SQLException {
        Connection conn= C3P0utils.getConnection();
        //ComboPooledDataSource dataSource = new ComboPooledDataSource();
        //Connection conn = dataSource.getConnection();
        String sql="select *from user";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        conn.setAutoCommit(false);
        ResultSet rs=pstmt.executeQuery();
        conn.commit();
        List<UserDao> list=new ArrayList<>();
        while(rs.next()){
            UserDao userDao=new UserDao();
            userDao.setUsername(rs.getString("username"));
            userDao.setPassword(rs.getString("password"));
            list.add(userDao);
        }
        Iterator<UserDao> it=list.iterator();
        while(it.hasNext()){
           UserDao userDao=it.next();
           System.out.println(userDao.getUsername()+"======="+userDao.getPassword());
        }
        C3P0utils.close(rs,pstmt,conn);
    }
}
