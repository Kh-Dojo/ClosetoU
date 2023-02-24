package com.closetou.donation.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.closetou.donation.model.service.DonationService;
import com.closetou.donation.model.vo.DonationForm;
import com.closetou.member.model.vo.Member;


@WebServlet(name = "DonationForm", urlPatterns = { "/donation/donationform" })
public class DonationFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DonationFormServlet() {

    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// 기부 신청서 작성 페이지로 포워드
    	request.getRequestDispatcher("/views/donation/donation_Form.jsp").forward(request, response);
	}
    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
				
		HttpSession session = request.getSession(false);		
		Member loginMember = session == null ? null : (Member)session.getAttribute("loginMember");
		
		
		if(loginMember != null) {
			
		DonationForm df = new DonationForm();
		df.setNo(loginMember.getNo());
		df.setDf_item(request.getParameter("D_item"));
		df.setDf_amount(request.getParameter("rangeresult"));
		df.setDf_name(request.getParameter("D_name"));
		df.setDf_phone(request.getParameter("D_phone"));
		String sample4 = (request.getParameter("sample6_address") + " " + request.getParameter("sample6_detailAddress"));
		df.setDf_addr(sample4);
		
		df.setDelivber(request.getParameter("deliver"));
		df.setDf_details(request.getParameter("content"));
		df.setSi_Do(request.getParameter("sido"));
		
		System.out.println(df);
		
		int result = new DonationService().save(df);
		
		if(result > 0) {
			request.setAttribute("msg", "게시글 등록 성공");
			request.setAttribute("location", "/");
		}
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	
		
		}
	}

}
