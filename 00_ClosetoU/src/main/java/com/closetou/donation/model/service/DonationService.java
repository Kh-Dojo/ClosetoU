package com.closetou.donation.model.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import javax.swing.Spring;

import com.closetou.donation.model.dao.DonationDao;
import com.closetou.donation.model.vo.DonationForm;
import static com.closetou.common.jdbc.JDBCTemplate.*;

public class DonationService {

	
	public List<HashMap<Object, String>> getGraphDataList(){ 

		List<HashMap<Object, String>> list = null;
		Connection conn = getConnection();
		
		list = new DonationDao().findDonation(conn);
		
		close(conn);
		
		
		return list;
		
	}
	
	// 저장 
	public int save(DonationForm df) {

		int result = 0;
		
		Connection conn = getConnection();
		
		result = new DonationDao().insertBoard(conn, df);
		
		if(result > 0) {
			commit(conn);
		}else{
			rollback(conn);
		}
		
		return result;
		
		
	}

}
