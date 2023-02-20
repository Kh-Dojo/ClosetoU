package com.closetou.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.closetou.article.model.service.ArticleService;
import com.closetou.article.model.vo.Article;
import com.closetou.article.model.vo.TradeArticle;
import com.closetou.board.model.service.BoardService;
import com.closetou.common.util.PageInfo;

@WebServlet(name = "boardtrade", urlPatterns = { "/views/board/trade", "/views/board/tradelist" })
public class BoardTradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardTradeServlet() {
	}

	// 거래 메인 페이지로 보내는 메소드입니다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 메인페이지 정보를 읽어 오기 위한 변수들
		int page = 0;
		int listCount = 0;
		PageInfo pageInfo = null;

		try {
			Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			page = 1;
		}

		listCount = new BoardService().getBoardCount();
		pageInfo = new PageInfo(page, 10, listCount, 15);

		request.setAttribute("pageInfo", pageInfo);

		request.getRequestDispatcher("/views/board/tradelist.jsp").forward(request, response);

	}

}
