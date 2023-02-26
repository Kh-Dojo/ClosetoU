package com.closetou.cloth.model.service;

import static com.closetou.common.jdbc.JDBCTemplate.close;
import static com.closetou.common.jdbc.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.closetou.article.model.vo.TradeArticle;
import com.closetou.cloth.model.dao.ClothDao;
import com.closetou.cloth.model.vo.Cloth;
import com.closetou.cloth.model.vo.ClothPhoto;

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

	public List<Cloth> getClothes(List<TradeArticle> trlist) {
		List<Cloth> result = null;

		Connection connection = getConnection();

		result = new ClothDao().getClothlistFromTradelist(connection, trlist);

		close(connection);

		return result;
	}

	public List<ClothPhoto> getClothPhotosbyClothNo(int clothNo) {
		List<ClothPhoto> clph = new ArrayList<>();

		Connection connection = getConnection();

		clph = new ClothDao().getClothPhotosbyClothNo(connection, clothNo);
		
		close(connection);

		return clph;
	}

	public Cloth getClothbyNo(int clothNumber) {
		Cloth cloth = null;
		
		Connection connection = getConnection();
		
		cloth = new ClothDao().getClothbyNo(connection, clothNumber);
		
		close(connection);
		
		return cloth;
	}

}
