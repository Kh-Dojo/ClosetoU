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


@WebServlet(name = "communityBoardView", urlPatterns = { "/communityArticleView" })
public class communityArticleViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public communityArticleViewServlet() {
    }

    // 게시판에서 제목 클릭하면 상세 페이지 나오게 만들기
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		Article article = null;
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		/* System.out.println("게시글 번호 " + no); */
		
		// 내가 봤던 게시글의 번호가 들어가는 쿠키 생성. 쿠키가 존재하면 조회수 늘어나지 않게 만들기
		
				// 지금 이 상태 세션 기준! 세션 = 하나의 브라우저와 관련된 영역 session 내장 객체를 통해 접근할 수 있는 영역.
				// 때문에 로그아웃 상태, 계정1, 계정2를 다 각 개인으로 보지 않고 하나로 봐서 한 번 조회수가 올라가면 다른 계정으로 로그인해도 조회수가 증가되지 않음
				
				// 1. 쿠키에 게시글을 조회한 이력이 있는지 확인
					// 쿠키를 배열로 만들기
				Cookie[] cookies = request.getCookies();
				String boardHistory = "";
				boolean hasRead = false;

				if(cookies != null) {
					// String name = null;								1) if(name.equals("boardHistory"))의 name에 인라인 처리로 생략			
					
					for (Cookie cookie : cookies) {	// for( cookie에 담아주기 : cookies 배열의 개수만큼 반복해서) 
						// name = cookie.getName();	// 쿠키 이름 받아옴		2) if(name.equals("boardHistory"))의 name에 인라인 처리로 생략
						// System.out.println(name);		// 잘 받았나 확인 코드 없어도 됨		인라인 처리하면 불가능
						
						if(cookie.getName().equals("boardHistory")) {			// cookies의 이름(키값)이 boardHistory 이면
							boardHistory = cookie.getValue();					// ㄴ value를 가져와 boardHistory에 넣음
							
							if(boardHistory.contains("|" + no + "|")) {
								hasRead = true;						// 이미 본 상태면 true 뜸
								
								break;	// 반복문 끝나게(for문 빠져나가게 )break 걸어줌
							}
						}
					}
				}
				
				// 2. 읽지 않은 게시글이면(조회한 이력이 없으면) 쿠키에 기록
				if(!hasRead) {
					Cookie cookie = new Cookie("boardHistory", boardHistory + "|" + no + "|");
					// 개발자도구의 애플리케이션의 쿠키 선택 후 쿠키 이름, 값 조회 가능
					
					cookie.setMaxAge(-1);
					response.addCookie(cookie);
				}
		
		
		
		
		
		article = new ArticleService().getArticleByNoForCommunity(no, hasRead);
		
		/*
		 * System.out.println("아티클 출력!!!!!!!!" + article);
		 */		
		request.setAttribute("Article", article);
		request.getRequestDispatcher("/views/board/communityBoardView.jsp").forward(request, response);
		
		System.out.println(article);
	}

}
