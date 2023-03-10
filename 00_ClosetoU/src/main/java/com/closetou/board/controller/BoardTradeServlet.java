package com.closetou.board.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.closetou.article.model.service.ArticleService;
import com.closetou.article.model.vo.Article;
import com.closetou.article.model.vo.TradeArticle;
import com.closetou.board.model.service.BoardService;
import com.closetou.cloth.model.service.ClothService;
import com.closetou.cloth.model.vo.Cloth;
import com.closetou.cloth.model.vo.ClothCategory;
import com.closetou.common.util.PageInfo;
import com.google.gson.Gson;

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
		List<Cloth> cllist = null;
		
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			page = 1;
		}

		
		// DB의 게시물을 구성해 페이지바 구성
		listCount = new BoardService().getBoardCountForTrade();
		pageInfo = new PageInfo(page, 10, listCount, 15);
		list = new BoardService().getArticleForTradeList(pageInfo);

		// 가져온 게시물들의 PK값을 기준으로 타테이블에 있는 속성들을 가져옴
		
		ArrayList<Integer> numbers = new ArticleService().noFromArticle(list);	
		trlist = new ArticleService().getTradeArticleByNos(numbers);
		
		//카테고리 설정
		ArrayList<ClothCategory> categorylist = new ArrayList<>();
		categorylist = new BoardService().getClothCategories();
		
		//의류 대표 사진설정
		cllist = new ClothService().getClothes(trlist);
		
//		Gson json = new Gson();
//		
//		String alistjson = URLEncoder.encode(json.toJson((list.toString())), "UTF-8").replaceAll("\\+", "%20");
//		String trlistjson = URLEncoder.encode(json.toJson((trlist.toString())), "UTF-8").replaceAll("\\+", "%20");
//		String cllistjson =  URLEncoder.encode(json.toJson((cllist.toString())), "UTF-8").replaceAll("\\+", "%20");
//		
//		HttpSession session = request.getSession();
//		session.setAttribute("alistjson", alistjson);
//		session.setAttribute("trlistjson", trlistjson);
//		session.setAttribute("cllistjson", cllistjson);
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("list", list);
		request.setAttribute("trlist", trlist);
		request.setAttribute("cllist", cllist);
		request.setAttribute("categorylist", categorylist);
		request.getRequestDispatcher("/views/board/trade.jsp").forward(request, response);
	};
}
