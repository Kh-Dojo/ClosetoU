package com.closetou.admin.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.closetou.member.model.vo.Member;

@WebServlet(name = "userManage", urlPatterns = { "/admin/userManage" })
public class adminUserManageServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "CLOSETOU", "CLOSETOU");

            String sql = "UPDATE SET enroll = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            request.setAttribute("members", rs);

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("00_ClosetoU/views/admin/userManage.jsp");
        dispatcher.forward(request, response);
    }
}
