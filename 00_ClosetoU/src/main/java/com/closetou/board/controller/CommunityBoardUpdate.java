package com.closetou.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 게시물 수정 버튼

@WebServlet(name = "communityBoardUpdate", urlPatterns = { "/board/communityBoardUpdate" })
public class CommunityBoardUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public CommunityBoardUpdate() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
