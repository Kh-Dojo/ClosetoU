package com.closetou.article.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.closetou.board.model.service.BoardService;
import com.closetou.cloth.model.vo.ClothCategory;

@WebServlet(name = "tradewrite", urlPatterns = { "/article/tradeWrite" })
public class tradeWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public tradeWriteServlet() {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<ClothCategory> categorylist = new ArrayList<>();

		categorylist = new BoardService().getClothCategories();

		request.setAttribute("categorylist", categorylist);
		request.getRequestDispatcher("/views/board/tradeBoardWrite.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
