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
import com.closetou.board.model.service.BoardService;
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

		//로그인 체크 필터 필요
		
		ArrayList<ClothCategory> categorylist = new ArrayList<>();

		categorylist = new BoardService().getClothCategories();

		request.setAttribute("categorylist", categorylist);
		request.getRequestDispatcher("/views/board/tradeBoardWrite.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 첨부파일 넣으면 서버로 전달

		/*
		 * // 사용자가 보낸 파라미터값 받기 System.out.println(request.getParameter("title")); //
		 * wirte.jsp의 50행 name System.out.println(request.getParameter("writer"));
		 * System.out.println(request.getParameter("upfile")); // 이렇게 하면 파일을 보내는 게 아니라
		 * 파일명만 보냄 System.out.println(request.getParameter("content"));
		 */

//		    	MultipartRequest mr = new MultipartRequest(request, getServletName(), 0, getServletInfo(), null);

		HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");

		if (loginMember != null) { // 로그인 상태

			// 파일이 저장될 경로 얻어오기
			String path = getServletContext().getRealPath("/resources/boardUpfile"); // / = 현재 웹 애플리케이션에서 webapp에 해당

			// 파일의 최대 사이즈 지정(10MB로 지정)
			// 사이즈 지정은 바이트 단위로 한다. 10485760byte
			int maxSize = 10485760;

			// 파일 인코딩 설정
			String encoding = "UTF-8";

	
			MultipartRequest mr = new MultipartRequest(request, path, maxSize, encoding, new FileRename());

			Article article = new Article();

			article.setUserNo(loginMember.getNo());
			article.setUserNickname(loginMember.getNickname());
			article.setTitle(mr.getParameter("title"));
			article.setContent(mr.getParameter("content"));
			article.setRenamedFileName(mr.getFilesystemName("upfile")); // 클라이언트가 파일을 선택해서 올린 이름 ex) 정처기해설집
			article.setOriginalFileName(mr.getOriginalFileName("upfile")); // 실제 서버에 업로드(저장) 될 때 파일 이름 ex)
			// 두 개의 파일명을 다 저장해야 함. 서버 저장용, 클라이언트에게 다시 보낼 때 사용하는 용
			article.setType(mr.getParameter("type"));

			int result = new ArticleService().save(article);

			if (result > 0) { // 게시글 작성 성공
				request.setAttribute("msg", "게시글 등록 성공.");
				request.setAttribute("location", "/board/communityBoardList");
			} else { // 게시글 작성 실패
				request.setAttribute("msg", "게시글 등록 실패.");
				request.setAttribute("location", "/board/communityBoardList");
			}

		} else { // 로그인 미상태
			request.setAttribute("msg", "로그인 후 수정해 주세요.");
			request.setAttribute("location", "/");
		}

		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);

	}

}
