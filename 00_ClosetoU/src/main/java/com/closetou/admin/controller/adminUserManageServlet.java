package com.closetou.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.closetou.member.model.vo.Member;

@WebServlet(name = "userManage", urlPatterns = { "/admin/userManage" })
public class adminUserManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Member memberService;
       
    public adminUserManageServlet() {
    	memberService = new Member();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("users", memberService.getAllUsers());
		request.getRequestDispatcher("/00_ClosetoU/views/admin/userManage.jsp").forward(request, response);
	}
}
