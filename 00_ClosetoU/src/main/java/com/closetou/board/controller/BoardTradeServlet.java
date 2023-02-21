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
import com.closetou.board.model.vo.SideMenu;
import com.closetou.common.util.PageInfo;

@WebServlet(name = "boardtrade", urlPatterns = { "/views/board/trade" })
public class BoardTradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardTradeServlet() {
	}

	// 거래 메인 페이지로 보내는 메소드입니다.
	@SuppressWarnings("null")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 메인페이지 정보를 읽어 오기 위한 변수들
		int page = 0;
		int listCount = 0;
		PageInfo pageInfo = null;
		List<Article> list = null;
		List<TradeArticle> trlist = null;
		SideMenu sideMenu = new SideMenu ("중고의류 거래 및 나눔", new String[] {"서브메뉴1", "서브메뉴2"} );
		

		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			page = 1;
		}

		System.out.println(page);

		listCount = new BoardService().getBoardCountForTrade();
		pageInfo = new PageInfo(page, 10, listCount, 15);
		list = new BoardService().getArticleForTradeList(pageInfo);

		System.out.println(list);

		ArrayList<Integer> numbers = new ArticleService().noFromArticle(list);

		System.out.println(numbers);

//		if (numbers != null) {
//
//			for (Integer no : numbers) {
//				TradeArticle tr = new ArticleService().getTradeArticleByNo(no);
//				trlist.add(tr);
//			}
//
//		}
//
//		System.out.println(trlist);

		request.setAttribute("sideMenu", sideMenu);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("list", list);
//		request.setAttribute("trlist", trlist);
		request.getRequestDispatcher("/views/board/trade.jsp").forward(request, response);
	};

}
