package com.closetou.cloth.model.service;

import static com.closetou.common.jdbc.JDBCTemplate.close;
import static com.closetou.common.jdbc.JDBCTemplate.getConnection;

import java.sql.Connection;

import com.closetou.cloth.model.dao.ClothDao;
import com.closetou.cloth.model.vo.Cloth;

public class ClothService {

	public int saveCloth(Cloth cloth) {
		int result = 0;
		
		Connection connection = getConnection();
		
		result = new ClothDao().saveCloth(connection, cloth);
		
		close(connection);
		
		return result;
	}

	public int getPhotoNoByPhotoId(String filesystemName) {
		int result = 0;
		
		Connection connection = getConnection();
		
		result = new ClothDao().getPhotoNoByPhotoId(connection, filesystemName);
		
		close(connection);
		
		return result;
	}

	
}
