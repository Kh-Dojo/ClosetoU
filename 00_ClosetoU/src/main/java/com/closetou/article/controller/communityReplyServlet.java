package com.closetou.article.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.closetou.article.model.service.ArticleService;
import com.closetou.article.model.vo.Reply;
import com.closetou.member.model.vo.Member;

//댓글 작성 서블릿
@WebServlet("/article/reply")
public class communityReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public communityReplyServlet() {
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int result = 0;
		
		// BoardUpdateServlet에서 로그인멤버얻어오는 코드 가져오기
		HttpSession session = request.getSession(false);	//requst의 getSession()메서드의 피라미터로 false를 전달하면 이미 생성된 세션이 있을 때 그 세션을 반환하고 없으면 null을 반환한다.
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
				
		int articleNo = Integer.parseInt(request.getParameter("articleNo"));
		String content = request.getParameter("content");
		
		// System.out.println(articleNo + ", " + content); // 잘 넘어가는지 확인
		
		Reply reply = new Reply();
		
		reply.setArticleNo(articleNo);
		reply.setUserNo(loginMember.getNo());
		reply.setContent(content);
		
		
		result = new ArticleService().saveReply(reply);
		
		if(result > 0 ) {
			request.setAttribute("msg", "댓글 등록 성공");
			request.setAttribute("location", "/communityArticleView?no=" + articleNo);
		} else {
			request.setAttribute("msg", "댓글 등록 실패");
			request.setAttribute("location", "/communityArticleView?no=" + articleNo);
		}
		
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

}


