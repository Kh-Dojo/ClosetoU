package com.closetou.body.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.closetou.article.model.vo.Article;
import com.closetou.body.model.service.BodyService;
import com.google.gson.Gson;



@WebServlet(name = "NoticeList", urlPatterns = { "/noticelist" })
public class BodyNoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public BodyNoticeListServlet() {

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		Map<String, Object> notice = new HashMap<String, Object>();
//		List list = null;
//		
//		list = new BodyService().getList();
//		
//		notice.put("notice", list);
//		
//		 response.setContentType("application/json;charset=UTF-8");
//
//	     new Gson().toJson(list, response.getWriter());

// 		------- notice list ajax 하기 --------
		System.out.println("do get 방식");
		BodyService BS = new BodyService();
		
		System.out.println(BS);
		Gson gson = new Gson();
		
		response.setContentType("application/json;charset=utf-8");
		
		gson.toJson( BS.getNoticeList(), response.getWriter());
		
//		List<Article> noticelist = new ArrayList<>();
//		
//		System.out.println("=========BodyNoticeListServlet=============");
//		noticelist = new BodyService().getNoticeList();
//		
//		System.out.println(noticelist);
//		
//		request.setAttribute("noticelist", noticelist);
//		request.getRequestDispatcher("/index.jsp");
		
		
	
	}

}
