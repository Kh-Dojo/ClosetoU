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
		ArrayList<Integer> numbers = new ArrayList<>();

		for (Article article : list) {
			int no = article.getNo();

			numbers.add(no);
		}
		return numbers;
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

  
	// 게시판에서 제목 클릭하면 상세 페이지 나오게 만들기
	// 상세 게시글 들어가면 값 보여주는 메소드
	public Article getArticleByNoForCommunity(int no) {
		Article article = null;
		
		Connection connection = getConnection();
		
		article = new ArticleDao().findArticleByNoForCommunity(connection, no);
		
		close(connection);
		
		return article;


	public List<TradeArticle> getTradeArticleByNos(ArrayList<Integer> numbers) {
		List<TradeArticle> result = null;
		
		Connection connection = getConnection();
		
		result = new ArticleDao().findTradeArticleByNos(connection, numbers);
		
		close(connection);
				
		return result;
		
	

	}

}








