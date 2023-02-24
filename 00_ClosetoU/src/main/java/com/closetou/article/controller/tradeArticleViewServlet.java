package com.closetou.article.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.closetou.article.model.service.ArticleService;
import com.closetou.article.model.vo.Article;
import com.closetou.article.model.vo.TradeArticle;
import com.closetou.cloth.model.service.ClothService;
import com.closetou.cloth.model.vo.Cloth;
import com.closetou.member.model.service.MemberService;
import com.closetou.member.model.vo.Member;

@WebServlet(name = "tradearticleview", urlPatterns = { "/trade/article/view" })
public class tradeArticleViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public tradeArticleViewServlet() {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		Article article = null;
		TradeArticle trart = null;
		Member member = null;

		int no = Integer.parseInt(request.getParameter("no"));

		Cookie[] cookies = request.getCookies();
		String boardHistory = "";
		boolean hasRead = false;

		if (cookies != null) {

			for (Cookie cookie : cookies) {

				if (cookie.getName().equals("boardHistory")) {
					boardHistory = cookie.getValue();

					if (boardHistory.contains("|" + no + "|")) {
						hasRead = true;

						break;
					}
				}
			}
		}

		if (!hasRead) {
			Cookie cookie = new Cookie("boardHistory", boardHistory + "|" + no + "|");
			cookie.setMaxAge(-1);
			response.addCookie(cookie);
		}

		article = new ArticleService().getArticleByNoForTrade(no, hasRead);
		trart = new ArticleService().getTradeArticleByNo(no);

		member = new MemberService().findMemberByNo(article.getUserNo());

		request.setAttribute("article", article);

		System.out.println(article);

		request.setAttribute("trart", trart);
		request.setAttribute("member", member);

		request.getRequestDispatcher("/views/board/tradeBoardView.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
