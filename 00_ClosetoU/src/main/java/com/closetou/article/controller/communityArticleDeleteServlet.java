package com.closetou.article.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.closetou.article.model.service.ArticleService;

// 게시글 삭제
@WebServlet(name = "communityArticleDelete", urlPatterns = { "/article/delete" })
public class communityArticleDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public communityArticleDeleteServlet() {
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 로그인 체크 & 본인 게시글 여부 확인(직접 적용시켜 보기)
		
				// no 값 가져오기. 현재 게시글의 번호
				int no = Integer.parseInt(request.getParameter("no"));
				
				// no 값이 잘 출력되는지 확인
				// System.out.println("게시글 번호 : " + no);
				
				// 0222 9시 18분 게시글번호 잘받아옴
				
				// 게시글 삭제하기
				int result = new ArticleService().delete(no);
					// BoardService의 delete()메소드에서 작업
				
				if( result > 0 ) {
					request.setAttribute("msg", "게시글 삭제 성공");
					request.setAttribute("location", "/board/communityBoardList"); // 게시글 목록으로 가게 해줌
				} else {
					request.setAttribute("msg", "게시글 삭제 실패");
					request.setAttribute("location", "/communityBoardView?no=" + no);
				}

												// r 매개값으로 포워딩 시켜주려는 view의 이름 넣어줌
				request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

}
