package com.closetou.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.closetou.article.model.vo.Article;
import com.closetou.board.model.service.BoardService;
import com.closetou.common.util.PageInfo;


@WebServlet(urlPatterns = {"/board/communityBoardList"})
public class CommunityBoardListServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public CommunityBoardListServelt() {
    }
    
    // 자유게시판 게시글 리스트에 담아 게시글 목록 가져오기
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int page = 0;
		int listCount = 0;
		PageInfo pageInfo = null;
		
		List<Article> list = null;
		
		try {
			page = Integer.parseInt(request.getParameter("page"));			
		} catch (NumberFormatException e) {
			page = 1;
		}
		
		listCount = new BoardService().getBoardCountForCommunity();
		pageInfo= new PageInfo(page, 10, listCount, 10);	// 한 페이지에 몇 개의 글 나오게 할 지 지정하는 메소드
		
		/*
		 * System.out.println(listCount);
		 */
		list = new BoardService().getArticleForCommunity(pageInfo);
		
		
		System.out.println("list : " + list);
		/* System.out.println(page); */
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/views/board/communityBoardList.jsp").forward(request, response);
	}

}
