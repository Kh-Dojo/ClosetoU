package com.closetou.article.model.service;

import static com.closetou.common.jdbc.JDBCTemplate.close;
import static com.closetou.common.jdbc.JDBCTemplate.getConnection;
import static com.closetou.common.jdbc.JDBCTemplate.commit;
import static com.closetou.common.jdbc.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.closetou.article.model.dao.ArticleDao;
import com.closetou.article.model.vo.Article;
import com.closetou.article.model.vo.TradeArticle;

public class ArticleService {

	// 아티클로부터 NO를 추출하여 ArrayList 객체로 반환하는 메소드
	@SuppressWarnings("null")
	public ArrayList<Integer> noFromArticle(List<Article> list) {
		ArrayList<Integer> nos = null;

		for (Article article : list) {
			nos.add(article.getNo());
		}

		return nos;
	}

	// No값으로 TradeArticle을 가져오는 메소드
	public TradeArticle getTradeArticleByNo(int no) {
		TradeArticle trart = null;

		Connection connection = getConnection();

		trart = new ArticleDao().findTradeArticleByNo(connection, no);

		close(connection);

		return trart;
	}

	// 문의글 DB에 넣는 메소드
	public int saveQna(Article article) {
		int result = 0;
		
		Connection connection = getConnection();
		
		result = new ArticleDao().insertQna(connection, article);
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);
		
		return result;
	}

}








