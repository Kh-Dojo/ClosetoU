package com.closetou.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.closetou.member.model.vo.Member;

@WebServlet(name = "myPage", urlPatterns = { "/member/myPage" })
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MyPageServlet() {
    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
		
		if(loginMember != null) {
			request.getRequestDispatcher("/views/member/myPage.jsp").forward(request, response);			
		} else {
			request.setAttribute("msg", "마이 페이지에 접속하려면 로그인 먼저 해 주세요.");
			request.setAttribute("location", "/");
			
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
    
    }

}
