package com.closetou.admin.controller;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "userManage", urlPatterns = { "/admin/userManage" })
public class adminUserManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public adminUserManageServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/00_ClosetoU/views/admin/userManage.jsp").forward(request, response);
	}
}
