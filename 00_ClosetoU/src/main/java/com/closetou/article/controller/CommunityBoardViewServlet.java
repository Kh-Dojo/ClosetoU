package com.closetou.article.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.closetou.article.model.vo.Article;


@WebServlet(name = "communityBoardView", urlPatterns = { "/communityBoardView" })
public class CommunityBoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public CommunityBoardViewServlet() {
    }

    // 게시판에서 제목 클릭시 게시글 상세페이지 나타나게 링크 걸기
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Article article = null;
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		System.out.println("게시글 번호 " + no);
		
	}

}
