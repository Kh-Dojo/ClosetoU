package com.closetou.member.model.service;


import static com.closetou.common.jdbc.JDBCTemplate.close;
import static com.closetou.common.jdbc.JDBCTemplate.commit;
import static com.closetou.common.jdbc.JDBCTemplate.getConnection;
import static com.closetou.common.jdbc.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.closetou.article.model.vo.Article;
import com.closetou.member.model.dao.MemberDao;
import com.closetou.member.model.vo.Member;

public class MemberService {

	public Member login(String userId, String userPwd) {
		Member member = this.findMemberById(userId);
		
		if(member == null || !member.getPassword().equals(userPwd)) {
			return null;
		} 			
			return member;
	}

	public Member findMemberById(String userId) {
		Connection connection = getConnection();
		
		Member member = new MemberDao().findMemberById(connection, userId);
		
		close(connection);
		
		return member;
	}

	public Boolean isDuplicateId(String userId) {
		return this.findMemberById(userId) != null;
	}

	public int updatePwd(int no, String userPwd) {
		int result = 0;
		Connection connection = getConnection();
		
		result = new MemberDao().updatePwd(connection, no, userPwd);
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);
		
		return result;
	}

	public int save(Member member) {
		int result = 0;
		Connection connection = getConnection();
		
		if(member.getNo() > 0) {
			// update 작업
			result = new MemberDao().updateMember(connection, member);
		} else {
			// insert 작업
			result = new MemberDao().insertMember(connection, member);
		}
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);
		
		return result;
	}

	public int delete(int no) {
		int result = 0;
		Connection connection = getConnection();
		
		result = new MemberDao().updateMemberStatus(connection, no, "N");
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);
		
		return result;
	}
	
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


}
