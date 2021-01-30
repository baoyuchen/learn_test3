package com.web.servlet;
import com.web.domain.UserDao;
import com.web.utils.Druidutils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@WebServlet("/login")
public class ServletDemo2 extends HttpServlet {
    static Connection conn=null;
    static PreparedStatement pstmt=null;
    static ResultSet rs=null;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.设置编码
        req.setCharacterEncoding("utf-8");
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        System.out.println(username+"======="+password);
        String sql="select *from user where username=? and password=?";
        try {
            conn= Druidutils.getConnection();
            pstmt=conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            rs=pstmt.executeQuery();//不可以用判空来确定用户是否存在的问题
            conn.commit();
            if(rs.next()){
                //登录成功
                UserDao loginUser=new UserDao();
                loginUser.setUsername(username);
                loginUser.setPassword(password);
                req.setAttribute("user",loginUser);
                //转发操作(转发操作需要记住)
                req.getRequestDispatcher("/successServlet").forward(req,resp);
            }else{
                //登录失败
                req.getRequestDispatcher("/failServlet").forward(req,resp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            Druidutils.close(pstmt,conn);
        }

    }
}
