package com.closetou.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.closetou.article.model.vo.Article;
import com.closetou.article.model.vo.TradeArticle;
import com.closetou.board.model.service.BoardService;

@WebServlet(name = "tradesearch", urlPatterns = { "/itemsearch" })
public class TradeSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TradeSearchServlet() {
	}

	// 거래 페이지에서 검색창을 이용해 검색했을 때 실행되는 서블릿
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("검색 서블릿 doPost 실행");
		
		// 검색창의 결과값과 체크한 속성값을 매개변수로 받음.
		String keyword = request.getParameter("search");
		String[] attribute = request.getParameterValues("item_attribute");
		
		// 결과를 받을 변수와 리턴되는 (페이지를 구성할) 아이템 개수를 받을 변수선언
		List<Article> list = null;
		List<TradeArticle> trList = null;

		// 의류 검색 서비스로 넘김 (반환값은 조회된 결과에 따른 TradeArticle 객체의 배열)
		list = new BoardService().searchArticle(keyword);
		trList = new BoardService().searchItem(keyword, attribute);

		// 검색 결과가 하나도 없을 경우 검색결과가 없다고 출력하고 메인페이지로 돌아감
		if (trList.isEmpty() && list.isEmpty()) {
			request.setAttribute("msg", "검색결과가 없습니다.");
			request.setAttribute("location", "/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);

			return;
		}

		// 검색 결과가 있을 경우 해당 값을 기반으로 페이지 구성
		request.setAttribute("list", list);
		request.setAttribute("trlist", trList);
		request.getRequestDispatcher("./views/board/tradelist.jsp").forward(request, response);
		
	}

}
