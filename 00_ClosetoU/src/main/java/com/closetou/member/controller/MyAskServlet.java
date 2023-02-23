package com.closetou.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.closetou.article.model.vo.Article;
import com.closetou.common.util.PageInfo;
import com.closetou.member.model.service.MemberService;
import com.closetou.member.model.vo.Member;

@WebServlet(name = "myAsk", urlPatterns = { "/myAsk" })
public class MyAskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MyAskServlet() {
    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	
    	HttpSession session = request.getSession(false);
		Member loginMember = session == null ? null : (Member)session.getAttribute("loginMember");
		
		int memNo = 0;
		int page = 0;
		int listCount = 0;
		PageInfo pageInfo = null;
		
		List<Article> list = null;
		
		memNo = loginMember.getNo();
		System.out.println(memNo);
		
		try {
			page = Integer.parseInt(request.getParameter("page"));			
		} catch (NumberFormatException e) {
			page = 1;
		}
		
		listCount = new MemberService().getBoardAsk(loginMember.getNo());
		pageInfo= new PageInfo(page, 10, listCount, 10);	// 한 페이지에 몇 개의 글 나오게 할 지 지정하는 메소드
		
		list = new MemberService().getArticleAsk(pageInfo, loginMember.getNo());
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/views/member/myAsk.jsp").forward(request, response);
    
    }
    

}
