package com.closetou.article.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
	
	private int no;			// 댓글번호
	
	private int articleNo;	// 게시물번호
	
	private int userNo;		// 댓글작성자회원번호
	
	private String userNickname;	// 댓글작성자닉네임
	
	private String content;	// 댓글내용
	
	private String visable;	// 비공개처리 여부(Y/N)
	
	private String secret;	// 비밀댓글 여부(Y/N)
	
	private Date commentDate;	// 댓글등록일자

	private String edited;		// 수정내역
	
	private Date editDate;		// 최종수정일자

}
