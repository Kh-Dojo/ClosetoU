package com.closetou.body.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.closetou.article.model.vo.Article;
import com.closetou.body.model.service.BodyService;
import com.google.gson.Gson;


@WebServlet(name="Poplist" , urlPatterns = { "/poplist" })
public class BodyPoplularServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BodyPoplularServlet() {


    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		List<Article> list = null;
		
		BodyService BS = new BodyService();
		
		Gson gson = new Gson();
		
		response.setContentType("application/json;charset=utf-8");
		gson.toJson(BS.getPopList(), response.getWriter());
		
	}

	

}
