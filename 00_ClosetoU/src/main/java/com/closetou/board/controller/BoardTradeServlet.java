package com.closetou.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "boardtrade", urlPatterns = { "/boardtrade" })
public class BoardTradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardTradeServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 
		response.sendRedirect("./views/board/trade.jsp");
		
	}

}