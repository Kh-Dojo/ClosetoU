package com.closetou.article.model.vo;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
	
	private int no;
	
	private int rowNum;
	
	private int userNo;
	
	private String userNickname;
	
	private String type;

	private String title;
	
	private String content;
	
	private int readCount;

	private String visable;
	
	private List<Reply> replies;
	
	private Date postDate;
	
	private String edited;
	
	private Date editDate;

}
