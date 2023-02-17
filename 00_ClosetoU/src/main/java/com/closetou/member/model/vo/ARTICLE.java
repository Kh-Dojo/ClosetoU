package com.closetou.member.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ARTICLE {
	
	private int no;
	
	private int userNo;
	
	private String type;
	
	private String title;
	
	private String content;
	
	private int readCount;
	
	private String Visable;
	
	private Date postDate;
	
	private String edited;
	
	private Date editDate;
	
	
}
