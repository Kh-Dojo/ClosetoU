package com.closetou.donation.model.dao;

import static com.closetou.common.jdbc.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.Spring;

import com.closetou.donation.model.vo.DonationForm;
import com.google.gson.JsonObject;

public class DonationDao {
	
	
	
	
	
	
	
	public int insertBoard(Connection conn, DonationForm donation) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		
		
		String query =  "INSERT INTO D_FORM VALUES(SEQ_DNO.NEXTVAL, ? , ? , ? , ?, ? , ? ,? , ? , DEFAULT, ?)";
		
		
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, donation.getNo());
			pstmt.setString(2, donation.getDf_item());
			pstmt.setString(3, donation.getDf_amount());
			pstmt.setString(4, donation.getDf_name());
			pstmt.setString(5, donation.getDf_phone());			
			pstmt.setString(6, donation.getDf_addr());
			pstmt.setString(7, donation.getDelivber());
			pstmt.setString(8, donation.getDf_details());
			pstmt.setString(9, donation.getSi_Do());
			// pstmt.setString(8, );
			
			result = pstmt.executeUpdate();
			
			if (result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public List<HashMap<Object, String>> findDonation(Connection conn) {
			
		List<HashMap<Object, String>>  resultList = new ArrayList<HashMap<Object,String>>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		
		String query =  "SELECT  DF.SIDO AS SIDO , COUNT (DF.SIDO) as DONATION_COUNT " + 
				"FROM D_FORM DF " + 
				"LEFT JOIN CMMN_CODE CC " + 
				"ON DF.SIDO =  CC.CMMN_NAME " + 
				"GROUP BY DF.SIDO ,CC.ODER " + 
				"ORDER BY CC.ODER ";
		
		try {
			pstmt = conn.prepareStatement(query);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				HashMap<Object, String> paramData = new HashMap<Object, String>();
				System.out.println(" SIDO  : "+ rs.getString("SIDO") );
				System.out.println(" DONATION_COUNT  : "+ rs.getString("DONATION_COUNT") );
				paramData.put("sido", rs.getString("SIDO"));
				paramData.put("donationCount", rs.getString("DONATION_COUNT"));

				resultList.add(paramData);
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return resultList;
	}
	
}
