package com.closetou.article.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.closetou.article.model.service.ArticleService;

// 댓글 삭제 서블릿
@WebServlet(name = "communityReplyDelete", urlPatterns = { "/article/replyDelete" })
public class communityReplyDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public communityReplyDeleteServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 댓글번호 가져오기
		int result = 0;
		
		int articleNo = Integer.parseInt(request.getParameter("articleNo"));	// 게시글 번호
		int replyNo = Integer.parseInt(request.getParameter("replyNo"));		// 댓글 번호
		
//		System.out.println("게시글 번호 : " + articleNo + "댓글번호 : " + replyNo);
		
		result = new ArticleService().deleteReply(articleNo, replyNo);
		// BoardService의 delete()메소드에서 작업
	
		if( result > 0 ) {
			request.setAttribute("msg", "댓글 삭제 성공");
			request.setAttribute("location", "/communityBoardView?no=" + articleNo); // 게시글 목록으로 가게 해줌
		} else {
			request.setAttribute("msg", "게시글 삭제 실패");
			request.setAttribute("location", "/communityBoardView?no=" + articleNo);
		}
	
										// r 매개값으로 포워딩 시켜주려는 view의 이름 넣어줌
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}


}
