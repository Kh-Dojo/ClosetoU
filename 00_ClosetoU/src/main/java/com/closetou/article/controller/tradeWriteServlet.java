package com.closetou.article.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.taglibs.standard.tag.common.fmt.RequestEncodingSupport;

import com.closetou.article.model.service.ArticleService;
import com.closetou.article.model.vo.Article;
import com.closetou.article.model.vo.TradeArticle;
import com.closetou.board.model.service.BoardService;
import com.closetou.cloth.model.service.ClothService;
import com.closetou.cloth.model.vo.Cloth;
import com.closetou.cloth.model.vo.ClothCategory;
import com.closetou.common.jdbc.JDBCTemplate;
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

		// 최대 100MB;
		int maxSize = 104857600;
		Article article = new Article();
		Cloth cloth = new Cloth();
		TradeArticle trart = new TradeArticle();

		// 파일 인코딩 설정
		String encoding = "UTF-8";

		MultipartRequest mr = new MultipartRequest(request, path, maxSize, encoding, new FileRename());

		// article 객체 세팅
		article.setUserNo(loginMember.getNo());
		article.setUserNickname(loginMember.getNickname());
		article.setTitle(mr.getParameter("title"));
		article.setContent(mr.getParameter("content"));
		article.setRenamedFileName(mr.getFilesystemName("cloth_upfile"));
		System.out.println(article.getRenamedFileName());
		article.setOriginalFileName(mr.getOriginalFileName("cloth_upfile"));
		article.setType("거래");

		// cloth 내용 세팅
		cloth.setPhotoNo(mr.getFilesystemName("cloth_upfile"));
		cloth.setName(mr.getParameter("cloth_name"));
		cloth.setCatagory(mr.getParameterValues("clothcategory"));

		// trart 내용 세팅
		trart.setPrice(Integer.parseInt(mr.getParameter("price")));
		if (Integer.parseInt(mr.getParameter("price")) == 0) {
			trart.setFree("Y");
		} else {
			trart.setFree("N");
		}
		trart.setTradeMethod(mr.getParameter("trademethod"));
		trart.setLocation(mr.getParameter("location"));

		// 게시글 내용 저장
		int tradeArticleSaveResult = new ArticleService().saveForTrade(article, cloth, trart);

		if (tradeArticleSaveResult == 0) {
			request.setAttribute("msg", "거래글 등록 실패");
			request.setAttribute("location", "/article/tradeWrite");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
		request.setAttribute("msg", "거래글 등록에 성공했습니다.");
		request.setAttribute("location", "/views/board/trade");
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}
}