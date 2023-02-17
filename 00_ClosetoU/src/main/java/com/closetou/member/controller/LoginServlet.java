package com.closetou.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.closetou.member.model.service.MemberService;
import com.closetou.member.model.vo.Member;

@WebServlet(name = "login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public LoginServlet() {
    }
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = null;
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		String userRole = request.getParameter("userRole");
		
		System.out.println(userId + ", " + userPwd);
		
		Member loginMember = new MemberService().login(userId, userPwd);
		
		System.out.println(loginMember);
		
		if(loginMember != null) {
			// loginmember 세션에 저장
			session = request.getSession();
			
			session.setAttribute("loginMember", loginMember);

			// 로그인이 완료되면 홈 화면으로 이동시킨다.
			response.sendRedirect(request.getContextPath() + "/");
		} else {
			// 로그인 실패에 대한 메시지를 띄워주고 홈 화면으로 이동시킨다.
			
			// 1. 공용으로 사용하는 에러 메세지 출력 페이지에
			//	  전달할 메시지와 메시지 출력 후 이동할 페이지를 request 객체에 저장한다.
			request.setAttribute("msg", "아이디나 비밀번호 틀렸지롱~");
			request.setAttribute("location", "/");
			
			// 2. request 객체의 데이터를 유지해서
			//	  에러 메시지를 출력 페이지에 전달하기 위해 forward를 실행한다.
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			
		}
		
		
	}

}
