package com.kh.dozo.member.model.vo;

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
	
	private String name;
	
	private String nickname;
	
	private String phone;
	
	private String addphone;
	
	private String email;
	
	private String address;
	
	private String status;
	
	private Date enrollDate;
	
}
