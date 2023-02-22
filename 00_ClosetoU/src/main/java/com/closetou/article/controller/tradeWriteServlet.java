package com.closetou.article.controller;

import java.io.IOException;
import java.util.ArrayList;

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
import com.closetou.cloth.model.service.ClothService;
import com.closetou.cloth.model.vo.Cloth;
import com.closetou.cloth.model.vo.ClothCategory;
import com.closetou.common.util.FileRename;
import com.closetou.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;

@WebServlet(name = "tradewrite", urlPatterns = { "/article/tradeWrite" })
public class tradeWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public tradeWriteServlet() {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 로그인 체크 필터 필요

		ArrayList<ClothCategory> categorylist = new ArrayList<>();

		categorylist = new BoardService().getClothCategories();

		request.setAttribute("categorylist", categorylist);
		request.getRequestDispatcher("/views/board/tradeBoardWrite.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");

		String path = getServletContext().getRealPath("/resources/boardUpfile"); 

		int maxSize = 10485760;
		int recentNo;
		Article article = new Article();
		Cloth cloth = new Cloth();
		TradeArticle trart = new TradeArticle();
		
		
		// 파일 인코딩 설정
		String encoding = "UTF-8";

		MultipartRequest mr = new MultipartRequest(request, path, maxSize, encoding, new FileRename());

		article.setUserNo(loginMember.getNo());
		article.setUserNickname(loginMember.getNickname());
		article.setTitle(mr.getParameter("title"));
		article.setContent(mr.getParameter("content"));
		article.setRenamedFileName(mr.getFilesystemName("upfile")); 
		article.setOriginalFileName(mr.getOriginalFileName("upfile")); 
		article.setType(mr.getParameter("type"));

		// 게시글 내용 저장
		int articleSaveResult = new ArticleService().saveForTrade(article);

		if(articleSaveResult == 0) {
			request.setAttribute("msg", "거래글 등록 실패.");
			request.setAttribute("location", "/views/board/trade");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
		
		recentNo = new ArticleService().getMostRecentlyArticleNoByMemberNo(loginMember.getNo());
		
		// 의류 내용 저장
		cloth.setPhotoNo(mr.getFilesystemName("upfile"));
		cloth.setName((String)request.getAttribute("clothName"));
		cloth.setCatagory(request.getParameterValues("clothCategories"));		
		
		int clothSaveResult = new ClothService().saveCloth(cloth);
		
		
	}
}