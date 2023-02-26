package com.closetou.donation.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.closetou.donation.model.service.DonationService;
import com.google.gson.Gson;


@WebServlet(name = "DonationBoard", urlPatterns = { "/donationboard" })
public class DonationBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DonationBoardServlet() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("=========================DOGET== ");
		DonationService donationService = new DonationService() ;
		
		
	    Gson gson = new Gson();
	    response.setContentType("application/json;charset=utf-8");
	    gson.toJson( donationService.getGraphDataList(), response.getWriter());
//		request.setAttribute("graphDataList",gson.toJson( donationService.getGraphDataList()));
		// request.getRequestDispatcher("/views/donation/donation_Board.jsp").forward(request, response);
//		request.getRequestDispatcher("/index.jsp").forward(request, response);
		
	}

}
