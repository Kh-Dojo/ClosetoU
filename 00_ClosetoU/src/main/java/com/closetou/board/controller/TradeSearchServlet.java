package com.closetou.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.closetou.board.model.service.BoardService;

@WebServlet(name = "tradesearch", urlPatterns = { "/itemsearch" })
public class TradeSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TradeSearchServlet() {
	}

	//	거래 페이지에서 검색창을 이용해 검색했을 때 실행되는 서블릿
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 검색창의 결과값과 체크한 속성값을 매개변수로 받음.
		String search= request.getParameter("search");
		String[] attribute = request.getParameterValues("item_attribute");
	
		// 서비스의 의류 검색으로 넘김
		int result = new BoardService().searchItem();
		
		// 검색 결과가 하나도 없을 경우 검색결과가 없다고 출력하고 메인페이지로 돌아감
		if ( result < 1 ) {
				request.setAttribute("msg", "검색결과가 없습니다.");
				request.setAttribute("location", "/");
				request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
				
				return;
		} 
		
		// 검색 결과가 있을 경우 해당 값을 기반으로 페이지 구성
		
		// 메소드 필요(list의 형태로)
		
	}

}
