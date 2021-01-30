package com.web.servlet;

import com.web.utils.Druidutils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/regist")
public class ResigstServlet extends HttpServlet {
    static Connection conn=null;
    static PreparedStatement pstmt=null;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        System.out.println(username+"======="+password);
        String sql="insert into user values (null,?,?)";
        try {
            conn= Druidutils.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            conn.setAutoCommit(false);
            int col=pstmt.executeUpdate();
            conn.commit();
            if(col>0){
                //注册成功
                resp.setContentType("text/html;charset=utf-8");
                resp.getWriter().write("注册成功");
            }else{
                //注册失败
                resp.setContentType("text/html;charset=utf-8");
                resp.getWriter().write("注册失败");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            Druidutils.close(pstmt,conn);
        }

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
