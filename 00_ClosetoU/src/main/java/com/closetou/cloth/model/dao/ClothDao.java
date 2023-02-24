package com.closetou.cloth.model.dao;

import static com.closetou.common.jdbc.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.buf.StringUtils;

import com.closetou.article.model.vo.TradeArticle;
import com.closetou.cloth.model.vo.Cloth;
import com.closetou.cloth.model.vo.ClothPhoto;

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

		String[] category = cloth.getCategory();
		String join = StringUtils.join(category);
		String replace = join.replace(",", ".");

		try {
			pstmt = connection.prepareStatement(query);

			pstmt.setString(1, cloth.getPhotoId());
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
				+ "    NO  " + "FROM  " 
				+ "    CLOTH  " 
				+ "WHERE  " 
				+ "    PHOTO_ID = ?";

		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, filesystemName);

			rs = pstmt.executeQuery();

			if (rs.next()) {
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

	public int saveClothPhoto(Connection connection, ClothPhoto cloph) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT " 
				+ "    INTO CLOTH_PHOTO ( " 
				+ "        PHOTO_ID, " 
				+ "        NO, "
				+ "        CLOTH_NO, " 
				+ "        ORIGINAL_NAME, " 
				+ "        CREATED_DATE " 
				+ "    ) " 
				+ "    VALUES "
				+ "    (?, SEQ_CLPH.NEXTVAL, ?, ?, DEFAULT)";

		System.out.println(cloph);

		try {
			pstmt = connection.prepareStatement(query);

			pstmt.setString(1, cloph.getPhotoId());
			pstmt.setInt(2, cloph.getClothNo());
			pstmt.setString(3, cloph.getOriginalName());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public List<Cloth> getClothlistFromTradelist(Connection connection, List<TradeArticle> trlist) {
		List<Cloth> cllist = new ArrayList<>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<Integer> numbers = new ArrayList<>();
		
		for ( TradeArticle trart : trlist) {
			numbers.add(trart.getClothNumber());
		}
			
		String query = "SELECT * FROM CLOTH WHERE NO IN (";

		for (int i = 0; i < numbers.size() - 1; i++) {
			query += (numbers.get(i) + ", ");
		}
		query += numbers.get(numbers.size() - 1);
		query += ") ORDER BY NO DESC";

		try {
			pstmt = connection.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Cloth cl = new Cloth();

				cl.setNo(rs.getInt("NO"));
				cl.setPhotoId(rs.getString("PHOTO_ID"));
				cl.setName(rs.getString("CLOTH_NAME"));
				cl.setCreateDate(rs.getDate("CREATED_DATE"));

				cllist.add(cl);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return cllist;
	}

	public List<ClothPhoto> getClothPhotosbyClothNo(Connection connection, int clothNo) {
		List<ClothPhoto> clphotos = new ArrayList<ClothPhoto>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "SELECT "
				+ "    PHOTO_ID, "
				+ "    NO, "
				+ "    CLOTH_NO, "
				+ "    ORIGINAL_NAME, "
				+ "    CREATED_DATE "
				+ "FROM "
				+ "    CLOTH_PHOTO "
				+ "WHERE "
				+ "    CLOTH_NO = ?";
		
		
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, clothNo);
	
			rs = pstmt.executeQuery();
	
			while(rs.next()) {
				ClothPhoto clpho = new ClothPhoto();
				
				clpho.setPhotoId(rs.getString("PHOTO_ID"));
				clpho.setNo(rs.getInt("NO"));
				clpho.setClothNo(rs.getInt("CLOTH_NO"));
				clpho.setOriginalName(rs.getString("ORIGINAL_NAME"));
				clpho.setCreatedDate(rs.getDate("CREATED_DATE"));
				
				clphotos.add(clpho);
				
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return clphotos;
	}

}
