package com.closetou.article.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
	
	private int no;
	
	private int userNo;
	
	private String type;

	private String title;
	
	private String content;
	
	private int readCount;

	private String visable;
	
	private Date postDate;
	
	private String edited;
	
	private Date editDate;

}
