package com.closetou.cloth.model.dao;


import static com.closetou.common.jdbc.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Connection;
import com.closetou.cloth.model.vo.Cloth;

public class ClothDao {

	public int saveCloth(Connection connection, Cloth cloth) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT\r\n"
				+ "    INTO CLOTH(SEQ_CLOTH_NO.NEXTVAL, ?, ?, DEFAULT, ?);";
		
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, cloth.getPhotoNo());
			pstmt.setString(2, cloth.getName());
			
			// 와씨 멘붕 문자열을 필드로 줘야함ㅠㅠ
			pstmt.setString(3, (cloth.getCatagory()).toString());
			
			result = pstmt.executeUpdate(query);
	
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
