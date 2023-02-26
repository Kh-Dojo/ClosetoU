package com.closetou.article.controller;

import java.io.File;
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
import com.closetou.cloth.model.service.ClothService;
import com.closetou.cloth.model.vo.Cloth;
import com.closetou.cloth.model.vo.ClothCategory;
import com.closetou.cloth.model.vo.ClothPhoto;
import com.closetou.common.util.FileRename;
import com.closetou.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;

@WebServlet(name = "tradearticleupdate", urlPatterns = { "/trade/article/update" })
public class tradeArticleUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public tradeArticleUpdateServlet() {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Article article = null;
		TradeArticle trart = null;
		List<ClothPhoto> clphs = null;

		// 로그인 멤버 정보 가져오기
		HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");

		// 게시글 번호 확인
		int no = Integer.parseInt(request.getParameter("no"));
		article = new ArticleService().getArticleByNoForTrade(no, true);

		// 카테고리 리스트 확인
		ArrayList<ClothCategory> categorylist = new ArrayList<>();
		categorylist = new BoardService().getClothCategories();

		request.setAttribute("categorylist", categorylist);

		// 거래 게시글 확인
		trart = new ArticleService().getTradeArticleByNo(no);

		// 사진 확인
		clphs = new ClothService().getClothPhotosbyClothNo(trart.getClothNumber());
		ClothPhoto clph = new ClothPhoto();

		if (article != null && loginMember.getNickname().equals(article.getUserNickname())) {
			request.setAttribute("article", article);
			request.setAttribute("trart", trart);
			request.setAttribute("clothphoto", clph);
			request.setAttribute("clothphotos", clphs);
			request.getRequestDispatcher("/views/board/tradeBoardUpdate.jsp").forward(request, response);
		} else {
			request.setAttribute("msg", "잘못된 접근입니다.");
			request.setAttribute("location", "/views/board/trade");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");

		String path = getServletContext().getRealPath("/resources/clothImages");

		// 최대 100MB;
		int maxSize = 104857600;
		Article article = new Article();
		Cloth cloth = new Cloth();
		ClothPhoto cloph = new ClothPhoto();
		TradeArticle trart = new TradeArticle();

		// 파일 인코딩 설정
		String encoding = "UTF-8";
		MultipartRequest mr = new MultipartRequest(request, path, maxSize, encoding, new FileRename());

		// article 객체 세팅
		article.setNo(Integer.parseInt(mr.getParameter("articleno")));
		article.setTitle(mr.getParameter("title"));
		article.setContent(mr.getParameter("content"));

		// cloth 내용 세팅
		cloth.setNo(Integer.parseInt(mr.getParameter("clothno")));
		cloth.setCategory(mr.getParameterValues("clothcategory"));

		// trart 내용 세팅
		trart.setNo(Integer.parseInt(mr.getParameter("articleno")));
		trart.setPrice(Integer.parseInt(mr.getParameter("price")));
		if (Integer.parseInt(mr.getParameter("price")) == 0) {
			trart.setFree("Y");
		} else {
			trart.setFree("N");
		}
		trart.setTradeMethod(mr.getParameter("trademethod"));
		trart.setLocation(mr.getParameter("location"));

		// 게시글 내용 저장
		int tradeArticleUpdateResult = new ArticleService().updateForTrade(article, cloth, trart);

		if (tradeArticleUpdateResult == 0) {
			request.setAttribute("msg", "거래글 수정 실패");
			request.setAttribute("location", "/trade/article/view?no=" + Integer.parseInt(mr.getParameter("articleno")));
//			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
		
		request.setAttribute("msg", "거래글 수정에 성공했습니다.");
		request.setAttribute("location", "/views/board/trade");
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}
}
