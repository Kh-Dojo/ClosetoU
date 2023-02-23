package com.closetou.cloth.model.dao;

import static com.closetou.common.jdbc.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.tomcat.util.buf.StringUtils;

import com.closetou.cloth.model.vo.Cloth;

public class ClothDao {

	public int saveCloth(Connection connection, Cloth cloth) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "    INSERT INTO CLOTH "
				+ "        (NO, "
				+ "        PHOTO_ID, "
				+ "        CLOTH_NAME, "
				+ "        CREATED_DATE, "
				+ "        CATEGORIES) "
				+ "    VALUES ( "
				+ "        SEQ_CLOTH_NO.NEXTVAL, "
				+ "        ?, "
				+ "        ?, "
				+ "		   DEFAULT, "
				+ "        ? "
				+ "    )";
		
		String[] catagory = cloth.getCatagory();
		String join = StringUtils.join(catagory);
		String replace = join.replace(",", ".");
				
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, cloth.getPhotoNo());
			pstmt.setString(2, cloth.getName());
			pstmt.setString(3, replace);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	
	public int getPhotoNoByPhotoId(Connection connection, String filesystemName) {
		int recentNo = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT  "
				+ "    NO  "
				+ "FROM  "
				+ "    CLOTH  "
				+ "WHERE  "
				+ "    PHOTO_ID = ?";
		
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, filesystemName);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				recentNo = rs.getInt("NO");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return recentNo;
		
	}

}
