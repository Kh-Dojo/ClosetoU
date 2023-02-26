package com.closetou.article.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.closetou.article.model.service.ArticleService;
import com.closetou.member.model.vo.Member;

@WebServlet(name = "tradearticledelete", urlPatterns = { "/trade/article/delete" })
public class tradeArticleDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public tradeArticleDeleteServlet() {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");

		int no = Integer.parseInt(request.getParameter("no"));
		String user = request.getParameter("user");
		System.out.println(user);
		
		
		if (!(loginMember.getNickname().equals(user))) {
			request.setAttribute("msg", "잘못된 접근입니다.");
			request.setAttribute("location", "/views/board/trade");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			return;
		}


		int result = new ArticleService().delete(no);

		if (result > 0) {
			request.setAttribute("msg", "게시글 삭제 성공");
			request.setAttribute("location", "/views/board/trade");
		} else {
			request.setAttribute("msg", "게시글 삭제 실패");
			request.setAttribute("location", "/trade/article/view?no=" + no);
		}

		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);

	}

}
