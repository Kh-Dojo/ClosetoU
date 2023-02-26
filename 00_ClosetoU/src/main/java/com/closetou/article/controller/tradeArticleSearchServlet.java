package com.closetou.article.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.closetou.board.model.service.BoardService;
import com.closetou.common.util.PageInfo;

@WebServlet(name = "tradearticlesearch", urlPatterns = { "/trade/article/search" })
public class tradeArticleSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public tradeArticleSearchServlet() {
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int page = 0;
    	int listCount = 0;
    	PageInfo pageInfo = null;
    	
    	try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			page = 1;
		}
    	
    	
    	request.setCharacterEncoding("utf-8");
		
    	String keyword = (String) request.getParameter("keyword");
    	System.out.println(keyword);
    	listCount = new BoardService().getBoardCountBySearchKeyword(keyword);
		
    	System.out.println(listCount);
    	pageInfo = new PageInfo(page, 10, listCount, 15);
    
    	request.setAttribute("msg", "검색결과를 확인하세요");
		request.setAttribute("location", "/views/board/trade");
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
    
    }

}
