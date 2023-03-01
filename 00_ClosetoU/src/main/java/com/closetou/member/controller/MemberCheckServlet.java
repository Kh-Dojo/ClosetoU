package com.closetou.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.closetou.member.model.service.MemberService;

@WebServlet("/member/idCheck")
public class MemberCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MemberCheckServlet() {
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Boolean> map = new HashMap<>();
		String id = request.getParameter("id");	
		
		map.put("duplicate", new MemberService().isDuplicateId(id));
				
		response.setContentType("application/json;charset=UTF-8");
		
		new Gson().toJson(map, response.getWriter());
	} 

}
