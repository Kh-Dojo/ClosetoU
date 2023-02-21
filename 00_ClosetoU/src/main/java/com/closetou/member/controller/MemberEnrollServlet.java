package com.closetou.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.closetou.member.model.service.MemberService;
import com.closetou.member.model.vo.Member;

@WebServlet(name = "memberEnroll", urlPatterns = { "/member/enroll" })
public class MemberEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MemberEnrollServlet() {
    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// 회원가입 페이지로 포워드
    	request.getRequestDispatcher("/views/member/enroll.jsp").forward(request, response);
	}
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
		
		Member member = new Member();
		
		member.setId(request.getParameter("userId"));
		member.setPassword(request.getParameter("userPwd"));
		member.setName(request.getParameter("userName"));
		member.setNickname(request.getParameter("userNickname"));
		member.setPhone(request.getParameter("phone"));
		member.setEmail(request.getParameter("email"));
		member.setAddress(request.getParameter("address"));
		member.setAddress_detail(request.getParameter("address_detail"));
		
		
		System.out.println(member);
		
		int result = new MemberService().save(member);
		
		System.out.println(result);
		
		if(result > 0) {
			// 회원 가입 완료
			request.setAttribute("msg", "회원 가입에 성공하였습니다.");
			request.setAttribute("location", "/");
		} else {
			request.setAttribute("msg", "회원 가입에 실패하였습니다.");
			request.setAttribute("location", "/member/enroll");			
		}
		
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);

	}

}
