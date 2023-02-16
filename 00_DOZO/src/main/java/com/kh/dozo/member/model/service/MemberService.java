package com.kh.dozo.member.model.service;


import static com.kh.dozo.common.jdbc.JDBCTemplate.getConnection;
import static com.kh.dozo.common.jdbc.JDBCTemplate.close;
import static com.kh.dozo.common.jdbc.JDBCTemplate.commit;
import static com.kh.dozo.common.jdbc.JDBCTemplate.rollback;

import java.sql.Connection;

import com.kh.dozo.member.model.dao.MemberDao;
import com.kh.dozo.member.model.vo.Member;

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


}
