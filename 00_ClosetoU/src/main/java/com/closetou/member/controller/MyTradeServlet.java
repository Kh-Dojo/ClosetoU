package com.closetou.member.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.closetou.article.model.service.ArticleService;
import com.closetou.article.model.vo.Article;
import com.closetou.article.model.vo.TradeArticle;
import com.closetou.board.model.service.BoardService;
import com.closetou.common.util.PageInfo;
import com.closetou.member.model.service.MemberService;
import com.closetou.member.model.vo.Member;

@WebServlet(name = "myTrade", urlPatterns = { "/myTrade" })
public class MyTradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyTradeServlet() {
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		request.setCharacterEncoding("UTF-8");
		
    		HttpSession session = request.getSession(false);
    		Member loginMember = session == null ? null : (Member)session.getAttribute("loginMember");
    		
    		int memNo = 0;
    		int page = 0;
 			int listCount = 0;
 			PageInfo pageInfo = null;
 			List<Article> list = null;
 			
 			memNo = loginMember.getNo();
 			
 			try {
 				page = Integer.parseInt(request.getParameter("page"));
 			} catch (NumberFormatException e) {
 				page = 1;
 			}
 			
 			listCount = new MemberService().getBoardCountForTrade(memNo);
 			pageInfo = new PageInfo(page, 10, listCount, 10);
 			
 			list = new MemberService().getArticleForTradeList(pageInfo, memNo);
 			
 			ArrayList<Integer> numbers = new ArticleService().noFromArticle(list);	
 			
 			request.setAttribute("pageInfo", pageInfo);
 			request.setAttribute("list", list);
 			request.getRequestDispatcher("/views/member/myTrade.jsp").forward(request, response);
	}

}
