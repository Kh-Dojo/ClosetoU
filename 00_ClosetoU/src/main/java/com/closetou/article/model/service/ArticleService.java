package com.closetou.article.model.service;

import static com.closetou.common.jdbc.JDBCTemplate.close;
import static com.closetou.common.jdbc.JDBCTemplate.commit;
import static com.closetou.common.jdbc.JDBCTemplate.getConnection;
import static com.closetou.common.jdbc.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.closetou.article.model.dao.ArticleDao;
import com.closetou.article.model.vo.Article;
import com.closetou.article.model.vo.Reply;
import com.closetou.article.model.vo.TradeArticle;
import com.closetou.cloth.model.dao.ClothDao;
import com.closetou.cloth.model.vo.Cloth;

public class ArticleService {


	// No값으로 TradeArticle을 가져오는 메소드
	public TradeArticle getTradeArticleByNo(int no) {
		TradeArticle trart = null;

		Connection connection = getConnection();

		trart = new ArticleDao().findTradeArticleByNo(connection, no);

		close(connection);

		return trart;
	}


	public List<TradeArticle> getTradeArticleByNos(ArrayList<Integer> numbers) {
		List<TradeArticle> result = null;

		Connection connection = getConnection();

		result = new ArticleDao().findTradeArticleByNos(connection, numbers);

		close(connection);

		return result;

	}


	public int saveForTrade(Article article, Cloth cloth, TradeArticle trart) {
		int result = 0;
		Connection connection = getConnection();

		// Article 넣기
		int insertBoard = new ArticleDao().insertBoard(connection, article);

		// 작성된 최근 게시물의 No를 가져옴
		int recentNo = new ArticleDao().getMostRecentlyArticleNoByMemberNo(connection, article.getUserNo());

		// 의류 넣기
		int insertCloth = new ClothDao().saveCloth(connection, cloth);

		// 등록된 의류의 No를 가져옴
		int recentClothNo = new ClothDao().getPhotoNoByPhotoId(connection, article.getRenamedFileName());

		// 거래 게시글 세팅
		trart.setNo(recentNo);
		trart.setClothNumber(recentClothNo);

		result = new ArticleDao().saveTradeArticle(connection, trart);

		if (result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		close(connection);
		return result;
	}

	
//	--------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
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
	public Article getArticleByNoForCommunity(int no, boolean hasRead) {
		Article article = null;
		
		Connection connection = getConnection();
		
		article = new ArticleDao().findArticleByNoForCommunity(connection, no);
		
		if (article != null && !hasRead) {	// 230216 8교시 읽지 않았어야만 if문 안의 게시글 증가 로직 실행되게 if 문 걸어줌
					
			int result = new ArticleDao().updateReadCount(connection, article);
					
			if(result > 0) {
				commit(connection);
			} else {
				rollback(connection);
			}
		
		}
		
		close(connection);
		
		return article;
	}


	// 게시글 파일 업로드
	public int save(Article article) {
		int result = 0;
		Connection connection = getConnection();
		
		//수정한 내용으로 게시글이 바뀌게 만들기
		if(article.getNo() > 0) {
			// update 작업
			result = new ArticleDao().updateBoard(connection, article);
		} else {
			// insert 작업
			result = new ArticleDao().insertBoard(connection, article);
		}
		

		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
			
		close(connection);
		
		return result;
	}

	// 게시글 삭제
	public int delete(int no) {
		int result = 0;
		
		Connection connection = getConnection();
															// 변경할 VISABLE 값
		result = new ArticleDao().updateStatus(connection, no, "N");	
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);		
		
		return result;
	}

	// 댓글등록
	public int saveReply(Reply reply) {
		int result = 0;
		Connection connection = getConnection();
		
		// insert 작업 DAO로 넘기기
		result = new ArticleDao().insertReply(connection, reply);
		
		if( result > 0 ) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);
		
		return result;
	
	}


	public int deleteReply(int articleNo, int replyNo) {
		int result = 0;
		
		Connection connection = getConnection();
															
		result = new ArticleDao().updateReplyStatus(connection, articleNo, replyNo, "N");	
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);		
		
		return result;
	}
	
}
