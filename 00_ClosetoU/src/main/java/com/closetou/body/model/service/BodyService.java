package com.closetou.body.model.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import com.closetou.article.model.vo.Article;
import com.closetou.body.model.dao.Bodydao;
import com.google.gson.JsonElement;
import static com.closetou.common.jdbc.JDBCTemplate.*;

public class BodyService {



	public List<HashMap<Object, String>> getNoticeList() {
		
		List<HashMap<Object, String>> list = null;
		Connection conn = getConnection();
		
		list = new Bodydao().findList(conn);
		
		close(conn);
		
		return list;
	}

	public List<HashMap<Object, String>> getPopList() {
		List<HashMap<Object, String>> list = null;
		Connection conn = getConnection();
		
		list = new Bodydao().findPopList(conn);
		
		close(conn);
		
		return list;
	}

//	public List<Article> getNoticeList() {
//
//		List<Article> noticelist = null;
//		Connection conn = getConnection();
//		
//		noticelist = new Bodydao().findList(conn);
//		close(conn);
//		
//		return noticelist;
//	}


	
	

}
