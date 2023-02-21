package com.closetou.article.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.closetou.article.model.service.ArticleService;
import com.closetou.article.model.vo.Article;
import com.closetou.member.model.vo.Member;


@WebServlet(urlPatterns = {"/article/qnawrite"})
public class qnaWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public qnaWriteServlet() {
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		//로그인체크
//		HttpSession session = request.getSession(false);
//		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
//		
//		if (loginMember != null) {
//	         request.getRequestDispatcher("/views/board/qnaWrite.jsp").forward(request, response);
//	      } else {   // 로그인 미상태
//	         request.setAttribute("msg", "로그인 후 문의해 주세요.");
//	         request.setAttribute("location", "/");
//	         request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
//	      }
//		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
		
		if (loginMember != null) {	// 로그인체크
	        int result = 0;
	        
	        String name = request.getParameter("name");
	        String phone = request.getParameter("phone");
	        String title = request.getParameter("title");	        
			String content = request.getParameter("content");
			
//			System.out.println(name + ", " + phone + ", " + title + ", " + content);
			
			Article article = new Article();
			
			article.setNo(loginMember.getNo());
			article.setTitle(title);
			article.setContent(content);
			
			result = new ArticleService().saveQna(article);
			
			if ( result > 0 ) {
				request.setAttribute("msg", "문의 등록 성공");
				request.setAttribute("location", "/");
			} else {
				request.setAttribute("msg", "문의 등록 실패");
				request.setAttribute("location", "/views/board/qnaWrite.jsp");
			}

			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			
	      } else {   // 로그인 미상태
	         request.setAttribute("msg", "로그인 후 문의해 주세요.");
	         request.setAttribute("location", "/");
	         request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	      }
	}

}
