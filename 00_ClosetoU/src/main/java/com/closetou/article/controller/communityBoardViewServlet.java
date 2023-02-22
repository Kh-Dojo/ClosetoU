package com.closetou.article.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.closetou.article.model.service.ArticleService;
import com.closetou.article.model.vo.Article;


@WebServlet(name = "communityBoardView", urlPatterns = { "/communityBoardView" })
public class communityBoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public communityBoardViewServlet() {
    }

    // 게시판에서 제목 클릭하면 상세 페이지 나오게 만들기
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		Article article = null;
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		System.out.println("게시글 번호 " + no);
		
		article = new ArticleService().getArticleByNoForCommunity(no);
		
		request.setAttribute("Article", article);
		request.getRequestDispatcher("/views/board/communityBoardView.jsp").forward(request, response);
		
		System.out.println(article);
	}

}
