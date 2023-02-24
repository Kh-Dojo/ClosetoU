package com.closetou.member.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	private int no;
	
	private String id;
	
	private String password;
	
	private String role;
	
	private String name;
	
	private String nickname;
	
	private String phone;
	
	private String email;
	
	private String address;
	
	private String address_detail;
	
	private String status;
	
	private Date enrollDate;

	// 삭제 할것
	public Object getAllUsers() {
		return null;
	}
	
}
