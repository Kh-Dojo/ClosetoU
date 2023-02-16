package com.closetou.member.model.service;


import static com.closetou.common.jdbc.JDBCTemplate.getConnection;
import static com.closetou.common.jdbc.JDBCTemplate.close;

import java.sql.Connection;

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


}
